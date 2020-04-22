package io.github.eutkin.herowars.matchmaker.repository;

import io.github.eutkin.herowars.matchmaker.model.Player;
import org.springframework.data.util.Pair;

import java.util.List;

public interface PlayerRepository {

    Pair<List<Player>, List<Player>> findAllByPack(Integer packId);
}
