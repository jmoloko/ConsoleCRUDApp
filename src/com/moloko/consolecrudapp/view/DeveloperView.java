package com.moloko.consolecrudapp.view;

import com.moloko.consolecrudapp.controller.DeveloperController;
import com.moloko.consolecrudapp.controller.SkillController;
import com.moloko.consolecrudapp.repository.JavaIOSkillRepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.moloko.consolecrudapp.view.MainView.mainView;

/**
 * @author Jack Milk
 */
public class DeveloperView {

    private static JavaIOSkillRepositoryImpl skillRepo = JavaIOSkillRepositoryImpl.getSkillRepo();

    public static void developerMenu(){
        System.out.println();
        System.out.println("        *******************************       ");
        System.out.println("        | Command Menu from Developer |       ");
        System.out.println("        *******************************       ");
        System.out.println("        | Options:                    |       ");
        System.out.println("        |   1. All Developers         |       ");
        System.out.println("        |   2. Get Developer by Id    |       ");
        System.out.println("        |   3. Create Developer       |       ");
        System.out.println("        |   4. Update Developer by Id |       ");
        System.out.println("        |   5. Delete Developer by Id |       ");
        System.out.println("        |   M. Main menu              |       ");
        System.out.println("        |   E. Exit                   |       ");
        System.out.println("        *******************************       ");


        String choice = "";

        while(!(choice.toLowerCase().equals("e"))) {
            System.out.print("          Select option: ");
            choice = new Scanner(System.in).next();
            switch (choice.toLowerCase()) {
                case "1":
                    System.out.println("All Developers: ");
                    DeveloperController.viewAllDevelopers();
                    break;
                case "2":
//                    SkillController.viewAllSkills();
                    System.out.print("Developer By Id (enter id): ");
                    String idDeveloperForView = new Scanner(System.in).next();
                    DeveloperController.viewDeveloperByID(idDeveloperForView);
                    break;
                case "3":
                    System.out.print("Enter new  First Name: ");
                    String firstName = new Scanner(System.in).next();
                    System.out.print("Enter new  Last Name: ");
                    String lastName = new Scanner(System.in).next();
                    List<String> skillId = getSkills();
                    DeveloperController.save(firstName, lastName, skillId);
                    break;
                case "4":
                    DeveloperController.viewAllDevelopers();
                    System.out.print("Enter Id Developer for update: ");
                    String idDeveloperForUpdate = new Scanner(System.in).next();
                    System.out.print("Enter new First Name: ");
                    String newFirstName = new Scanner(System.in).next();
                    System.out.print("Enter new Lat Name: ");
                    String newLastName = new Scanner(System.in).next();
                    List<String> skillIdForUpdate = getSkills();
                    DeveloperController.updateDeveloperForId(idDeveloperForUpdate, newFirstName, newLastName, skillIdForUpdate);
                    break;
                case "5":
                    DeveloperController.viewAllDevelopers();
                    System.out.print("Delete Developer By Id (enter id): ");
                    String idDeveloperForDelete = new Scanner(System.in).next();
                    DeveloperController.deleteDeveloperById(idDeveloperForDelete);
                    DeveloperController.viewAllDevelopers();
                    break;
                case "m":
                    mainView();
                    break;
                case "e":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid selection");
                    developerMenu();
                    break;
            }
        }
    }

    public static List<String> getSkills(){
        List<String> skillId = new ArrayList<>();
        SkillController.viewAllSkills();
        while (true){
            System.out.println("Enter Skill id: (Write 'S' to stop)");
            String skill = new Scanner(System.in).next();
            if (skill.equalsIgnoreCase("s")) {
                break;
            }
            if(!skillRepo.getAllId().contains(Integer.parseInt(skill))){
                System.out.println("This ID does not exist");
                continue;
            }
            skillId.add(skill);
        }
        return skillId;
    }

}
