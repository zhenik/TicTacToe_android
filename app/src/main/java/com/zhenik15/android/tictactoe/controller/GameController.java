package com.zhenik15.android.tictactoe.controller;

import android.view.View;
import android.widget.TextView;

import com.zhenik15.android.tictactoe.models.Board;

import java.io.Serializable;


public class GameController implements Serializable {

    private Board board;

    public GameController(Board board){
        this.board=board;
    }

    private boolean isCellAvailable(int i, int j){
        return board.getTable()[i][j]==' ';
    }

    private char nextTurn(){
        board.incrementTurnCounter();
        if (board.getTurnCounter()%2==0)return 'O';
        return 'X';
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
