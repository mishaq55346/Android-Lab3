package com.example.thirdlab;

public class ChickenContact {
    int id;
    String name;
    String date;

    public ChickenContact() {

    }

    public ChickenContact(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public ChickenContact(int id, String name, String date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
