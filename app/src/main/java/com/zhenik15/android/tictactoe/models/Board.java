package com.zhenik15.android.tictactoe.models;

import android.util.Log;

import java.io.Serializable;

public class Board implements Serializable{

    public static final String TAG = "Board:> ";

    private char[][] table;
//    private int turnCounter;

    public Board(){
        table = new char[3][3];
    }



    public char[][] getTable() {
        return table;
    }

//    public void incrementTurnCounter(){
//        turnCounter++;
//    }
//
//    public int getTurnCounter() {
//        return turnCounter;
//    }
//
//    public void setTurnCounter(int counter){
//        this.turnCounter=counter;
//    }

}
