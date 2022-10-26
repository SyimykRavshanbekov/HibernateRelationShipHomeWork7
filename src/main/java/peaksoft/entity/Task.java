package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import peaksoft.Util.Util;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task_name")
    private String name;

    @Column(name = "task_dead_line")
    private LocalDate deadLine;

    private String task;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH},fetch = FetchType.LAZY)
    private Lesson lesson;

    public Task(String name, LocalDate deadLine, String task, Long id) {
        this.name = name;
        this.deadLine = deadLine;
        this.task = task;
        this.lesson = convertLesson(id);
    }

    private Lesson convertLesson(Long id){
        SessionFactory sessionFactory = Util.getSessionFactory();
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            Lesson lesson1 = session.get(Lesson.class, id);
            session.getTransaction().commit();
            return lesson1;
        } catch (HibernateException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public String toString() {
        return "-----------------" +
                "\nid = " + id +
                "\nname = '" + name + '\'' +
                "\ndeadLine = " + deadLine +
                "\ntask = '" + task;
    }
}
