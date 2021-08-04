package org.example.DB_CRUD_Java.model;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private Long id;
    private String name;
    private List<Developer> developers;
    private TeamStatus teamStatus;

    public TeamStatus getTeamStatus() {
        return teamStatus;
    }

    public void setTeamStatus(TeamStatus teamStatus) {
        this.teamStatus = teamStatus;
    }

    public Team(String name, List<Developer> developers, TeamStatus teamStatus) {
        this.id = 0L;
        this.name = name;
        this.developers = developers;
        this.teamStatus = teamStatus;
    }

    public Team(Long id, String name, List<Developer> developers, TeamStatus teamStatus) {
        this.id = id;
        this.name = name;
        this.developers = developers;
        this.teamStatus = teamStatus;
    }

    public Team() {
        this.id = 0L;
        this.name = "";
        this.developers = new ArrayList<Developer>();
        this.teamStatus = TeamStatus.ACTIVE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Developer> developers) {
        this.developers = developers;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", developers=" + developers +
                ", teamStatus=" + teamStatus +
                '}';
    }
}
