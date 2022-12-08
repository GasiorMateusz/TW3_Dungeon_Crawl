package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private final int width;
    private final int height;



    private final Cell[][] cells;

    private Player player;
    private List<Actor> monstersList;
    private final List<Item> itemList;

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
        itemList = new ArrayList<>();
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


    public Player getPlayer() {
        return player;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isInBounds(int x, int y) {
        if (x < 0 || y < 0) return false;
        return x < width && y < height;
    }

    public List<Actor> getMonstersList() {
        return monstersList;
    }

    public void addMonsterToMonstersList(Actor monster) {
        if (this.monstersList == null) {
            this.monstersList = new ArrayList<>();
        }
        this.monstersList.add(monster);
    }
    public void addToItemList(Item itemToAdd) {
        this.itemList.add(itemToAdd);
    }
    public List<Item> getItemList() {
        return itemList;
    }

}
