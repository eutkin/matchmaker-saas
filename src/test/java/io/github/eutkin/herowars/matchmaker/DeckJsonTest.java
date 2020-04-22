package io.github.eutkin.herowars.matchmaker;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.eutkin.herowars.matchmaker.model.Deck;
import io.github.eutkin.herowars.matchmaker.model.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@JsonTest
public class DeckJsonTest {

    @Autowired
    private ObjectMapper json;

    @Test
    void deser() throws IOException {
        Player player = json.readValue(new ClassPathResource("deck.json").getInputStream(), Player.class);
        System.out.println(json.writeValueAsString(player));
    }
}
