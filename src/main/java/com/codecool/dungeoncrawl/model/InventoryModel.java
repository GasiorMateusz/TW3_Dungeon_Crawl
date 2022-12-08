package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.items.Inventory;

import java.util.List;
import java.util.stream.Collectors;

public class InventoryModel {
    private final List<String> items;

    public InventoryModel(Inventory inventory) {
        this.items = convertItemsToStrings(inventory);
    }

    public List<String> convertItemsToStrings(Inventory inventory) {
        return inventory.getItems().stream().map(Drawable::getTileName).collect(Collectors.toList());

    }
}
