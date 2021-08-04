package org.example.DB_CRUD_Java.servise;

import org.example.DB_CRUD_Java.model.Skill;
import org.example.DB_CRUD_Java.repository.SkillRepository;
import org.example.DB_CRUD_Java.repository.implementations.DBSkillRepositoryImpl;

import java.util.List;

public class SkillService {
    private final SkillRepository skillRepository= new DBSkillRepositoryImpl();

    public Skill getById(Long id) {
        return skillRepository.getById(id);
    }

    public List<Skill> getAll() {
        return skillRepository.getAll();
    }

    public Skill save(Skill skill, Long developer) {
        return skillRepository.save(skill,developer);
    }

    public Skill update(Skill s, Long developer) {
        return skillRepository.update(s,developer);
    }

    public void deleteById(Long id) {
        skillRepository.deleteById(id);
    }
}
