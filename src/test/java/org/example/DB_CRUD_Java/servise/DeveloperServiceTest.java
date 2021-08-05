package org.example.DB_CRUD_Java.servise;

import org.example.DB_CRUD_Java.model.Developer;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DeveloperServiceTest {
    @Mock
    private DeveloperService developerService;
    private Developer developer1;
    private Developer developer2;

    @Test
    void test_getAll() throws SQLException {
        developerService = mock(DeveloperService.class);
        developer1 = new Developer(1l, "Ivan","Ivanov",null);
        developer2 = new Developer(2l, "Egor","Egorov",null);
        List<Developer> developerList = new ArrayList<>(Arrays.asList(developer1, developer2));
        when(developerService.getAll()).thenReturn(developerList);

        List<Developer> developersListTest = developerService.getAll();
        assertEquals(2, developersListTest.size());
        assertNotNull(developersListTest);
    }

    @Test
    void test_getById() {
        developerService = mock(DeveloperService.class);
        developer1 = new Developer(1l, "Ivan","Ivanov",null);
        developer2 = new Developer(2l, "Egor","Egorov",null);
        List<Developer> developerList = new ArrayList<>(Arrays.asList(developer1, developer2));
        when(developerService.getById(1l)).thenReturn(developer1);
        assertEquals("Ivan", developer1.getFirstName());
    }

    @Test
    void test_save() {
        developerService = mock(DeveloperService.class);
        developer1 = new Developer(1l, "Ivan","Ivanov",null);
        developer2 = new Developer(2l, "Egor","Egorov",null);
        List<Developer> developerList = new ArrayList<>(Arrays.asList(developer1, developer2));
        when(developerService.save(developer1,1L)).thenReturn(developer1);
        Developer testDeveloper = developerService.save(developer1,1L);
        assertNotNull(developer1);
        assertEquals("Ivan", testDeveloper.getFirstName());
    }

    @Test
    void test_update() {
        developerService = mock(DeveloperService.class);
        developer1 = new Developer(1l, "Ivan","Ivanov",null);
        developer2 = new Developer(2l, "Egor","Egorov",null);
        List<Developer> developerList = new ArrayList<>(Arrays.asList(developer1, developer2));
        Developer testDeveloper =new Developer(2l, "Maik","Egorov",null);
        when(developerService.update(testDeveloper,1L)).thenReturn(testDeveloper);
    }

    @Test
    void test_deleteById() {
        developerService = mock(DeveloperService.class);
        developerService.deleteById(any());
        verify(developerService).deleteById(any());
    }

}
