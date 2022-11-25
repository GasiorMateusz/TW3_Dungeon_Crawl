package com.codecool.dungeoncrawl.logic.items;

import java.util.ArrayList;
import java.util.List;

public class Inventory {


    private List<Item> items = new ArrayList<>();

    public boolean hasKey() {
        return searchItem("key");
    }

    public boolean hasSword() {
       return searchItem("sword");
    }

    public boolean hasBow() {
        return searchItem("bow");
    }

    public boolean searchItem(String itemName){
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getTileName().equals(itemName)) {
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
    public void deleteKeyFromInventory() {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getTileName().equals("key")) {
                items.remove(i);
                break;
            }
        }
    }
}
