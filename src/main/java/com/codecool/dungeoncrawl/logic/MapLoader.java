package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.*;
import com.codecool.dungeoncrawl.logic.items.Crown;
import com.codecool.dungeoncrawl.logic.items.Key;
import com.codecool.dungeoncrawl.logic.items.careThings.Bandage;
import com.codecool.dungeoncrawl.logic.items.careThings.Medicine;
import com.codecool.dungeoncrawl.logic.items.careThings.NewLife;
import com.codecool.dungeoncrawl.logic.items.weapons.Bow;
import com.codecool.dungeoncrawl.logic.items.weapons.Sword;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    private static final String FLOORS = " .#XYOC";
    private static final String MONSTERS = "sgoc";
    private static final String ITEMS = "DMHBWKd";

    public static GameMap loadMap(String mapFile, boolean comingFromTeleport, Player... currentPlayer) throws RuntimeException {
        InputStream is = MapLoader.class.getResourceAsStream(mapFile);
        int[] size = getMapSize(is);
        is = MapLoader.class.getResourceAsStream(mapFile);
        Scanner scanner = new Scanner(is);
        int width = size[0];
        int height = size[1];

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
                        switch (line.charAt(x)) {
                            case 's':
                                cell.setType(CellType.FLOOR);
                                map.addMonsterToMonstersList(new Skeleton(cell));
                            case 'X':
                                break;
                            case 'g':
                                cell.setType(CellType.FLOOR);
                                map.addMonsterToMonstersList(new Ghosts(cell));
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
                                if (comingFromTeleport) {
                                    currentPlayer[0].setCell(cell);
                                    map.setPlayer(new Player(cell));
                                    map.getPlayer().setHealth(currentPlayer[0].getHealth());
                                    map.getPlayer().setName(currentPlayer[0].getName());
                                    map.getPlayer().setInventory(currentPlayer[0].getInventory());
                                    map.getPlayer().setStrikeStrength(currentPlayer[0].getStrikeStrength());
                                } else {
                                    map.setPlayer(new Player(cell));
                                    if (currentPlayer.length != 0) {
                                        map.getPlayer().setName(currentPlayer[0].getName());
                                    }
                                }
                                break;
                            case 'd':
                                cell.setType(CellType.FLOOR);
                                map.addToItemList(new Crown(cell));
                                break;
                            case 'K':
                                cell.setType(CellType.FLOOR);
                                map.addToItemList(new Key(cell));
                                break;
                            case 'W':
                                cell.setType(CellType.FLOOR);
                                map.addToItemList(new Sword(cell));
                                break;
                            case 'B':
                                cell.setType(CellType.FLOOR);
                                map.addToItemList(new Bow(cell));
                                break;
                            case 'H':
                                cell.setType(CellType.FLOOR);
                                map.addToItemList(new NewLife(cell));
                                break;
                            case 'M':
                                cell.setType(CellType.FLOOR);
                                map.addToItemList(new Medicine(cell));
                                break;
                            case 'D':
                                cell.setType(CellType.FLOOR);
                                map.addToItemList(new Bandage(cell));
                                break;

                            default:
                                throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                        }
                    }
                }
            }
        }
        return map;
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
}
