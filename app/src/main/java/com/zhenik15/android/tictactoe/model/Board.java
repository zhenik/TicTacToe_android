package com.zhenik15.android.tictactoe.model;

import android.util.Log;
import android.widget.TableLayout;

import com.zhenik15.android.tictactoe.model.util.GameSymbol;

import java.io.Serializable;
/**
 * Class represent game board entity
 * */
public class Board implements Serializable {

    public static final String TAG = "Board:> ";

    private char[][] table;
    private TableLayout tableLayout;

    public Board(TableLayout tableLayout) {
        table = new char[3][3];
        this.tableLayout = tableLayout;
    }

    public char[][] getTable() {
        return table;
    }

    public TableLayout getTableLayout() {
        return tableLayout;
    }

    public void resetGameBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                table[i][j] = GameSymbol.EMPTY;
                Log.i(TAG, "|" + table[i][j] + "|");
            }
        }
    }

    public boolean isCellAvailable(int i, int j) {
        return table[i][j] == GameSymbol.EMPTY;
    }
}