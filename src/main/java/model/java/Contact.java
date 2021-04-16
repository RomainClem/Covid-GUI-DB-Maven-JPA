package model.java;

import java.time.LocalDateTime;

public class Contact {
    //region Attributes
    private int personId1, personId2;
    private LocalDateTime dateContact;
    //endregion


    public Contact(int pId1, int pId2, LocalDateTime dateCtc) {
        this.personId1 = pId1;
        this.personId2 = pId2;
        this.dateContact = dateCtc;
    }
}
