package de.neuefische.java.hhjava242springweb.characters;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CharacterControllerIntegrationTest {

    private static MockWebServer mockWebServer;
    @Autowired
    MockMvc mockMvc;

    @BeforeAll
    static void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterAll
    static void stop() throws IOException {
        mockWebServer.shutdown();
    }

    @DynamicPropertySource
    static void dynamicProperties(DynamicPropertyRegistry registry) {
        registry.add("app.rickmorty.api.url", () -> mockWebServer.url("/").toString());
    }

    @Test
    void getAllCharacters() throws Exception {
        //GIVEN
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .setBody("""
                        {
                            "info": {
                                "count": 32,
                                "pages": 42
                            },
                            "results": [
                                {
                                    "id": 1,
                                    "name": "Rick Sanchez",
                                    "status": "Alive",
                                    "species": "Human"
                                }
                            ]
                        }
                        """));

        //WHEN
        mockMvc.perform(get("/api/characters"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                                                   "infos": {
                                                       "count": 32,
                                                       "pages": 42
                                                   },
                                                   "results": [
                                                       {
                                                           "id": 1,
                                                           "name": "Rick Sanchez",
                                                           "status": "Alive",
                                                           "species": "Human"
                                                       }
                                                   ]
                                               }
                        """));

        //THEN
    }
}
