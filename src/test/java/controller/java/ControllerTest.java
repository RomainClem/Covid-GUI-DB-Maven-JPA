package controller.java;
import model.java.Contact;
import model.java.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    private Controller controller;

    public static List<Object> personInfo(){
        List<Object> personInfo = new LinkedList<>();
        Collections.addAll(personInfo, "Test", "tester", "Testerson", 9999, "555-test", "test@test.com");
        return personInfo;
    }

    public static Contact contact(){
        return new Contact(1,3, LocalDateTime.now());
    }

    @BeforeEach
    public void setUp() throws Exception {
        controller = new Controller();
    }

    @RepeatedTest(5)
    @DisplayName("Adding a Person to the database.")
    public void testAddPerson() {
        // Checking if you can correctly only add the same person once
        assertTrue(controller.addPerson(personInfo()));
        assertFalse(controller.addPerson(personInfo()));

        // Removing it to reset the test
        controller.deletePerson((Integer) personInfo().get(3));
    }

    @Test
    @DisplayName("Finding a person by ID in the database.")
    public void testFindPerson(){
        controller.addPerson(personInfo());

        // Checking if each values have been correctly added in the db
        assertEquals(10, controller.findPerson((Integer) personInfo().get(3)).getId());
        assertEquals("Test", controller.findPerson((Integer) personInfo().get(3)).getFName());
        assertEquals("tester", controller.findPerson((Integer) personInfo().get(3)).getMName());
        assertEquals("Testerson", controller.findPerson((Integer) personInfo().get(3)).getLName());
        assertEquals("555-test", controller.findPerson((Integer) personInfo().get(3)).getPhone());
        assertEquals("test@test.com", controller.findPerson((Integer) personInfo().get(3)).getEmail());

        // Removing it to reset the test
        controller.deletePerson((Integer) personInfo().get(3));
    }

    @RepeatedTest(7)
    @DisplayName("Testing Listing and deleting contacts.")
    public void testListContact(){
        Contact contact = contact();
        assertNotNull(controller.loadContact());

        List<Contact> contactList = controller.loadContact();

        controller.addContact(contact);
        List<Contact> newContactList = controller.loadContact();

        assertNotEquals(contactList.size(), newContactList.size());
        assertNotEquals(contactList, newContactList);

        for (Contact c: newContactList ) {
            if (c.getDateContact().getMinute() == contact.getDateContact().getMinute()){
                controller.deleteContact(c.getId());
                contact = c;
            }
        }

        newContactList = controller.loadContact();
        assertEquals(contactList.size(), newContactList.size());

        for (Contact c: newContactList) {
            assertNotEquals(c, contact);
        }
    }



}