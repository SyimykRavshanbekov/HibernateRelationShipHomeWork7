package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
@NoArgsConstructor
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseName;
    private int duration;
    private LocalDate createAt;
    private String imageLink;
    private String description;

    @ManyToMany(cascade = {
            CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.MERGE,
            CascadeType.PERSIST},mappedBy = "courses",fetch = FetchType.EAGER)
    private List<Instructor> instructors = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "course")
    private List<Lesson> lessons = new ArrayList<>();

    public Course(String courseName, int duration, LocalDate createAt, String imageLink, String description) {
        this.courseName = courseName;
        this.duration = duration;
        this.createAt = createAt;
        this.imageLink = imageLink;
        this.description = description;
    }

    @Override
    public String toString() {
        return "\n--------------------------" +
                "\nid = " + id +
                "\ncourseName = '" + courseName + '\'' +
                "\nduration = " + duration +
                "\ncreateAt = " + createAt +
                "\nimageLink = '" + imageLink + '\'' +
                "\ndescription = '" + description;
    }
}
