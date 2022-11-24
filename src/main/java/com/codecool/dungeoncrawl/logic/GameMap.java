package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Octopus;
import com.codecool.dungeoncrawl.logic.actors.Player;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;

    private Player player;
    private Octopus octopus;
    private List<Actor> monstersList;

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
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

    public Octopus getOctopus() {
        return octopus;
    }

    public void setOctopus(Octopus octopus) {
        this.octopus = octopus;
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

    public Cell[][] getCells() {
        return cells;
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
}
