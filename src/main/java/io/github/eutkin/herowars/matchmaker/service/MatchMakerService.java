package io.github.eutkin.herowars.matchmaker.service;

import io.github.eutkin.herowars.matchmaker.model.Match;
import io.github.eutkin.herowars.matchmaker.model.Player;
import io.github.eutkin.herowars.matchmaker.repository.PlayerRepository;
import lombok.Data;
import org.springframework.data.util.Pair;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MatchMakerService {

    private final PlayerRepository playerRepository;
    private final MatchMaker matchMaker;

    public MatchMakerService(
            @NonNull PlayerRepository playerRepository,
            MatchMaker matchMaker) {
        this.playerRepository = playerRepository;
        this.matchMaker = matchMaker;
    }

    public MatchView matchTeamsByPackId(Integer packId) {
        Pair<List<Player>, List<Player>> pack = playerRepository.findAllByPack(packId);
        Map<String, Match> match = matchMaker
                .searchMatch(pack.getFirst(), pack.getSecond());
        return new MatchView(pack.getFirst(), pack.getSecond(), match);
    }

    @Data
    public static class MatchView {
        private final List<Player> team1;
        private final List<Player> team2;
        private final Map<String, Match> match;
    }
}
