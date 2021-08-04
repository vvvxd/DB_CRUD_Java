package org.example.DB_CRUD_Java.servise;

import org.example.DB_CRUD_Java.model.Developer;
import org.example.DB_CRUD_Java.model.Skill;
import org.example.DB_CRUD_Java.repository.DeveloperRepository;
import org.example.DB_CRUD_Java.repository.implementations.DBDeveloperRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class DeveloperService {
    private final org.example.DB_CRUD_Java.repository.DeveloperRepository DeveloperRepository = new DBDeveloperRepositoryImpl();

    public Developer getById(Long id) {
        return DeveloperRepository.getById(id);
    }

    public List<Developer> getAll() throws SQLException {
        return DeveloperRepository.getAll();
    }

    public Developer save(Developer dev,Long team) {
        return DeveloperRepository.save(dev,team);
    }

    public Developer update(Developer s, Long team) {
        return DeveloperRepository.update(s,team);
    }

    public void deleteById(Long id) {
        DeveloperRepository.deleteById(id);
    }
}
