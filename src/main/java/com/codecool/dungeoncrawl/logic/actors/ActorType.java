package com.codecool.dungeoncrawl.logic.actors;

import java.util.Arrays;
import java.util.Objects;

public enum ActorType {
    PLAYER('@'),
    SKELETON('s'),
    GHOSTS('g'),
    OCTOPUS('o'),
    CROCODILE('c');

    private final char mapChar;

    ActorType(char mapChar) {
        this.mapChar = mapChar;
    }

    public static char getActorMapChar(ActorType actor) {
        return actor.mapChar;
    }
    public static ActorType getActorType(char mapChar) {
        try {
            return Arrays
                    .stream(ActorType.values())
                    .filter(v -> Objects.equals(v.getActorMapChar(v), mapChar))
                    .findFirst()
                    .orElseThrow(() ->
                            new Exception(String.format("Unknown ActorType.key: '%s'", mapChar)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
