package com.codecool.dungeoncrawl.json;

import java.io.BufferedWriter;
import java.io.FileWriter;


public class FileWritter {

    public void saveTofile(String json, String filename) {

        try {
            FileWriter fileWriter= new FileWriter(filename);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write("");
            writer.write(json);
            writer.close();
        } catch (Exception IOException) {
            System.out.println("Can't save");
        }
    }

}

