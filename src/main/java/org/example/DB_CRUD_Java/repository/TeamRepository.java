package org.example.DB_CRUD_Java.repository;


import org.example.DB_CRUD_Java.model.Team;
import java.util.List;

public interface TeamRepository extends GenericRepository<Team,Long,Long> {
    Team getById(Long id);

    List<Team> getAll();

    Team save(Team s,Long q);

    Team update(Team s,Long q);

    Team save(Team s);

    Team update(Team s);

    void deleteById(Long id);
}
