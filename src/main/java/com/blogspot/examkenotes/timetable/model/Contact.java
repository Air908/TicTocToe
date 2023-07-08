package com.blogspot.examkenotes.timetable.model;

public class Contact {
    private int id;
    private String classname;

    public Contact(int id, String classname) {
        this.id = id;
        this.classname = classname;
    }

    public Contact(String classname) {
        this.classname = classname;
    }

    public Contact() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }
}
