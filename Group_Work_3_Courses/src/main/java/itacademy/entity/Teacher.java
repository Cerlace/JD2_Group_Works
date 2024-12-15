package itacademy.entity;

import itacademy.entity.embedded.PersonData;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "courses")
@EqualsAndHashCode(exclude = "courses")
@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Embedded
    private PersonData personData;

    @OneToMany(cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY,
            mappedBy = "teacher")
    private Set<Course> courses;
}
