package org.example.DB_CRUD_Java.repository;



import org.example.DB_CRUD_Java.model.Skill;

import java.util.List;

public interface SkillRepository extends GenericRepository<Skill, Long, Long> {
    Skill getById(Long id);

    List<Skill> getAll();

    Skill save(Skill s,Long q);

    Skill update(Skill s,Long q);

    void deleteById(Long id);
}
