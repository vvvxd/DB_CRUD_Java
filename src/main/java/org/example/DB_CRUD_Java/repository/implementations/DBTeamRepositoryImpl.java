package org.example.DB_CRUD_Java.repository.implementations;


import com.sun.istack.internal.Nullable;
import org.example.DB_CRUD_Java.model.Developer;
import org.example.DB_CRUD_Java.model.Skill;
import org.example.DB_CRUD_Java.model.Team;
import org.example.DB_CRUD_Java.model.TeamStatus;
import org.example.DB_CRUD_Java.repository.TeamRepository;
import org.example.DB_CRUD_Java.util.ConnectUtil;
import org.example.DB_CRUD_Java.view.DeveloperView;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBTeamRepositoryImpl implements TeamRepository {
    private final DBDeveloperRepositoryImpl dbDeveloperRepository = new DBDeveloperRepositoryImpl();
    private final String SQLGetAll = "select * from team t left join developer d on d.id_team = t.id_team left join skill s on s.id_developer = d.id_developer";
    private final String SQLDeleteById = "DELETE FROM team WHERE id_team = ?";
    private final String SQLUpdate = "update team set name = ?, team_status = ? where id_team = ?";
    private final String SQLSave = "INSERT INTO team (name,team_status) values (?,?)";

    private List<Team> getListFromDB() {
        List<Team> teamList = new ArrayList<>();
        Team team = null;
        List<Developer> developerList = null;
        Developer developer = null;
        List<Skill> skillList = null;
        Skill skill = null;

        try (PreparedStatement preparedStatement = ConnectUtil.getPreparedStatement(SQLGetAll)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            int prevTeamId = -1;
            int prevDevId = -1;
            int prevSkillId = -1;

            while (resultSet.next()) {
                if (resultSet.getInt(1) != prevTeamId) {
                    prevTeamId = resultSet.getInt(1);
                    developerList = new ArrayList<>();
                    team = new Team();
                    team.setId(resultSet.getLong(1));
                    team.setName(resultSet.getString(2));
                    team.setTeamStatus(TeamStatus.valueOf(resultSet.getString(3)));
                    team.setDevelopers(developerList);
                    teamList.add(team);
                }
                if (resultSet.getInt(4) != prevDevId && resultSet.getInt(4)!= 0) {
                    prevDevId = resultSet.getInt(4);
                    skillList = new ArrayList<>();
                    developer = new Developer();
                    developer.setId(resultSet.getLong(4));
                    developer.setFirstName(resultSet.getString(5));
                    developer.setLastName(resultSet.getString(6));
                    developer.setSkills(skillList);
                    developerList.add(developer);

                }
                if (resultSet.getInt(7) != prevSkillId && resultSet.getInt(7)!= 0) {
                    prevSkillId = resultSet.getInt(4);
                    skill = new Skill();
                    skill.setId(resultSet.getLong(7));
                    skill.setName(resultSet.getString(8));
                    skillList.add(skill);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teamList;
    }

    public List<Team> getAll() {
        return getListFromDB();
    }

    public Team getById(Long id) {
        return getListFromDB().stream().filter(s->s.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Team save(Team s, Long q) {
        return null;
    }

    @Override
    public Team update(Team s, Long q) {
        return null;
    }

    public Team save(Team s) {
        try (PreparedStatement preparedStatement = ConnectUtil.getPreparedStatement(SQLSave)) {
            preparedStatement.setString(1, s.getName());
            preparedStatement.setString(2, s.getTeamStatus().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbDeveloperRepository.insertTeamDevelopers(s);
        return s;
    }

    public Team update(Team s) {
        try (PreparedStatement preparedStatement = ConnectUtil.getPreparedStatement(SQLUpdate)) {
            preparedStatement.setString(1, s.getName());
            preparedStatement.setString(2, s.getTeamStatus().toString());
            preparedStatement.setInt(3, Math.toIntExact(s.getId()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbDeveloperRepository.updateTeamDevelopers(s);
        return s;
    }

    public void deleteById(Long id) {
        try (PreparedStatement preparedStatement = ConnectUtil.getPreparedStatement(SQLDeleteById)) {
            preparedStatement.setInt(1, Math.toIntExact(id));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
