package model.java;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "name")

public class Name implements Serializable{
    //region Attributes
    @Id
    @Column(name = "name_id", unique = true)
    private Integer id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "middleName", nullable = false)
    private String middleName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @OneToOne
    @MapsId
    @JoinColumn(name = "name_id")
    private Person person;
    //endregion

    public Name(int id, String firstName, String middleName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public Name() {

    }

    //region Getters/Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return  firstName + ' ' +
                middleName + ' ' +
                lastName;
    }

    //endregion
}
