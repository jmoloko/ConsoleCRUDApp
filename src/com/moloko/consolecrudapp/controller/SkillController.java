package com.moloko.consolecrudapp.controller;

import com.moloko.consolecrudapp.model.Skill;
import com.moloko.consolecrudapp.repository.JavaIOSkillRepositoryImpl;

/**
 * @author Jack Milk
 */
public class SkillController {
    private static JavaIOSkillRepositoryImpl skill = JavaIOSkillRepositoryImpl.getSkillRepo();
    private static int skillId = skill.getLastId() + 1;

    public static void viewAllSkills(){
        System.out.print("| Skills -> ");
        skill.getAll().forEach(s -> System.out.print(s.getId() + ":" + s.getName() + " "));
        System.out.println(" |");
    }

    public static void save(String newSkillName){
        Skill newSkill = skill.save(new Skill(skillId, newSkillName));
        viewAllSkills();
        System.out.println();
    }

    public static void viewSkillByID(String id){
        try {
            if (isDigit(id))
                System.out.println(skill.getById(Integer.parseInt(id)).getName());
            else
                System.out.println("Enter a digital designation id");
        } catch (NullPointerException e){
            System.out.println("This ID does not exist");
        }
    }

    public static void updateSkillForId(String id, String newName){
        try {
            if (isDigit(id)) {
                int skillId = Integer.parseInt(id);
                skill.update(new Skill(skillId, newName));
                viewAllSkills();
            } else {
                System.out.println("Enter a digital designation id");
            }
        } catch (NullPointerException e) {
            System.out.println("This ID does not exist");
        }

    }

    public static void deleteSkillById(String id){
        try {
            if (isDigit(id)) {
                int skillId = Integer.parseInt(id);
                skill.deleteById(skillId);
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
}
