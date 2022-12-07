package com.codecool.dungeoncrawl.logic.actors;

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
}
