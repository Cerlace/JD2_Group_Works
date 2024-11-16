package itacademy.dto;

import jakarta.persistence.*;

@Table(name = "address")
public class Address {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "street")
    private String street;
    @Column(name = "house")
    private Integer house;


    public Address(Integer id, String street, Integer house) {
        this.id = id;
        this.street = street;
        this.house = house;
    }

    public Address() {
    }

    public static AddressBuilder builder() {
        return new AddressBuilder();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getHouse() {
        return house;
    }

    public void setHouse(Integer house) {
        this.house = house;
    }

    @Override
    public String toString() {
        return "Address {" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", house=" + house +
                '}';
    }

    public static class AddressBuilder {
        private Integer id;
        private String street;
        private Integer house;

        public AddressBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public AddressBuilder street(String street) {
            this.street = street;
            return this;
        }

        public AddressBuilder house(Integer house) {
            this.house = house;
            return this;
        }

        public Address build() {
            return new Address(id, street, house);
        }
    }
}