package com.moloko.consolecrudapp.repository;

import com.moloko.consolecrudapp.model.Developer;
import com.moloko.consolecrudapp.model.Skill;
import com.moloko.consolecrudapp.model.TeamStatus;

import java.util.List;

/**
 * @author Jack Milk
 */
public interface TeamRepository {
    public List<String> getAllTeams();
    public void createAndSaveTeam(String name, List<Developer> developers);
    public void updateTeamFromId(int id, String newName, List<Developer> newDevelopers);
    public void deleteTeamFromId(int id);
}
