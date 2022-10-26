package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import peaksoft.Util.Util;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "lesson")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lesson_name")
    private String name;

    @Column(name = "lesson_video_link")
    private String videoLink;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "lesson_id")
    private List<Task> taskList = new ArrayList<>();

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH},fetch = FetchType.LAZY)
    private Course course;

    public Lesson(String name, String videoLink, Long courseId) {
        this.name = name;
        this.videoLink = videoLink;
        this.course = convertCourse(courseId);
    }


    private Course convertCourse(Long id){
        SessionFactory sessionFactory = Util.getSessionFactory();
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            Course course = session.get(Course.class, id);
            session.getTransaction().commit();
            return course;
        } catch (HibernateException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public String toString() {
        return "\n------------------" +
                "\nid = " + id +
                "\nname = '" + name + '\'' +
                "\nvideoLink = '" + videoLink;
    }
}
