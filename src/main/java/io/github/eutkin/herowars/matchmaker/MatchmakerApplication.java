package io.github.eutkin.herowars.matchmaker;

import io.github.eutkin.herowars.matchmaker.model.Match;
import io.github.eutkin.herowars.matchmaker.service.MatchMakerService;
import io.github.eutkin.herowars.matchmaker.service.MatchMakerService.MatchView;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@SpringBootApplication
@Controller
public class MatchmakerApplication {

    public MatchmakerApplication(MatchMakerService matchMaker) {
        this.matchMaker = matchMaker;
    }

    public static void main(String[] args) {
        SpringApplication.run(MatchmakerApplication.class, args);
    }

    private final MatchMakerService matchMaker;

    @GetMapping("/{packId}")
    public ModelAndView view(@PathVariable Integer packId) {
        MatchView result = matchMaker.matchTeamsByPackId(packId);

        ModelAndView view = new ModelAndView("result");
        view.addObject("view", result);
        return view;
    }

}
