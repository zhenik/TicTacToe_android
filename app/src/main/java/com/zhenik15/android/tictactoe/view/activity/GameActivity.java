package com.zhenik15.android.tictactoe.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.zhenik15.android.tictactoe.R;
import com.zhenik15.android.tictactoe.controller.GameController;
import com.zhenik15.android.tictactoe.models.Board;


public class GameActivity extends AppCompatActivity{

    public static final String TAG = "GameActivityTag";

    private GameController gameController;
    private Board gameBoard;
    private String playerName1;
    private String playerName2;

    private TextView player1;
    private TextView player2;

//    private char[][] gameBoard;
//    private int turnCounter;

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
        gameBoard = new Board();
        gameController = new GameController(gameBoard);
        Log.i(TAG, ": after init "+gameBoard.toString());
        gameBoard.resetGameBoard();
        Log.i(TAG, ": after reset "+ gameBoard);
        TableLayout table = (TableLayout)findViewById(R.id.game_board);

        for(int i = 0; i<table.getChildCount(); i++){
            TableRow row = (TableRow) table.getChildAt(i);
            for(int j = 0; j<row.getChildCount(); j++){
                TextView cell = (TextView) row.getChildAt(j);
                cell.setOnClickListener(gameController.getGameClickListener(i, j, cell));
                Log.i(TAG, " : set up listener for "+cell.getText()+i+j);
            }
        }
    }
}
