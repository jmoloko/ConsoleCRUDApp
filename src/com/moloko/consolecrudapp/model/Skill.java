package com.moloko.consolecrudapp.model;

/**
 * @author Jack Milk
 */
public class Skill {

    int id;
    String name;

    public Skill(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return id + ";" + name;
    }
}
