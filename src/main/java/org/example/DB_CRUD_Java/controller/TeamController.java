package org.example.DB_CRUD_Java.controller;

import org.example.DB_CRUD_Java.model.Developer;
import org.example.DB_CRUD_Java.model.Team;
import org.example.DB_CRUD_Java.model.TeamStatus;
import org.example.DB_CRUD_Java.repository.TeamRepository;
import org.example.DB_CRUD_Java.repository.implementations.DBTeamRepositoryImpl;
import org.example.DB_CRUD_Java.servise.TeamService;

import java.util.List;

public class TeamController {
    private final TeamService teamService = new TeamService();

    public Team getById(Long id) {
        return teamService.getById(id);
    }

    public List<Team> getAll() {
        return teamService.getAll();
    }

    public Team save(String name, List<Developer> developers, TeamStatus teamStatus) {
        return teamService.save(new Team(name, developers, teamStatus));
    }

    public Team update(Team s) {
        return teamService.update(s);
    }

    public void deleteById(Long id) {
        teamService.deleteById(id);
    }
}
