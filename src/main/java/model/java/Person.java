package model.java;

import javax.persistence.*;

@Entity
@Table(name = "person")

public class Person {

    @Id
    @Column(name = "ID", unique = true)
    private int id;

    @Column(name = "phoneNumber", nullable = false)
    private String phone;

    @Column(name = "email", nullable = false)
    private String email;

    @OneToOne
    @JoinColumn(name = "ID", nullable = false)
    private Name name;

    public Person(Name name, String phone, String email, int id) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.id = id;
    }

    public Person() {

    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + name.getFirstName() + '\'' +
                ", middleName='" + name.getMiddleName() + '\'' +
                ", lastName='" + name.getLastName() + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", name=" + name +
                '}';
    }
}