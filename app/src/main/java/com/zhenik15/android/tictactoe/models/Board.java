package com.zhenik15.android.tictactoe.models;

import android.util.Log;
import android.widget.TableLayout;

import java.io.Serializable;

public class Board implements Serializable{

    public static final String TAG = "Board:> ";

    private char[][] table;
    private TableLayout tableLayout;

    public Board(TableLayout tableLayout){
        table = new char[3][3];
        this.tableLayout=tableLayout;
    }

    public char[][] getTable() {
        return table;
    }

    public TableLayout getTableLayout() {
        return tableLayout;
    }
}
