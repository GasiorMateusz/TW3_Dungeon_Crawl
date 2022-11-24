package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("player", new Tile(27, 0));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("crocodile", new Tile(29, 8));
        tileMap.put("octopus", new Tile(25, 8));
        tileMap.put("ghosts", new Tile(24, 8));
        tileMap.put("key", new Tile(16, 23));
        tileMap.put("crown", new Tile(12, 24));
        tileMap.put("sword", new Tile(0, 30));
        tileMap.put("bow", new Tile(5, 28));
        tileMap.put("openDoor", new Tile(6, 9));
        tileMap.put("closedDoor", new Tile(3, 9));
        tileMap.put("stairs", new Tile(3, 6));
        tileMap.put("life", new Tile(23, 22));
        tileMap.put("medicine", new Tile(16, 25));
        tileMap.put("bandage", new Tile(27, 23));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }

    public static class Tile {
        public final int x, y, w, h;

        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }
}
