package com.zhenik15.android.tictactoe.models;
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

    public void win(){
        score++;
    }
}
