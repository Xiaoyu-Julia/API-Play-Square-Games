package com.example.apiplaysquaregames.service;

public class TicTacToeGameFactory {

    private String gameName;

    public TicTacToeGameFactory(String gameName) {
        this.gameName = gameName;
    }

    public String getGameName() {
        return gameName;
    }
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}
