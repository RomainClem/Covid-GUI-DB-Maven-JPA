package model.java;

public class Person {
    //region Attributes
    private Name name;
    private String phone, email;
    private int id;
    //endregion

    public Person(Name name, String phone, String email, int id) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.id = id;
    }
}