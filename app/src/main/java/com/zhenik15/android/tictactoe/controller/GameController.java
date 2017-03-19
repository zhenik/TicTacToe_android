package com.zhenik15.android.tictactoe.controller;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.zhenik15.android.tictactoe.models.Board;
import com.zhenik15.android.tictactoe.models.GameInfoService;
import com.zhenik15.android.tictactoe.models.GameStatusCode;

import java.io.Serializable;


public class GameController implements Serializable {

    public static final String TAG = "GameController:> ";

    private final char X = 'X';
    private final char O = 'O';
    private final char EMPTY = ' ';

    private Board board;
    private Context context;
    private GameInfoService gameInfoService;
    private int turnCounter;

    public GameController(Board board, Context context, GameInfoService gameInfoService){
        this.board=board;
        this.context =context;
        this.gameInfoService = gameInfoService;

//        this.gameInfoService.initNames();
//        this.gameInfoService.showNavigationButtons();
    }

    public void resetGameBoard(){
        turnCounter=0;
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                board.getTable()[i][j] = EMPTY;
                Log.i(TAG, "|"+ board.getTable()[i][j]+"|");
            }
        }
    }

    private boolean isCellAvailable(int i, int j){
        return board.getTable()[i][j]==EMPTY;
    }

    private char nextTurn(){
        turnCounter++;
        if (turnCounter%2==0)return O;
        return X;
    }


    public void setCellListeners(){
        TableLayout table = board.getTableLayout();
        for(int i = 0; i<table.getChildCount(); i++){
            TableRow row = (TableRow) table.getChildAt(i);
            for(int j = 0; j<row.getChildCount(); j++){
                TextView cell = (TextView) row.getChildAt(j);
                cell.setOnClickListener(getGameClickListener(i, j, cell));
                Log.i(TAG, "set up listener for "+cell.getText()+i+j);
            }
        }
    }
    private void unsetClickListeners(){
        for(int i = 0; i<board.getTableLayout().getChildCount(); i++){
            TableRow row = (TableRow) board.getTableLayout().getChildAt(i);
            for(int j = 0; j<row.getChildCount(); j++){
                TextView cell = (TextView) row.getChildAt(j);
                cell.setOnClickListener(null);
                Log.i(TAG, "unset listener for "+cell.getText()+i+j);
            }
        }
    }

    //http://stackoverflow.com/questions/10614696/how-to-pass-parameters-to-onclicklistener/10614751
    public View.OnClickListener getGameClickListener(final int i, final int j, final TextView cell){
        return new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                char turnSymbol=nextTurn();

                if(isCellAvailable(i,j)) {
//                    char turnSymbol=nextTurn();
//                    Log.i(TAG, "|turn=|"+turnSymbol+"|");
                    board.getTable()[i][j]=turnSymbol;
//                    Log.i(TAG,": click cell "+ i+j + " : "+gameBoard[i][j]);
                    cell.setText(String.valueOf(turnSymbol));
                }

                switch (checkGameState(turnSymbol)){
                    case GameStatusCode.DRAW:
                        Toast.makeText(context, "DRAW", Toast.LENGTH_SHORT).show();
                        stopGame(GameStatusCode.DRAW);
                        break;
                    case GameStatusCode.X_WIN:
                        Toast.makeText(context, "X_WIN", Toast.LENGTH_SHORT).show();
                        stopGame(GameStatusCode.X_WIN);
                        break;
                    case GameStatusCode.O_WIN:
                        Toast.makeText(context, "O_WIN", Toast.LENGTH_SHORT).show();
                        stopGame(GameStatusCode.O_WIN);
                        break;
                }
            }
        };
    }



    private int checkGameState(char turn) {

        // RAW check
        if (checkRows(turn))
            return getWinner(turn);

        // COLUMN check
        if (checkColumns(turn))
            return getWinner(turn);

        // DIAGONALS check
        if (checkDiagonals(turn))
            return getWinner(turn);

        // DRAW
        if (turnCounter==9)
            return GameStatusCode.DRAW;

        // CONTINUE
        return GameStatusCode.CONTINUE;
    }


    private int getWinner(char turn){
        if (turn==X)return GameStatusCode.X_WIN;
        else return GameStatusCode.O_WIN;
    }

    private boolean checkRows(char turn){
        for (int j = 0; j<3; j++){
            int symbolCounter=0;
            for(int i = 0; i<3; i++){
                if(board.getTable()[j][i]==turn)
                    symbolCounter++;
            }
            if(symbolCounter==3)
                return true;
        }
        return false;
    }

    private boolean checkColumns(char turn){
        for (int j = 0; j<3; j++){
            int symbolCounter=0;
            for(int i = 0; i<3; i++){
                if(board.getTable()[i][j]==turn)
                    symbolCounter++;
            }
            if(symbolCounter==3)
                return true;
        }
        return false;
    }

    private boolean checkDiagonals(char turn){
        int count1 = 0;
        int count2 = 0;
        for(int i = 0; i<3; i++) {
            if(board.getTable()[i][i]==turn)
                count1++;
        }

        for(int i = 0; i<3; i++){
            if(board.getTable()[i][3-1-i]==turn)
                count2++;
        }
        return (count1==3||count2==3);
    }


    private void stopGame(int statusCode){
        unsetClickListeners();
        switch (statusCode){
            case GameStatusCode.DRAW:
                break;

        }
    }



}