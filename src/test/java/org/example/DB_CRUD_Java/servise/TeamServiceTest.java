package org.example.DB_CRUD_Java.servise;

import org.example.DB_CRUD_Java.model.Team;
import org.example.DB_CRUD_Java.model.TeamStatus;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TeamServiceTest {
    @Mock
    private TeamService teamService;
    private Team team1;
    private Team team2;

    @Test
    void test_getAll() {
        teamService = mock(TeamService.class);
        team1 = new Team(1l,"First",null, TeamStatus.ACTIVE);
        team2 = new Team(2l,"Two",null, TeamStatus.DELETED);
        List<Team> teamList = new ArrayList<>(Arrays.asList(team1, team2));
        when(teamService.getAll()).thenReturn(teamList);

        List<Team> teamsListTest = teamService.getAll();
        assertEquals(2, teamsListTest.size());
        assertNotNull(teamsListTest);
    }

    @Test
    void test_getById() {
        teamService = mock(TeamService.class);
        team1 = new Team(1l,"First",null, TeamStatus.ACTIVE);
        team2 = new Team(2l,"Two",null, TeamStatus.DELETED);
        List<Team> teamList = new ArrayList<>(Arrays.asList(team1, team2));
        when(teamService.getById(1l)).thenReturn(team1);
        assertEquals("First", team1.getName());
    }

    @Test
    void test_save() {
        teamService = mock(TeamService.class);
        team1 = new Team(1l,"First",null, TeamStatus.ACTIVE);
        team2 = new Team(2l,"Two",null, TeamStatus.DELETED);
        List<Team> teamList = new ArrayList<>(Arrays.asList(team1, team2));
        when(teamService.save(team1)).thenReturn(team1);
        Team testTeam = teamService.save(team1);
        assertNotNull(team1);
        assertEquals("First", testTeam.getName());
    }

    @Test
    void test_update() {
        teamService = mock(TeamService.class);
        team1 = new Team(1l,"First",null, TeamStatus.ACTIVE);
        team2 = new Team(2l,"Two",null, TeamStatus.DELETED);
        List<Team> teamList = new ArrayList<>(Arrays.asList(team1, team2));
        Team testTeam =new Team(2l,"Red",null, TeamStatus.DELETED);
        when(teamService.update(testTeam)).thenReturn(testTeam);
    }

    @Test
    void test_deleteById() {
        teamService = mock(TeamService.class);
        teamService.deleteById(any());
        verify(teamService).deleteById(any());
    }

}
