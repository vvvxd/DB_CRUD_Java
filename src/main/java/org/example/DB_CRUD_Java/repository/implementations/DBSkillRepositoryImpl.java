package org.example.DB_CRUD_Java.repository.implementations;


import org.example.DB_CRUD_Java.model.Developer;
import org.example.DB_CRUD_Java.model.Skill;
import org.example.DB_CRUD_Java.repository.SkillRepository;
import org.example.DB_CRUD_Java.util.ConnectUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBSkillRepositoryImpl implements SkillRepository {
    private final String SQLSkillsByDeveloperId = "select * from skill s where s.id_developer = ?";
    private final String SQLSaveDeveloperSills = "INSERT INTO skill (name,id_developer) values (?,?)";
    private final String SQLUpdateSkill = "update skill set name = ? , id_developer = ? where id_skill = ?";
    private final String SQLGetById = "select * from skill s where s.id_skill = ?";
    private final String SQLDeleteById = "DELETE FROM skill WHERE id_skill = ?";
    private final String SQLGetAll = "select * from skill";

    public Skill getById(Long id) {
        Skill skill = new Skill();
        try (PreparedStatement preparedStatement = ConnectUtil.getPreparedStatement(SQLGetById)) {
            preparedStatement.setInt(1, Math.toIntExact(id));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                skill.setId(resultSet.getLong(1));
                skill.setName(resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skill;
    }

    public List<Skill> getAll() {
        List<Skill> skillList = new ArrayList<>();
        Skill skill = null;
        try (PreparedStatement preparedStatement = ConnectUtil.getPreparedStatement(SQLGetAll)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                skill = new Skill();
                skill.setId(resultSet.getLong(1));
                skill.setName(resultSet.getString(2));
                skillList.add(skill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skillList;
    }

    public Skill save(Skill skill,Long q) {
        try (PreparedStatement preparedStatement = ConnectUtil.getPreparedStatement(SQLSaveDeveloperSills)) {
            preparedStatement.setString(1, skill.getName());
            preparedStatement.setInt(2, Math.toIntExact(q));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skill;
    }

    public Skill update(Skill skill,Long q) {
        try (PreparedStatement preparedStatement = ConnectUtil.getPreparedStatement(SQLUpdateSkill)) {
            preparedStatement.setString(1, skill.getName());
            preparedStatement.setInt(2, Math.toIntExact(q));
            preparedStatement.setInt(3, Math.toIntExact(skill.getId()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skill;
    }

    @Override
    public Skill save(Skill s) {
        return null;
    }

    @Override
    public Skill update(Skill s) {
        return null;
    }

    public void deleteById(Long id) {
        try (PreparedStatement preparedStatement = ConnectUtil.getPreparedStatement(SQLDeleteById)) {
            preparedStatement.setInt(1, Math.toIntExact(id));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void updateDeveloperSkills(Developer s) {
        try (PreparedStatement preparedStatement = ConnectUtil.getPreparedStatement(SQLUpdateSkill)) {
            for (Skill skill : s.getSkills()) {
                preparedStatement.setString(1, skill.getName());
                preparedStatement.setInt(2, Math.toIntExact(skill.getId()));
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void saveDeveloperSills(Developer s, int id) {
        try (PreparedStatement preparedStatement = ConnectUtil.getPreparedStatement(SQLSaveDeveloperSills)) {
            for (Skill skill : s.getSkills()) {
                preparedStatement.setString(1, skill.getName());
                preparedStatement.setInt(2, id);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    List<Skill> getListSkillsByDeveloperId(Long id) {
        List<Skill> skills = new ArrayList<>();
        Skill skill = null;
        try (PreparedStatement preparedStatement = ConnectUtil.getPreparedStatement(SQLSkillsByDeveloperId)) {
            preparedStatement.setInt(1, Math.toIntExact(id));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                skill = new Skill();
                skill.setId(resultSet.getLong(1));
                skill.setName(resultSet.getString(2));
                skills.add(skill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skills;
    }
}
