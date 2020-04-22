package io.github.eutkin.herowars.matchmaker.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;

@Data
public class Deck {

    private int power;

    @JsonCreator
    public Deck(int power) {
        this.power = power;
    }
}
