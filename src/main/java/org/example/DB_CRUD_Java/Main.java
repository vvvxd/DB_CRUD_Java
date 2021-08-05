package org.example.DB_CRUD_Java;


import org.example.DB_CRUD_Java.model.Developer;
import org.example.DB_CRUD_Java.model.Skill;
import org.example.DB_CRUD_Java.repository.DeveloperRepository;
import org.example.DB_CRUD_Java.repository.implementations.DBDeveloperRepositoryImpl;
import org.example.DB_CRUD_Java.repository.implementations.DBTeamRepositoryImpl;
import org.example.DB_CRUD_Java.view.MainView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        MainView mainView = new MainView();
        mainView.startApp();

    }
}
