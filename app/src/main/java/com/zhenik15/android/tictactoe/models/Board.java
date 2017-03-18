package com.zhenik15.android.tictactoe.models;

import android.util.Log;

import java.io.Serializable;

public class Board implements Serializable{

    public static final String TAG = "BoardTag";

    private char[][] table;
    private int turnCounter;

    public Board(){
        table = new char[3][3];
    }

    public void resetGameBoard(){
        turnCounter=0;
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                table[i][j] = ' ';
                Log.i(TAG, "|"+ table[i][j]+"|");
            }
        }
    }

    public char[][] getTable() {
        return table;
    }

    public void incrementTurnCounter(){
        turnCounter++;
    }

    public int getTurnCounter() {
        return turnCounter;
    }
}
