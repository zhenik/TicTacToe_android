package com.zhenik15.android.tictactoe.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;
import android.widget.TextView;

import com.zhenik15.android.tictactoe.R;
import com.zhenik15.android.tictactoe.controller.GameController;
import com.zhenik15.android.tictactoe.models.Board;
import com.zhenik15.android.tictactoe.models.GameInfoService;
import com.zhenik15.android.tictactoe.models.Player;

import org.w3c.dom.Text;


public class GameActivity extends AppCompatActivity{

    public static final String TAG = "GameActivity:> ";

    private GameController gameController;
    private Board gameBoard;
    private GameInfoService gameInfoService;

    private Player player1;
    private Player player2;

    public static TextView playerScore1;
    public static TextView playerScore2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        initPlayers();
        initGame();
        initInfo();

    }

    private void initPlayers(){
        player1 = (Player) getIntent().getSerializableExtra("player1");
        player2 = (Player) getIntent().getSerializableExtra("player2");
    }

    private void initInfo(){

//        player1NameView = (TextView)findViewById(R.id.game_player1_name);
//        player2NameView = (TextView)findViewById(R.id.game_player2_name);
//        getApplicationContext().getResources().getLayout(R.id.);
        gameInfoService = new GameInfoService(player1, player2, findViewById(R.id.game_main_view), this);
//        gameInfoService = new GameInfoService(player1, player2, getApplicationContext());

//        player1NameView.setText(player1.getName());
//        player2NameView.setText(player2.getName());
    }

    private void initGame() {

        TableLayout table = (TableLayout)findViewById(R.id.game_board);
        gameBoard=new Board(table);
        gameController = new GameController(gameBoard, getApplicationContext(), gameInfoService);

        gameController.resetGameBoard();
        gameController.setCellListeners();
    }

}
