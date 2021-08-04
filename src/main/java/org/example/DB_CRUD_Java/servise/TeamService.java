package org.example.DB_CRUD_Java.servise;

import org.example.DB_CRUD_Java.model.Developer;
import org.example.DB_CRUD_Java.model.Team;
import org.example.DB_CRUD_Java.model.TeamStatus;
import org.example.DB_CRUD_Java.repository.TeamRepository;
import org.example.DB_CRUD_Java.repository.implementations.DBTeamRepositoryImpl;

import java.util.List;

public class TeamService {
    private final TeamRepository TeamRepository = new DBTeamRepositoryImpl();

    public Team getById(Long id) {
        return TeamRepository.getById(id);
    }

    public List<Team> getAll() {
        return TeamRepository.getAll();
    }

    public Team save(Team team) {
        return TeamRepository.save(team);
    }

    public Team update(Team s) {
        return TeamRepository.update(s);
    }

    public void deleteById(Long id) {
        TeamRepository.deleteById(id);
    }
}
