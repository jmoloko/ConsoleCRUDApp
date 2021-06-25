package com.moloko.consolecrudapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jack Milk
 */
public class Developer {

    private static int genId = 1;

    private int id = genId;
    private String firstName;
    private String lastName;
    private List<Skill> skills = new ArrayList<>();

    public Developer(String firstName, String lastName, List<Skill> skills) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.skills = skills;
        genId++;
    }

    // If add one skill
    public Developer(String firstName, String lastName, Skill skill) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.skills.add(skill);
        genId++;
    }

    // If add without skill
    public Developer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        genId++;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    // If set one skill
    public void setSkill(Skill skill){
        this.skills.add(skill);
    }

    @Override
    public String toString() {
        return id + ";" + firstName + ";" + lastName + ";" + skills;
    }
}
