package io.github.eutkin.herowars.matchmaker.service;

import static java.util.stream.Collectors.toMap;

import io.github.eutkin.herowars.matchmaker.model.Deck;
import io.github.eutkin.herowars.matchmaker.model.Match;
import io.github.eutkin.herowars.matchmaker.model.Player;
import io.github.eutkin.herowars.matchmaker.model.TitanDeck;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@Service
public class MatchMaker {

    public Map<String, Match> searchMatch(List<Player> team1, List<Player> team2) {
        Map<String, Deck> enemyByHeroes = team2.stream()
                .collect(toMap(Player::getName, Player::getHeroes));
        Map<String, TitanDeck> enemyByTitans = team2.stream()
                .collect(toMap(Player::getName, Player::getTitans));

        Map<String, Match> buffer = new HashMap<>();

        resolve(buffer, team1, enemyByHeroes);

        return buffer;
    }

    private boolean resolve(Map<String, Match> buffer, List<Player> team1,
            Map<String, Deck> enemyByHeroes) {
        for (Player player : team1) {
            String playerName = player.getName();
            if (buffer.containsKey(playerName)) {
                continue;
            }
            for (Entry<String, Deck> entry : enemyByHeroes.entrySet()) {
                Deck enemyDeck = entry.getValue();
                boolean match = check(player.getHeroes(), enemyDeck);
                if (!match) {
                    continue;
                }
                String enemyNickname = entry.getKey();
                buffer.put(playerName, new Match().enemyByHeroes(enemyNickname));
                if (resolve(buffer, team1, enemyByHeroes)) {
                    return true;
                }
                buffer.remove(playerName);
                return false;
            }
        }
        return true;
    }

    private boolean check(Deck playerDeck, Deck enemyDeck) {
        int diff = playerDeck.getPower() - enemyDeck.getPower();
        return diff > 2000 && diff < 20000;
    }

}
