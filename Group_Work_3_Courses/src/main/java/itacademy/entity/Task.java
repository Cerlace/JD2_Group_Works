package itacademy.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.util.Date;
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

    @Column(name = "task_date")
    private Date taskDate;

    @OneToMany(cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            mappedBy = "task")
    private Set<Grade> grades = new HashSet<>();
}
