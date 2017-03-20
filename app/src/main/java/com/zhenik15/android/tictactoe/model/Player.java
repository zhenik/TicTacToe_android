package com.zhenik15.android.tictactoe.model;

import android.support.annotation.NonNull;

import java.io.Serializable;

public class Player implements Serializable, Comparable<Player> {

    public static final String TAG = "Player:> ";

    private String name;
    private int score;

    public Player(String name) {
        this.name = name;
        score = 0;
    }
    public Player(String name, int score){
        this.name=name;
        this.score=score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int win() {
        return ++score;
    }

    // For DESC Sort. From biggest to lowest
    @Override
    public int compareTo(@NonNull Player o) {

        return -Integer.compare(this.score, o.score);
    }
}