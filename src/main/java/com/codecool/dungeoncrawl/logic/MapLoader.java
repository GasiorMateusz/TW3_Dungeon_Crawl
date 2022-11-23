package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Crocodile;
import com.codecool.dungeoncrawl.logic.actors.Octopus;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.items.Key;
import com.codecool.dungeoncrawl.logic.items.Sword;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap() {
        InputStream is = MapLoader.class.getResourceAsStream("/map.txt");
        int [] size = getMapSize(is);
        is = MapLoader.class.getResourceAsStream("/map.txt");
        Scanner scanner = new Scanner(is);
        int width = size[0];
        int height = size[1];

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            map.addMonsterToMonstersList(new Skeleton(cell));
                            break;
                        case 'o':
                            cell.setType(CellType.FLOOR);
                            map.addMonsterToMonstersList(new Octopus(cell));
                            break;
                        case 'c':
                            cell.setType(CellType.FLOOR);
                            map.addMonsterToMonstersList(new Crocodile(cell));
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        case 'K':
                            cell.setType(CellType.FLOOR);
                            new Key(cell);
                            break;
                        case 'W':
                            cell.setType(CellType.FLOOR);
                            new Sword(cell);
                            break;
                        case'O':
                            cell.setType(CellType.OPEN_DOOR);
                            break;
                        case'C':
                            cell.setType(CellType.CLOSED_DOOR);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

    private static int[] getMapSize(InputStream is){
        int width = 0;
        int height = 0;
        Scanner scanner = new Scanner(is);
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            if(line.length()>width){
                width = line.length();
            }
            height ++;
        }
        scanner.close();
        return new int []{width, height };
    }

}
