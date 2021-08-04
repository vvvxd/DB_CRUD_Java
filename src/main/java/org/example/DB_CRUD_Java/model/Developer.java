package org.example.DB_CRUD_Java.model;

import java.util.ArrayList;
import java.util.List;

public class Developer {
    private Long id;
    private String firstName;
    private String lastName;
    private List<Skill> skills;


    public Developer(Long id, String firstName, String lastName, List<Skill> skills) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.skills = skills;
    }

    public Developer(String firstName, String lastName, List<Skill> skills) {
        this.id = 0L;
        this.firstName = firstName;
        this.lastName = lastName;
        this.skills = skills;
    }

    public Developer() {
        this.id = 0L;
        this.firstName = "";
        this.lastName = "";
        this.skills = new ArrayList<Skill>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", skills=" + skills +
                '}';
    }
}
