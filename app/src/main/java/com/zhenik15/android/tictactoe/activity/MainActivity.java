package com.zhenik15.android.tictactoe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zhenik15.android.tictactoe.R;
import com.zhenik15.android.tictactoe.model.Player;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "MainActivity:> ";

    private static final String PLAYER1 = "player1";
    private static final String PLAYER2 = "player2";

    private Button startGameBtn;
    private Button top3Btn;
    private EditText player1Name;
    private EditText player2Name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initAllElements();
    }

    private void initAllElements() {
        player1Name = (EditText) findViewById(R.id.main_player1);
        player2Name = (EditText) findViewById(R.id.main_player2);
        initButtons();
    }

    private void initButtons() {
        startGameBtn = (Button) findViewById(R.id.main_start_game_btn);
        startGameBtn.setOnClickListener(this);
        top3Btn = (Button)findViewById(R.id.main_top3_btn);
        top3Btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_start_game_btn:

                String name1 = player1Name.getText().toString();
                String name2 = player2Name.getText().toString();
                if (isUserNamesValid(name1, name2)) {
                    Log.i(TAG, ": names :" + player1Name.getText() + "  -  " + player2Name.getText());
                    Intent intent = new Intent(getBaseContext(), GameActivity.class);
                    intent.putExtra(PLAYER1, new Player(name1));
                    intent.putExtra(PLAYER2, new Player(name2));
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Not valid names", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.main_top3_btn:

                Intent intent = new Intent(getBaseContext(), Best3Activity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * YODA-style of equal
     * */
    private boolean isUserNamesValid(String name1, String name2) {
        boolean valid = !name1.isEmpty() && !name1.isEmpty() && !"".equals(name1) && !"".equals(name2);
        if (valid) return true;
        return false;
    }
}