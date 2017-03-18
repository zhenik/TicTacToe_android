package com.zhenik15.android.tictactoe.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zhenik15.android.tictactoe.R;
import com.zhenik15.android.tictactoe.models.Player;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "MainActivity:> ";
    private Button startGameBtn;
    private EditText player1Name;
    private EditText player2Name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initAllElements();
    }

    private void initAllElements(){
        player1Name =(EditText)findViewById(R.id.main_player1);
        player2Name =(EditText)findViewById(R.id.main_player2);
        initButton();
    }
    private void initButton(){
        startGameBtn=(Button)findViewById(R.id.main_start_game_btn);
        startGameBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_start_game_btn:

                String name1 = player1Name.getText().toString();
                String name2 = player2Name.getText().toString();
                if (isUserNamesValid(name1,name2)){
                    Log.i(TAG, ": names :"+ player1Name.getText() + "  -  " + player2Name.getText());
                    Intent intent = new Intent(getBaseContext(), GameActivity.class);
                    intent.putExtra("player1", new Player(name1));
                    intent.putExtra("player2", new Player(name2));
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Not valid names", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private boolean isUserNamesValid(String name1, String name2){
        boolean valid = !name1.isEmpty() && !name1.isEmpty() && !"".equals(name1) && !"".equals(name2);
        if (valid)return true;
        return false;
    }
}
