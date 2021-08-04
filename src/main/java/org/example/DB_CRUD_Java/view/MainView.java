package org.example.DB_CRUD_Java.view;

import java.sql.SQLException;
import java.util.Scanner;

public class MainView {
    private DeveloperView developerView = new DeveloperView();
    private SkillView skillView = new SkillView();
    private TeamView teamView = new TeamView();
    private Scanner scanner;

    public void startApp() throws SQLException {
        mainMenu();
        selectMenu();
    }

    public void mainMenu() {
        System.out.println("\n*** MAIN MENU ***");
        System.out.println(" ================================= ");
        System.out.println("Choose next action:");
        System.out.println("1. TEAM\n2. DEVELOPER\n3. SKILL\n4. EXIT");
    }

    private void selectMenu() throws SQLException {
        String input;
        scanner = new Scanner(System.in);
        while (true) {
            input = scanner.nextLine();
            if (input.equals("1")) {
                teamView.runner();
            } else if (input.equals("2")) {
                developerView.runner();
            } else if (input.equals("3")) {
                skillView.runner();
            } else if (input.equals("4")) {
                System.out.println("*** Thanks for using our program ***");
                break;
            } else {
                System.out.println("Please, enter numbers from 1 to 4");
                mainMenu();
            }
        }
    }

}
