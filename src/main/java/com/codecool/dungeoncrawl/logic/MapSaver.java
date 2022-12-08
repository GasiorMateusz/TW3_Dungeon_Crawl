package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.ActorType;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.ItemType;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

public class MapSaver {
    static int  height;
    static int width;
    static char[][] map;
    //todo dd consult Dominika i Marcin (it could replace part of or entire saveMap method)
    public static String convertGameMapToString(GameMap mapToSave) {
        width = mapToSave.getWidth();
        height = mapToSave.getHeight();
        map = new char[height][width];

        fillMapFloor(mapToSave, height, width, map);
        fillMapWithItems(mapToSave, map);
        String stringMap = getFinalMap(height, width, map);
        return stringMap;
    }
    //todo dd consult Dominika i Marcin (it could replace part of or entire saveMap method)


    public static void saveMap(GameMap mapToSave, String mapFile) {
         width = mapToSave.getWidth();
         height = mapToSave.getHeight();
         map = new char[height][width];

        fillMapFloor(mapToSave, height, width, map);
        fillMapWithItems(mapToSave, map);
        saveMapToTextFile(mapFile, height, width, map);
        System.out.println(getFinalMap(height, width, map));
        System.out.println("\nMONSTERS:\n" + getMonstersInfo(mapToSave));
        System.exit(0);
    }

    private static void fillMapFloor(GameMap mapToSave, int height, int width, char[][] map) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                map[y][x] = mapToSave.getCell(x, y).getType().getMapChar();
            }
        }
    }

    private static void fillMapWithMonsters(GameMap mapToSave, char[][] map) {
        for (Actor monster : mapToSave.getMonstersList()) {
            if (monster.isAlive()) {
                char monsterChar = ActorType.getActorMapChar(ActorType.valueOf(monster.getClass().getSimpleName().toUpperCase()));
                map[monster.getCell().getY()][monster.getCell().getX()] = monsterChar;
            }
        }
    }

    private static void fillMapWithItems(GameMap mapToSave, char[][] map) {
        for (Item item : mapToSave.getItemList()) {
            char itemChar = ItemType.getItemMapChar(item.getItemType());
            map[item.getCell().getY()][item.getCell().getX()] = itemChar;
        }
    }


    public static String getFinalMap(int height, int width, char[][] map) {
        StringBuilder output = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                output.append(map[y][x]);
            }
            output.append(System.lineSeparator());
        }
        return output.toString();
    }


    private static String getMonstersInfo(GameMap mapToSave) {
        StringJoiner output = new StringJoiner(", ");
        output.add(Integer.toString((int) mapToSave.getMonstersList()
                .stream()
                .filter(Actor::isAlive)
                .count()));
        for (Actor monster : mapToSave.getMonstersList()) {
            if (monster.isAlive()) {
                output.add(monster.getClass().getSimpleName());
                output.add(Integer.toString(monster.getHealth()));
                output.add(Integer.toString(monster.getX()));
                output.add(Integer.toString(monster.getY()));
            }
        }
        return output.toString();
    }

    private static String getInventoryInfo(GameMap mapToSave) {
        StringJoiner output = new StringJoiner(", ");
        output.add(Integer.toString((int) mapToSave.getPlayer().getInventory().getItems()
                .stream()
                .count()));
        for (Item item : mapToSave.getPlayer().getInventory().getItems()) {
            output.add(item.getItemType().toString());
        }
        return output.toString();
    }

    private static void saveMapToTextFile(String mapFile, int height, int width, char[][] map)  {
        String fileName = "src/main/resources/" + mapFile + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-dd-M--HH-mm-ss")) + ".txt";
        try {
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write(getFinalMap(height, width, map));
            myWriter.close();
            System.out.println("Map successfully saved to the file " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}