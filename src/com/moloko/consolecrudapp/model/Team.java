package com.moloko.consolecrudapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jack Milk
 */
public class Team {

    private int id;
    private String name;
    private List<Developer> developers = new ArrayList<>();
    private Enum<TeamStatus> status = TeamStatus.ACTIVE;


    public Team(int id, String name, List<Developer> developers) {
        this.id = id;
        this.name = name;
        this.developers = developers;
    }

    // If create Team with one developer
    public Team(int id, String name, Developer developer) {
        this.id = id;
        this.name = name;
        this.developers.add(developer);
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

    public List<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Developer> developers) {
        this.developers = developers;
    }

    // If set one developer
    public void setDeveloper(Developer developer) {
        this.developers.add(developer);
    }

    public Enum<TeamStatus> getStatus() {
        return status;
    }

    public void setStatus(Enum<TeamStatus> status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return id + ";" + name + ";" + developers + ";" + status;
    }
}
