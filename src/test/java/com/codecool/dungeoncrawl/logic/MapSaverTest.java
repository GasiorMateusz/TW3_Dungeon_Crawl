//package com.codecool.dungeoncrawl.logic;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class MapSaverTest {
//
//    @BeforeEach
//    void setUp() {
//
//
//
//    }
//
//    @Test
//    void saveMapWithStringParameter() {
//        String testMap = "STR### ###\n#d.#.c#\n#..s..#\n #.@.#\n#.....#\n#g.#.o#\n### ###";
//        GameMap mapLoaded = MapLoader.loadMap(testMap, false);
//        int width = mapLoaded.getWidth();
//        int height = mapLoaded.getHeight();
//        char[][] map = new char[height][width];
//        MapSaver.saveMap(mapLoaded, "t");
//      //  assertEquals();
//
//
//
//
//    }
//}