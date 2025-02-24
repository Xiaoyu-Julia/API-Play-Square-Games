package com.example.apiplaysquaregames.controller;

import com.example.apiplaysquaregames.service.GameCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
public class GameCatalogueController {

    @Autowired
    private GameCatalog gameCatalog;

    @GetMapping("/catalog")
    public List<String> getGamesIdentifiers(){
        return gameCatalog.getGamesIdentifiers().stream().toList();
    }


}
