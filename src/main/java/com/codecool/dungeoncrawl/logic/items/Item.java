package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Item implements Drawable {
    private final Cell cell;
    private final ItemType itemType;

    protected Item(Cell cell) {
        this.cell = cell;
        this.cell.setItem(this);
        itemType = ItemType.valueOf(this.getClass().getSimpleName().toUpperCase());
    }
    public ItemType getItemType() {
        return this.itemType;
    }
    public Cell getCell() {
        return cell;
    }
}
