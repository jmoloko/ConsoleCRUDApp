package com.moloko.consolecrudapp.controller;


import com.moloko.consolecrudapp.model.Developer;
import com.moloko.consolecrudapp.model.Skill;
import com.moloko.consolecrudapp.model.Team;
import com.moloko.consolecrudapp.repository.JavaIODeveloperRepositoryImpl;
import com.moloko.consolecrudapp.repository.JavaIOTeamRepositoryImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Jack Milk
 */
public class TeamController {
    private static JavaIODeveloperRepositoryImpl developer = JavaIODeveloperRepositoryImpl.getDeveloperRepo();
    private static JavaIOTeamRepositoryImpl team = JavaIOTeamRepositoryImpl.getTeamRepo();
    private static int teamId = team.getLastId() + 1;


    public static void viewAllTeams(){
        for (Team team: team.getAll()){
            teamToConsole(team);
        }
        System.out.println();
    }

    public static void viewTeamByID(String id){
        try {
            if (isDigit(id)) {
                Team teamById = team.getById(Integer.parseInt(id));
                teamToConsole(teamById);
            } else {
                System.out.println("Enter a digital designation id");
            }
        } catch (NullPointerException e){
            System.out.println("This ID does not exist");
        }
    }

    public static void save(String name, List<String> developers){
        List<Developer> developerList = new ArrayList<>();
        for (String dev: developers){
            Developer newDeveloper = developer.getById(Integer.parseInt(dev));
            developerList.add(newDeveloper);
        }
        Team newTeam = team.save(new Team(teamId, name, developerList));
        viewAllTeams();
        System.out.println();
    }

    public static void updateTeamForId(String id, String name, List<String> developers){
        try {
            if (isDigit(id)) {
                List<Developer> developerList = new ArrayList<>();
                for (String dev: developers){
                    Developer developerUpd = developer.getById(Integer.parseInt(dev));
                    developerList.add(developerUpd);
                }
                team.update(new Team(Integer.parseInt(id), name, developerList));
                viewAllTeams();
            } else {
                System.out.println("Enter a digital designation id");
            }
        } catch (NullPointerException e) {
            System.out.println("This ID does not exist");
        }
    }

    public static void deleteTeamById(String id){
        try {
            if (isDigit(id)) {
                int teamId = Integer.parseInt(id);
                team.deleteById(teamId);
            } else {
                System.out.println("Enter a digital designation id");
            }
        } catch (NullPointerException e) {
            System.out.println("This ID does not exist");
        }
    }

    private static boolean isDigit(String enterString) {
        try {
            Integer.parseInt(enterString.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static void teamToConsole(Team team){
        System.out.print("| Team -> id:"+ team.getId() + " " + team.getName());
        System.out.print(" | Developers -> ");
        team.getDevelopers().forEach(d -> System.out.print(d.getId() + ":" + d.getFirstName() + " " + d.getLastName() + " "));
        System.out.print(" | All Skills -> ");
        Set<String> skills = new HashSet<>();
        for (Developer dev: team.getDevelopers()){
            for (Skill skill: dev.getSkills()){
                skills.add(skill.getName());
            }
        }
        skills.forEach(s -> System.out.print(s + " "));
        System.out.println("|");
    }
}
