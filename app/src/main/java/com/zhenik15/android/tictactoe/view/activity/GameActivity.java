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
import com.zhenik15.android.tictactoe.models.Player;


public class GameActivity extends AppCompatActivity{

    public static final String TAG = "GameActivity:> ";

    private GameController gameController;
    private Board gameBoard;

    private Player player1;
    private Player player2;

    private TextView player1NameView;
    private TextView player2NameView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        initPlayers();
        initGameBoard();
        initInfo();
    }

    private void initPlayers(){
        player1 = (Player) getIntent().getSerializableExtra("player1");
        player2 = (Player) getIntent().getSerializableExtra("player2");
    }

    private void initInfo(){
        player1NameView = (TextView)findViewById(R.id.game_player1_name);
        player2NameView = (TextView)findViewById(R.id.game_player2_name);
        player1NameView.setText(player1.getName());
        player2NameView.setText(player2.getName());
    }

    private void initGameBoard() {
        TableLayout table = (TableLayout)findViewById(R.id.game_board);

        gameController = new GameController(new Board(), getApplicationContext());
        gameController.resetGameBoard();
        gameController.setCellListeners(table);
    }

}
