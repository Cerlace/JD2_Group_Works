package itacademy.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Data
@ToString(exclude = {"student", "task"})
@EqualsAndHashCode(exclude = {"student", "task"})
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "grades")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    @Column
    private String grade;

    @Column
    private String feedback;
}
