package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.ActorType;
import com.codecool.dungeoncrawl.logic.actors.Crocodile;
import com.codecool.dungeoncrawl.logic.actors.Ghosts;
import com.codecool.dungeoncrawl.logic.actors.Octopus;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.items.Bandage;
import com.codecool.dungeoncrawl.logic.items.Bow;
import com.codecool.dungeoncrawl.logic.items.Crown;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.ItemType;
import com.codecool.dungeoncrawl.logic.items.Key;
import com.codecool.dungeoncrawl.logic.items.Life;
import com.codecool.dungeoncrawl.logic.items.Medicine;
import com.codecool.dungeoncrawl.logic.items.Sword;
import com.codecool.dungeoncrawl.model.PlayerModel;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class MapLoader {
    private static final String FLOORS = " .#XYOC";
    private static final String MONSTERS = "sgoc";
    private static final String ITEMS = "DMHBWKd";

    public static GameMap loadMap(String mapFile) {
        InputStream is;
        int height, width;
        if (mapFile.startsWith("STR")) {
            String mapString = mapFile.substring(3);
            is = new ByteArrayInputStream(mapString.getBytes());
            int[] size = getMapSize(is);
            width = size[0];
            height = size[1];
            is = new ByteArrayInputStream(mapString.getBytes());
        } else {
            is = MapLoader.class.getResourceAsStream(mapFile);
            int[] size = getMapSize(is);
            width = size[0];
            height = size[1];
            is = MapLoader.class.getResourceAsStream(mapFile);
        }
        Scanner scanner = new Scanner(is);
        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    String read = String.valueOf((line.charAt(x)));
                    if (FLOORS.contains(read)) {
                        cell.setType(CellType.getCellType((line.charAt(x))));
                    } else {
                        cell.setType(CellType.FLOOR);
                        if (MONSTERS.contains(read)) {
                            addNewMonster(map, cell, getMonsterConstructor(read));
                        } else {
                            if (ITEMS.contains(read)) {
                                addNewItem(map, cell, getItemConstructor(read));
                            } else {
                                if (read.equals("@")) {
                                    assert true;
                                } else {
                                    throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                                }
                            }
                        }
                    }
                }
            }
        }
        return map;
    }

    public Cell findPlayersPositionOnTheMapInTheFile(String mapFile){
        InputStream is;
        int height, width;
        if (mapFile.startsWith("STR")) {
            String mapString = mapFile.substring(3);
            is = new ByteArrayInputStream(mapString.getBytes());
            int[] size = getMapSize(is);
            width = size[0];
            height = size[1];
            is = new ByteArrayInputStream(mapString.getBytes());
        } else {
            is = MapLoader.class.getResourceAsStream(mapFile);
            int[] size = getMapSize(is);
            width = size[0];
            height = size[1];
            is = MapLoader.class.getResourceAsStream(mapFile);
        }
        Scanner scanner = new Scanner(is);
        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    String read = String.valueOf((line.charAt(x)));
                    if (read.equals("@")) {
                        return new Cell(map, x, y, CellType.FLOOR);
                    }
                }
            }
        }
        return null;
    }

    public void addPlayerToMap(Player player, boolean comingFromTeleport, GameMap map){
        if (comingFromTeleport) {
            teleportPlayer(map, player.getCell(), new Player[]{player});
        } else {
            map.setPlayer(new Player(player.getCell()));
        }
    }

    private static void teleportPlayer(GameMap map, Cell cell, Player[] currentPlayer) {
        currentPlayer[0].setCell(cell);
        map.setPlayer(new Player(cell));
        map.getPlayer().setHealth(currentPlayer[0].getHealth());
        map.getPlayer().setName(currentPlayer[0].getName());
        map.getPlayer().setInventory(currentPlayer[0].getInventory());
        map.getPlayer().setStrikeStrength(currentPlayer[0].getStrikeStrength());
    }

    private static int[] getMapSize(InputStream is) {
        int width = 0;
        int height = 0;
        Scanner scanner = new Scanner(is);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.length() > width) {
                width = line.length();
            }
            height++;
        }
        scanner.close();
        return new int[]{width, height};
    }

    private static String getMonsterClassName(String read) {
        String monsterClassRead = String.valueOf(ActorType.getActorType(read.charAt(0))).toLowerCase();
        String monsterClassString = monsterClassRead.substring(0, 1).toUpperCase() + monsterClassRead.substring(1);
        return ("com.codecool.dungeoncrawl.logic.actors." + monsterClassString);
    }

    private static String getItemClassName(String read) {
        String itemClassRead = String.valueOf(ItemType.getItemType(read.charAt(0))).toLowerCase();
        String itemClassString = itemClassRead.substring(0, 1).toUpperCase() + itemClassRead.substring(1);
        return ("com.codecool.dungeoncrawl.logic.items." + itemClassString);
    }

    private static Constructor<?> getMonsterConstructor(String read) {
        Class<?> monsterClass;
        try {
            monsterClass = Class.forName(getMonsterClassName(read));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Constructor<?> monsterConstructor;
        try {
            monsterConstructor = monsterClass.getConstructor(Cell.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return monsterConstructor;
    }

    private static Constructor<?> getItemConstructor(String read) {
        Class<?> itemClass;
        try {
            itemClass = Class.forName(getItemClassName(read));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Constructor<?> itemConstructor;
        try {
            itemConstructor = itemClass.getConstructor(Cell.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return itemConstructor;
    }

    private static void addNewMonster(GameMap map, Cell cell, Constructor<?> monsterConstructor) {
        try {
            map.addMonsterToMonstersList((Actor) monsterConstructor.newInstance(cell));
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static void addNewItem(GameMap map, Cell cell, Constructor<?> itemConstructor) {
        try {
            map.addToItemList((Item) itemConstructor.newInstance(cell));
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createPlayer(PlayerModel playerModel, GameMap map){
        Player player = new Player(map.getCell(playerModel.getX(), playerModel.getY()));
        map.setPlayer(player);
        map.getCell(player.getX(), player.getY()).setActor(player);
        player.setCell(map.getCell(player.getX(), player.getY()));
        player.setName(playerModel.getPlayerName());
        player.setHealth(playerModel.getHp());

    }
}
