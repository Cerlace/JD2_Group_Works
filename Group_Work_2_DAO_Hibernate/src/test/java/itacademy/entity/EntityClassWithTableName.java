package itacademy.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "TestTable")
public class EntityClassWithTableName {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @Column
    private String name;

    @Column(name = "last_name")
    private String lastName;

    private int age;
}
