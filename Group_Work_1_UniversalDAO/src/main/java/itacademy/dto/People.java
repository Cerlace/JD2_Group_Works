package itacademy.dto;

import itacademy.annotations.ColumnAnn;
import itacademy.annotations.IdAnn;
import itacademy.annotations.TableAnn;

@TableAnn(name = "people")
public class People {
    @IdAnn
    Integer id;

    @ColumnAnn(name = "name")
    String name;

    @ColumnAnn(name = "surname")
    String surname;

    @ColumnAnn(name = "age")
    int age;

    public People(Integer id, String name, String surname, int age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public People() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                '}';
    }

    //TODO написать Builder для класса, по аналогии с тем что в Address
}
