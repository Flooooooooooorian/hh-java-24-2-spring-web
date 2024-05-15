package de.neuefische.java.hhjava242springweb.characters.api;

public record RickMortyApiInfo(
        int count,
        int pages,
        String next,
        String prev
) {
}
