package com.moloko.consolecrudapp.model;

/**
 * @author Jack Milk
 */
public class Skill {
    private static int genId = 1;

    int id = genId;
    String name;

    public Skill(String name) {
        this.name = name;
        genId++;
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

    public String fromWriteToConsole() {
        return id + ": " + name;
    }

    @Override
    public String toString() {
        return id + ";" + name;
    }
}
