package com.zhenik15.android.tictactoe.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.zhenik15.android.tictactoe.R;
import com.zhenik15.android.tictactoe.model.Board;
import com.zhenik15.android.tictactoe.model.GameStatusCode;
import com.zhenik15.android.tictactoe.model.GameSymbol;
import com.zhenik15.android.tictactoe.model.Player;


public class GameActivity extends AppCompatActivity {

    public static final String TAG = "GameActivity:> ";

    // Views
    private TextView scorePlayer1;
    private TextView scorePlayer2;
    private View navigation;

    // Models
    private Board gameBoard;
    private Player player1;
    private Player player2;

    // Game
    private int turnCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        initPlayers();
        initBoard();
        initNavigation();
        navigation.setVisibility(View.INVISIBLE);
        resetGame();
    }

    /**
     * SET & INIT  methods
     */

    private void initPlayers() {
        player1 = (Player) getIntent().getSerializableExtra("player1");
        player2 = (Player) getIntent().getSerializableExtra("player2");

        ((TextView) findViewById(R.id.game_player1_name)).setText(player1.getName());
        ((TextView) findViewById(R.id.game_player2_name)).setText(player2.getName());

        scorePlayer1 = (TextView) findViewById(R.id.game_player1_score);
        scorePlayer2 = (TextView) findViewById(R.id.game_player2_score);
        updateScore();

    }

    private void setScore(TextView scoreView, Player player) {
        scoreView.setText("Score: " + player.getScore());
    }

    private void updateScore() {
        setScore(scorePlayer1, player1);
        setScore(scorePlayer2, player2);
    }

    private void initNavigation() {
        navigation = findViewById(R.id.game_nav_panel);
    }

    private void initBoard() {
        TableLayout table = (TableLayout) findViewById(R.id.game_board);
        gameBoard = new Board(table);
    }

    public void setCellListeners() {
        TableLayout table = gameBoard.getTableLayout();
        for (int i = 0; i < table.getChildCount(); i++) {
            TableRow row = (TableRow) table.getChildAt(i);
            for (int j = 0; j < row.getChildCount(); j++) {
                TextView cell = (TextView) row.getChildAt(j);
                cell.setOnClickListener(getGameClickListener(i, j, cell));
                Log.i(TAG, "set up listener for " + cell.getText() + i + j);
            }
        }
    }

    private void unsetCellListeners() {
        for (int i = 0; i < gameBoard.getTableLayout().getChildCount(); i++) {
            TableRow row = (TableRow) gameBoard.getTableLayout().getChildAt(i);
            for (int j = 0; j < row.getChildCount(); j++) {
                TextView cell = (TextView) row.getChildAt(j);
                cell.setOnClickListener(null);
                Log.i(TAG, "unset listener for " + cell.getText() + i + j);
            }
        }
    }

    //http://stackoverflow.com/questions/10614696/how-to-pass-parameters-to-onclicklistener/10614751
    public View.OnClickListener getGameClickListener(final int i, final int j, final TextView cell) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                char turnSymbol = nextTurn();

                if (gameBoard.isCellAvailable(i, j)) {
//                    Log.i(TAG, "|turn=|"+turnSymbol+"|");
                    gameBoard.getTable()[i][j] = turnSymbol;
//                    Log.i(TAG,": click cell "+ i+j + " : "+gameBoard[i][j]);
                    cell.setText(String.valueOf(turnSymbol));
                }

                switch (checkGameState(turnSymbol)) {
                    case GameStatusCode.DRAW:
                        Toast.makeText(getApplicationContext(), "DRAW", Toast.LENGTH_SHORT).show();
                        stopGame(GameStatusCode.DRAW);
                        break;
                    case GameStatusCode.X_WIN:
                        Toast.makeText(getApplicationContext(), "X_WIN", Toast.LENGTH_SHORT).show();
                        stopGame(GameStatusCode.X_WIN);
                        break;
                    case GameStatusCode.O_WIN:
                        Toast.makeText(getApplicationContext(), "O_WIN", Toast.LENGTH_SHORT).show();
                        stopGame(GameStatusCode.O_WIN);
                        break;
                }
            }
        };
    }

    /**
     * GAME LOGIC
     */

    public void resetGame() {
        turnCounter = 0;
        gameBoard.resetGameBoard();
        setCellListeners();
    }


    private char nextTurn() {
        turnCounter++;
        if (turnCounter % 2 == 0) return GameSymbol.O;
        return GameSymbol.X;
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
        if (turnCounter == 9)
            return GameStatusCode.DRAW;

        // CONTINUE
        return GameStatusCode.CONTINUE;
    }


    private int getWinner(char turn) {
        if (turn == GameSymbol.X) return GameStatusCode.X_WIN;
        else return GameStatusCode.O_WIN;
    }

    private boolean checkRows(char turn) {
        for (int j = 0; j < 3; j++) {
            int symbolCounter = 0;
            for (int i = 0; i < 3; i++) {
                if (gameBoard.getTable()[j][i] == turn)
                    symbolCounter++;
            }
            if (symbolCounter == 3)
                return true;
        }
        return false;
    }

    private boolean checkColumns(char turn) {
        for (int j = 0; j < 3; j++) {
            int symbolCounter = 0;
            for (int i = 0; i < 3; i++) {
                if (gameBoard.getTable()[i][j] == turn)
                    symbolCounter++;
            }
            if (symbolCounter == 3)
                return true;
        }
        return false;
    }

    private boolean checkDiagonals(char turn) {
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < 3; i++) {
            if (gameBoard.getTable()[i][i] == turn)
                count1++;
        }

        for (int i = 0; i < 3; i++) {
            if (gameBoard.getTable()[i][3 - 1 - i] == turn)
                count2++;
        }
        return (count1 == 3 || count2 == 3);
    }

    private void stopGame(int statusCode) {
        unsetCellListeners();
        switch (statusCode) {
            case GameStatusCode.DRAW:
                navigation.setVisibility(View.VISIBLE);
                break;
            case GameStatusCode.O_WIN:
                player1.win();
                updateScore();
                navigation.setVisibility(View.VISIBLE);
                break;
            case GameStatusCode.X_WIN:
                player2.win();
                updateScore();
                navigation.setVisibility(View.VISIBLE);
                break;
        }

    }

}