package org.example.DB_CRUD_Java.model;

public class Skill {
    private Long id;
    private String name;

    public Skill(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public Skill( String name) {
        this.id = 0L;
        this.name = name;
    }

    public Skill() {
        this.id = 0L;
        this.name = "";
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
