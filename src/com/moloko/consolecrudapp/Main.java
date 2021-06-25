package com.moloko.consolecrudapp;

import com.moloko.consolecrudapp.model.Developer;
import com.moloko.consolecrudapp.model.Skill;
import com.moloko.consolecrudapp.repository.DeveloperRepositoryImpl;
import com.moloko.consolecrudapp.repository.SkillRepositoryImpl;
import com.moloko.consolecrudapp.repository.TeamRepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Jack Milk
 */
public class Main {
    public static void main(String[] args) {

        SkillRepositoryImpl skill = new SkillRepositoryImpl();
        DeveloperRepositoryImpl developer = new DeveloperRepositoryImpl();
        TeamRepositoryImpl team = new TeamRepositoryImpl();

        skill.createAndSaveSkill("Ruby");
        skill.createAndSaveSkill("PHP");
        skill.createAndSaveSkill("Java");
        skill.createAndSaveSkill("Python");


        developer.createAndSave(new String[]{"John", "Doe"}, skill.skills.subList(0, 2));
        developer.createAndSave(new String[] {"Mike", "Kue"}, skill.skills.subList(2, skill.skills.size()));



        team.createAndSave(new String[] {"BackEnd"}, developer.developers);
        team.deleteFromId(1);

        System.out.println(team.getAll());
        System.out.println("=========================");
        System.out.println(team.teams);

    }
}
