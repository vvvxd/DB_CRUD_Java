package org.example.DB_CRUD_Java.controller;


import org.example.DB_CRUD_Java.model.Skill;
import org.example.DB_CRUD_Java.servise.SkillService;

import java.util.List;

public class SkillController {
    private final SkillService skillService= new SkillService();

    public Skill getById(Long id) {
        return skillService.getById(id);
    }

    public List<Skill> getAll() {
        return skillService.getAll();
    }

    public Skill save(String name, Long developer) {
        return skillService.save(new Skill(name),developer);
    }

    public Skill update(Skill s, Long developer) {
        return skillService.update(s,developer);
    }

    public void deleteById(Long id) {
        skillService.deleteById(id);
    }
}
