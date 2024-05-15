package de.neuefische.java.hhjava242springweb.characters;

import de.neuefische.java.hhjava242springweb.characters.api.RickMortyApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/characters")
public class CharacterController {

    private final RickMortyApiService rickMortyApiService;

    public CharacterController(RickMortyApiService rickMortyApiService) {
        this.rickMortyApiService = rickMortyApiService;
    }

    @GetMapping
    public RickMortyApiResponse getAllCharacters() {
        return rickMortyApiService.loadAllCharacters();
    }
}
