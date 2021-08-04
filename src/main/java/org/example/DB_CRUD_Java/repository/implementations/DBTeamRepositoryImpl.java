package org.example.DB_CRUD_Java.repository.implementations;


import com.sun.istack.internal.Nullable;
import org.example.DB_CRUD_Java.model.Team;
import org.example.DB_CRUD_Java.model.TeamStatus;
import org.example.DB_CRUD_Java.repository.TeamRepository;
import org.example.DB_CRUD_Java.util.ConnectUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBTeamRepositoryImpl implements TeamRepository {
    private final DBDeveloperRepositoryImpl dbDeveloperRepository = new DBDeveloperRepositoryImpl();
    private final String SQLGetById = "select * from team  where id_team = ?";
    private final String SQLGetAll = "select * from team";
    private final String SQLDeleteById = "DELETE FROM team WHERE id_team = ?";
    private final String SQLUpdate = "update team set name = ?, team_status = ? where id_team = ?";
    private final String SQLSave = "INSERT INTO team (name,team_status) values (?,?)";

    public Team getById(Long id) {
        Team team = new Team();
        try (PreparedStatement preparedStatement = ConnectUtil.getPreparedStatement(SQLGetById)) {
            preparedStatement.setInt(1, Math.toIntExact(id));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                team.setId(resultSet.getLong(1));
                team.setName(resultSet.getString(2));
                team.setTeamStatus(TeamStatus.valueOf(resultSet.getString(3)));
                team.setDevelopers(dbDeveloperRepository.getListDevelopersByTeamId(team.getId()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return team;
    }

    public List<Team> getAll() {
        List<Team> teamList = new ArrayList<>();
        Team team = null;
        try (PreparedStatement preparedStatement = ConnectUtil.getPreparedStatement(SQLGetAll)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                team = new Team();
                team.setId(resultSet.getLong(1));
                team.setName(resultSet.getString(2));
                team.setTeamStatus(TeamStatus.valueOf(resultSet.getString(3)));
                team.setDevelopers(dbDeveloperRepository.getListDevelopersByTeamId(team.getId()));
                teamList.add(team);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teamList;
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
