package io.github.eutkin.herowars.matchmaker.model;


import lombok.Data;

@Data
public class Player {

    private final String name;

    private final Deck heroes;

    private final TitanDeck titans;
}
