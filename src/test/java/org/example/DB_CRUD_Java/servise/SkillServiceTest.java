package org.example.DB_CRUD_Java.servise;

import org.example.DB_CRUD_Java.model.Skill;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SkillServiceTest {
    @Mock
    private SkillService skillService;
    private Skill skill1;
    private Skill skill2;

    @Test
    void test_getAll() {
        skillService = mock(SkillService.class);
        skill1 = new Skill(1l, "js");
        skill2 = new Skill(2l, "java");
        List<Skill> skillList = new ArrayList<>(Arrays.asList(skill1, skill2));
        when(skillService.getAll()).thenReturn(skillList);

        List<Skill> skillsListTest = skillService.getAll();
        assertEquals(2, skillsListTest.size());
        assertNotNull(skillsListTest);
    }

    @Test
    void test_getById() {
        skillService = mock(SkillService.class);
        skill1 = new Skill(1l, "js");
        skill2 = new Skill(2l, "java");
        List<Skill> skillList = new ArrayList<>(Arrays.asList(skill1, skill2));
        when(skillService.getById(1l)).thenReturn(skill1);
        assertEquals("js", skill1.getName());
    }

    @Test
    void test_save() {
        skillService = mock(SkillService.class);
        skill1 = new Skill(1l, "js");
        skill2 = new Skill(2l, "java");
        List<Skill> skillList = new ArrayList<>(Arrays.asList(skill1, skill2));
        when(skillService.save(skill1,1L)).thenReturn(skill1);
        Skill testSkill = skillService.save(skill1,1L);
        assertNotNull(skill1);
        assertEquals("js", testSkill.getName());
    }

    @Test
    void test_update() {
        skillService = mock(SkillService.class);
        skill1 = new Skill(1l, "js");
        skill2 = new Skill(2l, "java");
        List<Skill> skillList = new ArrayList<>(Arrays.asList(skill1, skill2));
        Skill testSkill =new Skill(2l, "py");
        when(skillService.update(testSkill,1L)).thenReturn(testSkill);
    }

    @Test
    void test_deleteById() {
        skillService = mock(SkillService.class);
        skillService.deleteById(any());
        verify(skillService).deleteById(any());
    }

}
