package de.neuefische.java.hhjava242springweb.characters;

import de.neuefische.java.hhjava242springweb.characters.api.RickMortyApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class RickMortyApiService {

    private final RestClient restClient;

    public RickMortyApiService() {
        this.restClient = RestClient.builder()
                .baseUrl("https://rickandmortyapi.com")
                .build();
    }

    public RickMortyApiResponse loadAllCharacters() {

        RickMortyApiResponse body = restClient.get()
                .uri("/api/character")
                .retrieve()
                .body(RickMortyApiResponse.class);

        return body;
    }
}
