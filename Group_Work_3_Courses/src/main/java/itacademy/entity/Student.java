package itacademy.entity;

import itacademy.entity.embedded.PersonData;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

import java.util.Set;

@Builder
@Data
@ToString(exclude = "courses")
@EqualsAndHashCode(exclude = "courses")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @Embedded
    private PersonData personData;

    @ManyToMany(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinTable(name = "student_course",
    joinColumns = {@JoinColumn(name = "student_id")},
    inverseJoinColumns = {@JoinColumn(name = "course_id")})
    private Set<Course> courses;

    @OneToMany(mappedBy = "student")
    private Set<Grade> grades;
}
