package com.moloko.consolecrudapp.view;

import com.moloko.consolecrudapp.controller.SkillController;

import java.util.Scanner;

/**
 * @author Jack Milk
 */
public class MainView {

    public static void mainView(){

        System.out.println();
        System.out.println("        *****************************       ");
        System.out.println("        |       Command Menu        |       ");
        System.out.println("        *****************************       ");
        System.out.println("        | Options:                  |       ");
        System.out.println("        |       1. Skills           |       ");
        System.out.println("        |       2. Developers       |       ");
        System.out.println("        |       3. Teams            |       ");
        System.out.println("        |       E. Exit             |       ");
        System.out.println("        *****************************       ");

        System.out.print("          Select option: ");
        String choice = new Scanner(System.in).next();

        while(!(choice.equals("1") ||
                choice.equals("2") ||
                choice.equals("3") ||
                choice.toLowerCase().equals("e") ||
                choice.toLowerCase().equals("m")))
        {
            System.out.print("Invalid selection \n");
            System.out.print("Select option again:");
            choice = new Scanner(System.in).next();
        }

        switch (choice.toLowerCase()) {
            case "1":
                SkillView.skillMenu();
                break;
            case "2":
                DeveloperView.developerMenu();
                break;
            case "3":
                TeamView.teamMenu();
                break;
            case "m":
                mainView();
                break;
            case "e":
                System.exit(0);
                break;
            default:
                System.out.println("Invalid selection");
                mainView();
                break;
        }
    }

}
