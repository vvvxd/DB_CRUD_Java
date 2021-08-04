package org.example.DB_CRUD_Java.view;

import org.example.DB_CRUD_Java.controller.SkillController;
import org.example.DB_CRUD_Java.model.Skill;

import java.util.List;
import java.util.Scanner;

public class SkillView {
    private final SkillController skillController = new SkillController();
    Scanner scanner = new Scanner(System.in);

    public void save() {
        System.out.println("Enter the name of the skill: ");
        String data = scanner.nextLine();
        System.out.println("Enter the developer of the skill: ");
        DeveloperView  developerView = new DeveloperView();
        developerView.getAll();
        Long developer =Long.parseLong(scanner.nextLine());
        if (!data.isEmpty()) {
            Skill skill = skillController.save(data,developer);
            System.out.println(skill.toString());
        }
    }

    public void getAll() {
        try {
            List<Skill> skillList;
            skillList = skillController.getAll();
            if (skillList.isEmpty()) {
                return;
            }
            for (Skill i : skillList) {
                System.out.println(i.toString());
            }
        } catch (NumberFormatException e) {
            System.out.println("There are no skills.");
        }

    }

    public void getById(Long id) {
        try {
            List<Skill> skillList;
            Skill skill;
            skillList = skillController.getAll();
            if (skillList.isEmpty()) {
                return;
            }
            skill = skillController.getById(id);
            System.out.println(skill.toString());
        } catch (NumberFormatException e) {
            System.out.println("There are no skills.");
        }
    }

    public void update(Long id) {
        try {
            Skill skill;
            System.out.println("Write new name of skill: ");
            String data = scanner.nextLine();
            System.out.println("Enter the developer of the skill: ");
            DeveloperView  developerView = new DeveloperView();
            developerView.getAll();
            Long developer =Long.parseLong(scanner.nextLine());
            skill = skillController.update(new Skill(id, data),developer);
            System.out.println(skill.toString());
        } catch (NumberFormatException exception) {
            System.out.println("Wrong id or name.");
        }
    }

    public void deleteById(Long id) {
        try {
            List<Skill> skillList;
            skillList = skillController.getAll();
            if (skillList.isEmpty()) {
                return;
            }
            skillController.deleteById(id);
        } catch (NumberFormatException e) {
            System.out.println("Write correct id.");
        }
    }

    public void runner() {
        menu();
        selectMenu();
    }

    private void menu() {
        System.out.println("\n*** SKILL MENU ***");
        System.out.println(" ================================= ");
        System.out.println("Choose next action:");
        System.out.println("1. Save\n2. Get all\n3. Get by id\n4. Update\n5. DeleteById\n6. RETURN");
    }

    private void selectMenu() {
        String input;
        scanner = new Scanner(System.in);
        while (true) {
            input = scanner.nextLine();
            if (input.equals("1")) {
                save();
            } else if (input.equals("2")) {
                getAll();
            } else if (input.equals("3")) {
                System.out.println("Enter id");
                Long num = Long.parseLong(scanner.nextLine());
                getById(num);
            } else if (input.equals("4")) {
                System.out.println("Enter id");
                Long num = Long.parseLong(scanner.nextLine());
                update(num);
            } else if (input.equals("5")) {
                System.out.println("Enter id");
                Long num = Long.parseLong(scanner.nextLine());
                deleteById(num);
            } else if (input.equals("6")) {
                break;
            } else {
                System.out.println("Please, enter numbers from 1 to 6");
                menu();
            }
        }
    }
}
