package com.zhenik15.android.tictactoe.model;
import java.io.Serializable;

public class Player implements Serializable {
    public static final String TAG = "Player:> ";
    private String name;
    private int score;

    public Player(String name){
        this.name=name;
        score=0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int win(){
        return ++score;
    }
}
