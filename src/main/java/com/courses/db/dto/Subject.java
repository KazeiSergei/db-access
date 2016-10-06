package com.courses.db.dto;

/**
 * Created by Sergei on 04.07.2016.
 */
public class Subject {
    private int id;
    private String name;

    public Subject(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Subject( String name) {

        this.name = name;
    }

    public Subject() {

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

    @Override
    public String toString() {
        return "Subject(" + "id=" + id + ", name='" + name + '\'' +  ')';
    }
}
