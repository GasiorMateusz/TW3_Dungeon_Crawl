package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.items.Inventory;
import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlayerDaoJdbc implements PlayerDao {
    private final DataSource dataSource;

    public PlayerDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(PlayerModel player) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO player (player_name, hp, x, y, inventory) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, player.getPlayerName());
            statement.setInt(2, player.getHp());
            statement.setInt(3, player.getX());
            statement.setInt(4, player.getY());
            statement.setString(5, player.getInventory().toString());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            player.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(PlayerModel player) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE player SET hp=?, x=?, y=? WHERE player_name = " + player.getPlayerName() + ";";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            statement.setString(1, player.getPlayerName());
            statement.setInt(2, player.getHp());
            statement.setInt(3, player.getX());
            statement.setInt(4, player.getY());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //    public PlayerModel get(int playerId) {
//        PlayerModel playerModel;
//        try (Connection conn = dataSource.getConnection()) {
//
//            String query = "SELECT * FROM player WHERE id =" + playerId + ";";
//            Statement statement = conn.createStatement();
//            ResultSet resultSet = statement.executeQuery(query);
//            resultSet.next();
//
//            playerModel = new PlayerModel(
//                    resultSet.getString(2),
//                    resultSet.getInt(4),
//                    resultSet.getInt(5)
//            );
//            playerModel.setId(resultSet.getInt(1));
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return playerModel;
//    }
    @Override
    public PlayerModel get(String player_name) {
        PlayerModel playerModel;
        try (Connection conn = dataSource.getConnection()) {

            String query = "SELECT * FROM player WHERE player_name = ?";
            PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, player_name);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            playerModel = new PlayerModel(
                    resultSet.getString(2),
                    resultSet.getInt(4),
                    resultSet.getInt(5)
            );
            playerModel.setId(resultSet.getInt(1));
            playerModel.setHp(resultSet.getInt(3));
//            playerModel.setInventory(resultSet.getString(6));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return playerModel;
    }

    @Override
    public List<PlayerModel> getAll() {
        return null;
    }

    public List<String> getSaveNames() {
        try (Connection conn = dataSource.getConnection()) {

            String query = "SELECT player_name FROM player";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<String> result = new ArrayList<>();
            while (resultSet.next()) {
                PlayerModel playerModel = new PlayerModel(resultSet.getString(1));
                result.add(playerModel.getPlayerName());
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
