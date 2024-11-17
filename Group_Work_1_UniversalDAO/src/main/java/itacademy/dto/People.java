package itacademy.dto;

import itacademy.annotations.ColumnAnn;
import itacademy.annotations.IdAnn;
import itacademy.annotations.TableAnn;

@TableAnn(name = "people")
public class People {
    @IdAnn
    @ColumnAnn(name = "id")
    private Integer id;

    @ColumnAnn(name = "name")
    private String name;

    @ColumnAnn(name = "surname")
    private String surname;

    @ColumnAnn(name = "age")
    private int age;

    public People(Integer id, String name, String surname, int age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public People() {
    }

    public static PeopleBuilder builder() {
        return new PeopleBuilder();
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

    public static class PeopleBuilder {
        private Integer id;
        private String name;
        private String surname;
        private int age;

        public PeopleBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public PeopleBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PeopleBuilder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public PeopleBuilder age(int age) {
            this.age = age;
            return this;
        }

        public People build() {
            return new People(this.id, this.name, this.surname, this.age);
        }
    }
}
