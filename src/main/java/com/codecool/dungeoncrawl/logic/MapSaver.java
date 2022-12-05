package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.ActorType;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.ItemType;

public class MapSaver {

    public static void saveMap(GameMap mapToSave, String mapFile) {
        int width = mapToSave.getWidth();
        int height = mapToSave.getHeight();
        char[][] map = new char[height][width];

        fillMapFloor(mapToSave, height, width, map);
        fillMapWithItems(mapToSave, map);
        fillMapWithMonsters(mapToSave, map);
//        saveMapToTextFile(mapFile, height, width, map);
        System.out.println(getFinalMap(height, width, map));
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

    private static String getFinalMap(int height, int width, char[][] map) {
        StringBuilder output = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                output.append(map[y][x]);
            }
            output.append(System.lineSeparator());
        }
        return output.toString();
    }

//    private static void saveMapToTextFile(String mapFile, int height, int width, char[][] map) throws IOException {
//        String fileName = "/" + mapFile + LocalDateTime.now() + ".txt";
//        try {
//            FileWriter myWriter = new FileWriter(fileName);
//            myWriter.write(getFinalMap(height, width, map));
//            myWriter.close();
//            System.out.println("Map successfully saved to the file " + fileName);
//        } catch (IOException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
//    }
}
