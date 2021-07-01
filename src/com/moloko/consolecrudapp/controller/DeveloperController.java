package com.moloko.consolecrudapp.controller;

import com.moloko.consolecrudapp.model.Developer;
import com.moloko.consolecrudapp.model.Skill;
import com.moloko.consolecrudapp.repository.JavaIODeveloperRepositoryImpl;
import com.moloko.consolecrudapp.repository.JavaIOSkillRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jack Milk
 */
public class DeveloperController {
    private static JavaIODeveloperRepositoryImpl developer = JavaIODeveloperRepositoryImpl.getDeveloperRepo();
    private static JavaIOSkillRepositoryImpl skillRepo = JavaIOSkillRepositoryImpl.getSkillRepo();
    private static int developerId = developer.getLastId() + 1;

    public static void viewAllDevelopers(){
        for (Developer dev: developer.getAll()){
            developerToConsole(dev);
        }
        System.out.println();
    }

    public static void viewDeveloperByID(String id){
        try {
            if (isDigit(id)) {
                Developer dev = developer.getById(Integer.parseInt(id));
                developerToConsole(dev);
            } else {
                System.out.println("Enter a digital designation id");
            }
        } catch (NullPointerException e){
            System.out.println("This ID does not exist");
        }
    }

    public static void save(String firstName, String lastName, List<String> skills){
        List<Skill> skillList = new ArrayList<>();
        for (String sk: skills){
            Skill skill = skillRepo.getById(Integer.parseInt(sk));
            skillList.add(skill);
        }
        Developer newDeveloper = developer.save(new Developer(developerId, firstName, lastName, skillList));
        viewAllDevelopers();
        System.out.println();
    }

    public static void updateDeveloperForId(String id, String firstName, String lastName, List<String> skills){
        try {
            if (isDigit(id)) {
                List<Skill> skillList = new ArrayList<>();
                for (String sk: skills){
                    Skill skill = skillRepo.getById(Integer.parseInt(sk));
                    skillList.add(skill);
                }
                developer.update(new Developer(Integer.parseInt(id), firstName, lastName, skillList));
                viewAllDevelopers();
            } else {
                System.out.println("Enter a digital designation id");
            }
        } catch (NullPointerException e) {
            System.out.println("This ID does not exist");
        }
    }

    public static void deleteDeveloperById(String id){
        try {
            if (isDigit(id)) {
                int devId = Integer.parseInt(id);
                developer.deleteById(devId);
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

    private static void developerToConsole(Developer dev){
        System.out.print("| Developer -> id:"+ dev.getId() + " " + dev.getFirstName() + " " + dev.getLastName());
        System.out.print(" | Skills -> ");
        dev.getSkills().forEach(s -> System.out.print(s.getId() + ":" + s.getName() + " "));
        System.out.println("|");
    }
}
