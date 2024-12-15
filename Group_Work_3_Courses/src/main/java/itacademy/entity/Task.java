package itacademy.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@ToString(exclude = "course")
@EqualsAndHashCode(exclude = "course")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @Column
    private String title;

    @Column
    private String description;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            mappedBy = "task")
    private Set<Grade> grades = new HashSet<>();
}
