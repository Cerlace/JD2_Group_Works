package itacademy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToMany(mappedBy = "courses", cascade = CascadeType.MERGE)
    private Set<Student> students;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            mappedBy = "course")
    private Set<Task> tasks;

    @Column
    private String name;

    @Column
    private Integer duration;
}
