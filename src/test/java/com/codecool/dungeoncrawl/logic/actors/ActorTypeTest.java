package com.codecool.dungeoncrawl.logic.actors;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActorTypeTest {

    @Test
    void getActorType() {
        ActorType testActor;
        assertThrows(RuntimeException.class, () -> ActorType.getActorType('X'));
    }
}