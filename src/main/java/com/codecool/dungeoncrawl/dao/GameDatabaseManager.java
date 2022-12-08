package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class GameDatabaseManager {
    private PlayerDao playerDao;
    private GameStateDao gameStateDao;

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        playerDao = new PlayerDaoJdbc(dataSource);
        gameStateDao = new GameStateDaoJdbc(dataSource);
    }
    public void setPlayer(PlayerModel player){
        playerDao.set(player);
    }

    public void savePlayer(PlayerModel model) {
        playerDao.set(model);
    }

    public void saveGameState(GameState gameState) {
        gameStateDao.add(gameState);
    }

    public void saveAll(Player player, String currentMap, String savedGameName) {
        long now = System.currentTimeMillis();
        java.sql.Date currentDate = new java.sql.Date(now);
        PlayerModel model = new PlayerModel(player, savedGameName);
        GameState gameState = new GameState(currentMap, currentDate, model);
        savePlayer(model);
        saveGameState(gameState);
    }

//    public PlayerModel getPlayerById(int index) {
//        return playerDao.get(index);
//    }

    public PlayerModel getSelectedPlayer(String selectedPlayer){
        return playerDao.get(selectedPlayer);
    }

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String dbName = System.getenv("PSQL_DB_NAME");
        String user = System.getenv("PSQL_USER_NAME");
        String password = System.getenv("PSQL_PASSWORD");

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }

    public List<String> getLoadNames(){
        return playerDao.getSaveNames();
    }

    public GameState getGameState(int playerId){
        return gameStateDao.get(playerId);
    }
}
