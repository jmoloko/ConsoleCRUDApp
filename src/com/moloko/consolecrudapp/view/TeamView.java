package com.moloko.consolecrudapp.view;

import com.moloko.consolecrudapp.controller.DeveloperController;
import com.moloko.consolecrudapp.controller.SkillController;
import com.moloko.consolecrudapp.controller.TeamController;
import com.moloko.consolecrudapp.repository.JavaIODeveloperRepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.moloko.consolecrudapp.view.MainView.mainView;

/**
 * @author Jack Milk
 */
public class TeamView {
    private static JavaIODeveloperRepositoryImpl developerRepo = JavaIODeveloperRepositoryImpl.getDeveloperRepo();

    public static void teamMenu(){
        System.out.println();
        System.out.println("        *******************************       ");
        System.out.println("        |   Command Menu from Team    |       ");
        System.out.println("        *******************************       ");
        System.out.println("        | Options:                    |       ");
        System.out.println("        |     1. All Teams            |       ");
        System.out.println("        |     2. Get Team by Id       |       ");
        System.out.println("        |     3. Create Team          |       ");
        System.out.println("        |     4. Update Team by Id    |       ");
        System.out.println("        |     5. Delete Team by Id    |       ");
        System.out.println("        |     M. Main menu            |       ");
        System.out.println("        |     E. Exit                 |       ");
        System.out.println("        *******************************       ");


        String choice = "";

        while(!(choice.toLowerCase().equals("e"))) {
            System.out.print("          Select option: ");
            choice = new Scanner(System.in).next();
            switch (choice.toLowerCase()) {
                case "1":
                    System.out.println("All Teams: ");
                    TeamController.viewAllTeams();
                    break;
                case "2":
                    TeamController.viewAllTeams();
                    System.out.print("Team By Id (enter id): ");
                    String idTeamForView = new Scanner(System.in).next();
                    TeamController.viewTeamByID(idTeamForView);
                    break;
                case "3":
                    System.out.print("Enter new  Теам Name: ");
                    String name = new Scanner(System.in).next();
                    List<String> developersId = getDevelopers();
                    TeamController.save(name, developersId);
                    break;
                case "4":
                    TeamController.viewAllTeams();
                    System.out.print("Enter Id Team for update: ");
                    String idTeamForUpdate = new Scanner(System.in).next();
                    System.out.print("Enter new Team Name: ");
                    String newTeamName = new Scanner(System.in).next();
                    List<String> developerIdForUpdate = getDevelopers();
                    TeamController.updateTeamForId(idTeamForUpdate, newTeamName, developerIdForUpdate);
                    break;
                case "5":
                    TeamController.viewAllTeams();
                    System.out.print("Delete Team By Id (enter id): ");
                    String idTeamForDelete = new Scanner(System.in).next();
                    TeamController.deleteTeamById(idTeamForDelete);
                    TeamController.viewAllTeams();
                    break;
                case "m":
                    mainView();
                    break;
                case "e":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid selection");
                    teamMenu();
                    break;
            }
        }
    }

    public static List<String> getDevelopers(){
        List<String> developersId = new ArrayList<>();
        DeveloperController.viewAllDevelopers();
        while (true){
            System.out.println("Enter Developer id: (Write 'S' to stop)");
            String developer = new Scanner(System.in).next();
            if (developer.equalsIgnoreCase("s")) {
                break;
            }
            if(!developerRepo.getAllId().contains(Integer.parseInt(developer))){
                System.out.println("This ID does not exist");
                continue;
            }
            developersId.add(developer);
        }
        return developersId;
    }
}
