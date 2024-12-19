package itacademy.entity;

import itacademy.entity.embedded.PersonData;
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
import javax.persistence.Embedded;

import java.util.HashSet;
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

    @OneToMany(cascade = {CascadeType.PERSIST,
            CascadeType.MERGE},
            fetch = FetchType.LAZY,
            mappedBy = "teacher")
    @Builder.Default
    private Set<Course> courses = new HashSet<>();
}
