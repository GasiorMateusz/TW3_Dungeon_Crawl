package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class PlayerDaoJdbc implements PlayerDao {
    private final int ADD_INDEX = 1;

    private final DataSource dataSource;

    public PlayerDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(PlayerModel player) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO player (player_name, hp, x, y) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, player.getPlayerName());
            statement.setInt(2, player.getHp());
            statement.setInt(3, player.getX());
            statement.setInt(4, player.getY());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            player.setId(resultSet.getInt(ADD_INDEX));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(PlayerModel player) {
        try (Connection conn = dataSource.getConnection()) {
            String query = "UPDATE player set " +
                    " hp = '" + player.getHp()+
                    "' ,x = '" + player.getX() +
                    "' ,y = '" + player.getY() +
                    "' WHERE player_name = '" + player.getPlayerName() + "'";
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void set(PlayerModel playerModel) {
        try (Connection conn = dataSource.getConnection()) {
            String query = "SELECT COUNT(*) from player";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            if (resultSet.getInt(1) != 0 ){
                update(playerModel);
            }else {
                add(playerModel);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PlayerModel get(int playerId) {
        PlayerModel playerModel;
        try (Connection conn = dataSource.getConnection()) {

            String query = "SELECT * FROM player WHERE id =" + playerId + ";";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();

            playerModel = new PlayerModel(
                    resultSet.getString(2),
                    resultSet.getInt(4),
                    resultSet.getInt(5)
            );
            playerModel.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return playerModel;
    }

    @Override
    public List<PlayerModel> getAll() {
        return null;
    }
}
