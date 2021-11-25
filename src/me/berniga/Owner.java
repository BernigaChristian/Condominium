package me.berniga;

public class Owner {
    private static int owners = 0;
    private final int id;
    private String name;
    private String surname;
    //constructors
    public Owner(String name, String surname) {
        id = ++owners;
        this.name = name;
        this.surname = surname;
    }
    //getters & setters
    public int getId() {
        return id;
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
    //methods
}
