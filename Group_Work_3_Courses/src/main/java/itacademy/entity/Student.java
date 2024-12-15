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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Embedded
    private PersonData personData;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "student_course",
    joinColumns = {@JoinColumn(name = "student_id")},
    inverseJoinColumns = {@JoinColumn(name = "course_id")})
    private Set<Course> courses;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            mappedBy = "student")
    private Set<Grade> grades;
}
