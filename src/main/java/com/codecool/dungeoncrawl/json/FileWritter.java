package com.codecool.dungeoncrawl.json;

import java.io.BufferedWriter;
import java.io.FileWriter;


public class FileWritter {

    public void saveTofile(String json, String filename) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write(json);
            writer.close();
        } catch (Exception IOException) {
            System.out.println("Can't save");
        }
    }

}

