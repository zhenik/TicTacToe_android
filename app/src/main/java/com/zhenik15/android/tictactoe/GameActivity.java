package com.zhenik15.android.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class GameActivity extends AppCompatActivity{

    public static final String TAG = "GameActivityTag";

    private String playerName1;
    private String playerName2;

    private TextView player1;
    private TextView player2;

    private char[][] gameBoard;
    private int turnCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        initPlayerNames();
        initGameBoard();
    }

    private void initPlayerNames(){
        playerName1 = getIntent().getStringExtra("userName1");
        playerName2 = getIntent().getStringExtra("userName2");
        Log.i(TAG, " names:"+playerName1);
        Log.i(TAG, " names:"+playerName2);
        player1 = (TextView)findViewById(R.id.game_player1_name);
        player2 = (TextView)findViewById(R.id.game_player2_name);
        player1.setText(playerName1);
        player2.setText(playerName2);
    }

    protected void initGameBoard() {
        gameBoard=new char[3][3];
        Log.i(TAG, ": after init "+gameBoard.toString());
        resetGameBoard();
//        Log.i(TAG, ": after reset "+ gameBoard);
        TableLayout table = (TableLayout)findViewById(R.id.game_board);
        for(int i = 0; i<table.getChildCount(); i++){
            TableRow row = (TableRow) table.getChildAt(i);
            for(int j = 0; j<row.getChildCount(); j++){
                TextView cell = (TextView) row.getChildAt(j);
//                cell.setText(R.string.space);
                cell.setOnClickListener(move(i, j, cell));
                Log.i(TAG, " : set up listener for "+cell.getText()+i+j);
            }
        }
    }

    protected void resetGameBoard(){
        turnCounter=0;
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                gameBoard[i][j] = ' ';
                Log.i(TAG, "|"+gameBoard[i][j]+"|");
            }
        }
    }

    private boolean isCellAvailable(int i, int j){
        return gameBoard[i][j]==' ';
    }

    private char nextTurn(){
        turnCounter++;
        if (turnCounter%2==0)return 'O';
        return 'X';
    }


    //http://stackoverflow.com/questions/10614696/how-to-pass-parameters-to-onclicklistener/10614751
    private View.OnClickListener move(final int i, final int j, final TextView cell1){
        return new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if(isCellAvailable(i,j)) {
                    gameBoard[i][j]='X';
                    Log.i(TAG,": click cell "+ i+j + " : "+gameBoard[i][j]);
                    cell1.setText("X");
                }

//                cell1.setText('X');
            }
        };
    }
}
