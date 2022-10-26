package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instructor")
@NoArgsConstructor
@Getter
@Setter
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    @ManyToMany( cascade = {
            CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.MERGE,
            CascadeType.PERSIST},
            fetch = FetchType.EAGER)
    @JoinTable(
            name = "instructor_course",
            joinColumns = @JoinColumn(name = "INSTRUCTOR_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "COURSE_ID", referencedColumnName = "id")
    )
    private List<Course> courses = new ArrayList<>();


    public Instructor(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "\n-----------------------" +
                "\nid = " + id +
                "\nfirstName =' " + firstName + '\'' +
                "\nlastName = '" + lastName + '\'' +
                "\nemail = '" + email + '\'' +
                "\nphoneNumber = '" + phoneNumber + '\'';
    }
}
