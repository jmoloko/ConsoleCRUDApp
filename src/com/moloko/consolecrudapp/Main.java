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

        Scanner scan = new Scanner(System.in);

//        Skill html = new Skill("HTML");
//        Skill css = new Skill("CSS");
//        Skill php = new Skill("PHP");
//        Skill js = new Skill("JavaScript");
//        Skill mysql = new Skill("MySQL");
//
//        ArrayList<Skill> phpSkills = new ArrayList<>();
//        phpSkills.add(php);
//        phpSkills.add(html);
//        phpSkills.add(css);
//
//        ArrayList<Skill> jsSkills = new ArrayList<>();
//        jsSkills.add(js);
//        jsSkills.add(css);
//        jsSkills.add(html);
//
//
//        Developer devPhp = new Developer("John", "Doe", phpSkills);
//        devPhp.setSkill(mysql);
//        Developer devJS = new Developer("Mike", "Route", jsSkills);
//
//        System.out.println(devPhp);
//        System.out.println(devJS);
//
//        System.out.print("Rename Skill " + html.getName());
//        String skillName = scan.next();
//
//        System.out.println(skillName);



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



//
//

//
//        System.out.println("============");

//        skill.allSkillsToConsole();

    }
}
