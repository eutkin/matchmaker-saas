package io.github.eutkin.herowars.matchmaker.repository.impl;

import static java.util.stream.Collectors.toList;

import io.github.eutkin.herowars.matchmaker.model.Deck;
import io.github.eutkin.herowars.matchmaker.model.Player;
import io.github.eutkin.herowars.matchmaker.model.TitanDeck;
import io.github.eutkin.herowars.matchmaker.repository.PlayerRepository;
import org.springframework.data.util.Pair;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.function.Predicate;

@Repository
public class JdbcPlayerRepository implements PlayerRepository {

    private final JdbcOperations jdbc;

    public JdbcPlayerRepository(@NonNull JdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Pair<List<Player>, List<Player>> findAllByPack(Integer packId) {
        List<Pair<Player, Boolean>> players = jdbc
                .query("select * from teams where pack_id = ?", (rs, i) -> {
                    String name = rs.getString("name");
                    int heroes = rs.getInt("heroes");
                    int titans = rs.getInt("titans");
                    int superCount = rs.getInt("super");
                    return Pair.of(new Player(name, new Deck(heroes),
                            new TitanDeck(titans, superCount)), "g".equals(rs.getString("type")));
                }, packId);
        List<Player> goodGuy = players.stream().filter(Pair::getSecond).map(Pair::getFirst)
                .collect(toList());
        List<Player> badGuy = players.stream().filter(Predicate.not(Pair::getSecond))
                .map(Pair::getFirst).collect(toList());
        return Pair.of(goodGuy, badGuy);
    }
}
