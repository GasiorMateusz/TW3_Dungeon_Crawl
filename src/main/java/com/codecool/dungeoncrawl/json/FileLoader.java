package com.codecool.dungeoncrawl.json;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileLoader {
    String content;
    public String loadFromFile(String filename){
        try {
            content = Files.readString(Paths.get(filename));
        }catch (Exception IOException){
            System.out.println("Can't load the data");
        }
        return content;
    }
}
