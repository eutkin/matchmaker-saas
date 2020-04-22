package io.github.eutkin.herowars.matchmaker.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString(callSuper = true)
public class TitanDeck extends Deck {

    private final int superCount;

    @JsonCreator
    public TitanDeck(int power, int superCount) {
        super(power);
        this.superCount = superCount;
    }
}
