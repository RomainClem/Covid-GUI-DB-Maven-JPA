package model.java;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "Contact")

public class Contact implements Serializable {
    //region Attributes
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "contact_id", unique = true)
    private int id;

    @Column(name = "person1Id", nullable = false)
    private int personId1;

    @Column(name = "person2Id", nullable = false)
    private int personId2;

    @Column(name = "dateContact", nullable = false)
    private LocalDateTime dateContact;
    //endregion

    public Contact(int pId1, int pId2, LocalDateTime dateCtc) {
        this.personId1 = pId1;
        this.personId2 = pId2;
        this.dateContact = dateCtc;
    }

    public Contact() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonId1() {
        return personId1;
    }

    public void setPersonId1(int personId1) {
        this.personId1 = personId1;
    }

    public int getPersonId2() {
        return personId2;
    }

    public void setPersonId2(int personId2) {
        this.personId2 = personId2;
    }

    public LocalDateTime getDateContact() {
        return dateContact;
    }

    public void setDateContact(LocalDateTime dateContact) {
        this.dateContact = dateContact;
    }
}
