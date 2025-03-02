package com.example.apiplaysquaregames.service;

import java.util.List;
import java.util.Locale;

public interface GameCatalog {
    List<String> getGamesIdentifiers(Locale locale);

}
