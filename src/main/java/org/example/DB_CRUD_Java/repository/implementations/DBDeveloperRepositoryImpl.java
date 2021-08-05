package org.example.DB_CRUD_Java.repository.implementations;


import org.example.DB_CRUD_Java.model.Developer;
import org.example.DB_CRUD_Java.model.Skill;
import org.example.DB_CRUD_Java.model.Team;
import org.example.DB_CRUD_Java.model.TeamStatus;
import org.example.DB_CRUD_Java.repository.DeveloperRepository;
import org.example.DB_CRUD_Java.util.ConnectUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBDeveloperRepositoryImpl implements DeveloperRepository {
    private final DBSkillRepositoryImpl dbSkillRepository = new DBSkillRepositoryImpl();
    private final String SQLGetAll = "select * from developer d left join skill s on s.id_developer = d.id_developer";
    private final String SQLSave = "INSERT INTO developer (firstname,lastname,id_team) values (?,?,?)";
    private final String SQLDeleteById = "DELETE FROM developer WHERE id_developer = ?";
    private final String SQLMaxId = "SELECT id_developer FROM developer WHERE id_developer = (SELECT max(id_developer) FROM developer)";
    private final String SQLUpdate = "update developer set firstname = ?, lastname = ?, id_team =? where id_developer = ?";
    private final String SQLDeveloperByTeamId = "select * from developer d where d.id_team = ?";

    private List<Developer> getListFromDB() {
        List<Developer> developerList = new ArrayList<>();
        Developer developer = null;
        List<Skill> skillList = null;
        Skill skill = null;

        try (PreparedStatement preparedStatement = ConnectUtil.getPreparedStatement(SQLGetAll)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            int prevDevId = -1;
            int prevSkillId = -1;

            while (resultSet.next()) {
                if (resultSet.getInt(1) != prevDevId && resultSet.getInt(1)!= 0) {
                    prevDevId = resultSet.getInt(1);
                    skillList = new ArrayList<>();
                    developer = new Developer();
                    developer.setId(resultSet.getLong(1));
                    developer.setFirstName(resultSet.getString(2));
                    developer.setLastName(resultSet.getString(3));
                    developer.setSkills(skillList);
                    developerList.add(developer);

                }
                if (resultSet.getInt(5) != prevSkillId && resultSet.getInt(5)!= 0) {
                    prevSkillId = resultSet.getInt(5);
                    skill = new Skill();
                    skill.setId(resultSet.getLong(5));
                    skill.setName(resultSet.getString(6));
                    skillList.add(skill);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developerList;
    }

    public Developer getById(Long id) {
      return getListFromDB().stream().filter(s->s.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Developer> getAll() {
        return getListFromDB();
    }

    public Developer update(Developer s,Long team) {
        try (PreparedStatement preparedStatement = ConnectUtil.getPreparedStatement(SQLUpdate)) {
            preparedStatement.setString(1, s.getFirstName());
            preparedStatement.setString(2, s.getLastName());
            preparedStatement.setInt(3, Math.toIntExact(team));
            preparedStatement.setInt(4, Math.toIntExact(s.getId()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbSkillRepository.updateDeveloperSkills(s);
        return s;
    }

    @Override
    public Developer save(Developer s) {
        return null;
    }

    @Override
    public Developer update(Developer s) {
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


    public Developer save(Developer s,Long team) {
        try (PreparedStatement preparedStatement = ConnectUtil.getPreparedStatement(SQLSave)) {
            preparedStatement.setString(1, s.getFirstName());
            preparedStatement.setString(2, s.getLastName());
            preparedStatement.setInt(3, Math.toIntExact(team));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        insertDeveloperSkills(s);
        return s;
    }


    private void insertDeveloperSkills(Developer s) {
        try (PreparedStatement preparedStatement = ConnectUtil.getPreparedStatement(SQLMaxId)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                dbSkillRepository.saveDeveloperSills(s, resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Developer> getListDevelopersByTeamId(Long id) {
        List<Developer> developers = new ArrayList<>();
        Developer developer = null;
        try (PreparedStatement preparedStatement = ConnectUtil.getPreparedStatement(SQLDeveloperByTeamId)) {
            preparedStatement.setInt(1, Math.toIntExact(id));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                developer = new Developer();
                developer.setId(resultSet.getLong(1));
                developer.setFirstName(resultSet.getString(2));
                developer.setLastName(resultSet.getString(3));
                developer.setSkills(dbSkillRepository.getListSkillsByDeveloperId(developer.getId()));
                developers.add(developer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developers;
    }

    public void updateTeamDevelopers(Team s) {
        for (Developer dev: s.getDevelopers()) {
            update(dev,s.getId());
        }
    }

    public void insertTeamDevelopers(Team s) {
        for (Developer dev: s.getDevelopers()) {
            save(dev,s.getId());
        }
    }
}
