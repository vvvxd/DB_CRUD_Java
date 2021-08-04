package org.example.DB_CRUD_Java.repository;

import org.example.DB_CRUD_Java.model.Developer;

import java.sql.SQLException;
import java.util.List;

public interface DeveloperRepository extends GenericRepository<Developer,Long, Long>{
    Developer getById(Long id);

    List<Developer> getAll() throws SQLException;

    Developer save(Developer s,Long team);

    void deleteById(Long id);

    Developer update(Developer s, Long team);
}
