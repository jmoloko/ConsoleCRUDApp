package com.moloko.consolecrudapp.repository;

import com.moloko.consolecrudapp.model.Skill;

import java.util.List;

/**
 * @author Jack Milk
 */
public interface DeveloperRepository{
    public List<String> getAllDevelopers();
    public void createAndSaveDeveloper(String firstName, String lastName, List<Skill> skills);
    public void updateDeveloperFromId(int id, String newFirstName, String newLastName, List<Skill> newSkills);
    public void deleteDeveloperFromId(int id);
}
