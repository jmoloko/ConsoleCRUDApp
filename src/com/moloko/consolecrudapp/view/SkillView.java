package com.moloko.consolecrudapp.view;

import com.moloko.consolecrudapp.controller.SkillController;

import java.util.Scanner;

import static com.moloko.consolecrudapp.view.MainView.mainView;

/**
 * @author Jack Milk
 */
public class SkillView {

    public static void skillMenu(){
        System.out.println();
        System.out.println("        *****************************       ");
        System.out.println("        |  Command Menu from Skills |       ");
        System.out.println("        *****************************       ");
        System.out.println("        | Options:                  |       ");
        System.out.println("        |     1. All Skills         |       ");
        System.out.println("        |     2. Get Skill by Id    |       ");
        System.out.println("        |     3. Create Skill       |       ");
        System.out.println("        |     4. Update Skill by Id |       ");
        System.out.println("        |     5. Delete Skill by Id |       ");
        System.out.println("        |     M. Main menu          |       ");
        System.out.println("        |     E. Exit               |       ");
        System.out.println("        *****************************       ");


        String choice = "";

        while(!(choice.toLowerCase().equals("e"))) {
            System.out.print("          Select option: ");
            choice = new Scanner(System.in).next();
            switch (choice.toLowerCase()) {
                case "1":
                    System.out.println("All Skills: ");
                    SkillController.viewAllSkills();
                    break;
                case "2":
                    SkillController.viewAllSkills();
                    System.out.print("Skill By Id (enter id): ");
                    String idSkillForView = new Scanner(System.in).next();
                    SkillController.viewSkillByID(idSkillForView);
                    break;
                case "3":
                    System.out.print("Enter new Skill name: ");
                    String name = new Scanner(System.in).next();
                    SkillController.save(name);
                    break;
                case "4":
                    SkillController.viewAllSkills();
                    System.out.print("Enter Id Skill for update: ");
                    String idSkillForUpdate = new Scanner(System.in).next();
                    System.out.print("Enter new name: ");
                    String newName = new Scanner(System.in).next();
                    SkillController.updateSkillForId(idSkillForUpdate, newName);
                    break;
                case "5":
                    SkillController.viewAllSkills();
                    System.out.print("Delete Skill By Id (enter id): ");
                    String idSkillForDelete = new Scanner(System.in).next();
                    SkillController.deleteSkillById(idSkillForDelete);
                    SkillController.viewAllSkills();
                    break;
                case "m":
                    mainView();
                    break;
                case "e":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid selection");
                    skillMenu();
                    break;
            }
        }
    }

}
