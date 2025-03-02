package com.example.apiplaysquaregames.controller;

import com.example.apiplaysquaregames.service.GameCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

@RestController
public class GameCatalogueController {

    @Autowired
    private GameCatalog gameCatalog;

    @GetMapping("/catalog")
    public List<String> getGamesIdentifiers(@RequestHeader("Accept-Language") Locale locale) {
        //return gameCatalog.getGamesIdentifiers().stream().toList();
        return gameCatalog.getGamesIdentifiers(locale);
    }

}
