package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;

public class Cell implements Drawable {
    private CellType type;
    private Actor actor;




    private Item item;
    private GameMap gameMap;
    private int x, y;

    Cell(GameMap gameMap, int x, int y, CellType type) {
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Actor getActor() {
        return actor;
    }
    public Item getItem() { return item;}
    public void setItem(Item item) {this.item = item;}

    public Cell getNeighbor(int dx, int dy) {
        if (isOutOfMap(x + dx, y + dy)) {
            return null;
        } else {
            return gameMap.getCell(x + dx, y + dy);
        }
    }

    @Override
    public String getTileName() {
        return type.getTileName();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public boolean isFloor() {
        return this.getType().equals(CellType.FLOOR);
    }

    public boolean isActor() {
        return this.actor != null;
    }

    public boolean isPlayer() {
        return this.actor instanceof Player;
    }

    public boolean isOutOfMap(int x, int y) {
        return x * y < 0 || x >= gameMap.getWidth() || y >= gameMap.getHeight();
    }

    public boolean isClosedDoor(){
        return this.getType().equals(CellType.CLOSED_DOOR);
    }

    public boolean isOpenDoor(){
        return this.getType().equals(CellType.OPEN_DOOR);
    }
}
