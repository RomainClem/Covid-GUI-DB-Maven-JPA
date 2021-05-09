package model.java;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "person")

public class Person implements Serializable {
    //region Attributes
    @Id
    @Column(name = "person_id", unique = true)
    private Integer id;

    @Column(name = "phoneNumber", nullable = false)
    private String phone;

    @Column(name = "email", nullable = false)
    private String email;

    @OneToOne(mappedBy = "person", cascade=CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Name name;
    //endregion

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

    public String getFName(){return name.getFirstName();};

    public String getMName(){return name.getMiddleName();};

    public String getLName(){return name.getLastName();};

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

    public String print() {
        return  "Id#" + id + ", " +
                name +
                ", ph: " + phone +
                ", @: " + email;
    }

    @Override
    public String toString(){
        return id + " " + name.getFirstName() + " " + name.getLastName();
    }
}