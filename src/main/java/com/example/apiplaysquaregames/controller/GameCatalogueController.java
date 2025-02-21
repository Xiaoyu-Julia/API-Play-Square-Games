package com.example.apiplaysquaregames.controller;

import com.example.apiplaysquaregames.service.GameCatalog;
import fr.le_campus_numerique.square_games.engine.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
public class GameCatalogueController {

    @Autowired
    private GameCatalog gameCatalog;

    @GetMapping("/catalogue")
    List<Game> getGamesIdentifiers(){
        return gameCatalog.getGamesIdentifiers();
    }

}
git