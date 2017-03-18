package com.zhenik15.android.tictactoe.controller;

import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.zhenik15.android.tictactoe.R;
import com.zhenik15.android.tictactoe.models.Board;

import java.io.Serializable;


public class GameController implements Serializable {

    public static final String TAG = "GameController:> ";
    private Board board;

    public GameController(Board board){
        this.board=board;
    }

    public void resetGameBoard(){
        board.setTurnCounter(0);
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                board.getTable()[i][j] = ' ';
                Log.i(TAG, "|"+ board.getTable()[i][j]+"|");
            }
        }
    }

    private boolean isCellAvailable(int i, int j){
        return board.getTable()[i][j]==' ';
    }

    private char nextTurn(){
        board.incrementTurnCounter();
        if (board.getTurnCounter()%2==0)return 'O';
        return 'X';
    }

    public void setCellListeners(TableLayout table){
        for(int i = 0; i<table.getChildCount(); i++){
            TableRow row = (TableRow) table.getChildAt(i);
            for(int j = 0; j<row.getChildCount(); j++){
                TextView cell = (TextView) row.getChildAt(j);
                cell.setOnClickListener(getGameClickListener(i, j, cell));
                Log.i(TAG, "set up listener for "+cell.getText()+i+j);
            }
        }
    }

    //http://stackoverflow.com/questions/10614696/how-to-pass-parameters-to-onclicklistener/10614751
    public View.OnClickListener getGameClickListener(final int i, final int j, final TextView cell){
        return new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if(isCellAvailable(i,j)) {
                    char turnSymbol=nextTurn();
//                    Log.i(TAG, "|turn=|"+turnSymbol+"|");
                    board.getTable()[i][j]=turnSymbol;
//                    Log.i(TAG,": click cell "+ i+j + " : "+gameBoard[i][j]);
                    cell.setText(String.valueOf(turnSymbol));
                }

            }
        };
    }
}
