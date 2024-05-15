package de.neuefische.java.hhjava242springweb.characters.api;

import java.util.List;

public record RickMortyApiCharacter(
        int id,
        String name,
        String status,
        String species,
        List<String> episode) {
}
