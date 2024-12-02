package itacademy.entity;

import javax.persistence.*;

@Entity
@Table
public class EntityClassWithoutTableName {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @Column
    private String name;
    private int age;
}
