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
}