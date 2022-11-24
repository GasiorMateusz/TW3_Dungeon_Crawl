package com.codecool.dungeoncrawl.logic.items;

import java.util.ArrayList;
import java.util.List;

public class Inventory {


    private List<Item> items = new ArrayList<>();

    public boolean hasKey() {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getTileName().equals("key")) {
                return true;
            }
        }
        return false;
    }

    public boolean hasSword() {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getTileName().equals("sword")) {
                return true;
            }
        }
        return false;
    }

    public boolean hasBow() {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getTileName().equals("bow")) {
                return true;
            }
        }
        return false;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
