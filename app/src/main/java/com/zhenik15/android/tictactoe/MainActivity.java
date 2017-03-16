package com.zhenik15.android.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "MainActivityTag";
    private Button startGameBtn;
    private EditText player1;
    private EditText player2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initAllElements();
    }

    private void initAllElements(){
        player1=(EditText)findViewById(R.id.main_player1);
        player2=(EditText)findViewById(R.id.main_player2);
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

                String name1 = player1.getText().toString();
                String name2 = player2.getText().toString();
                if (isUserNamesValid(name1,name2)){
                    Log.i(TAG, ": names :"+player1.getText() + "  -  " + player2.getText());
                    Intent intent = new Intent(getBaseContext(), GameActivity.class);
                    intent.putExtra("userName1", name1);
                    intent.putExtra("userName2", name2);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Not valid names", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private boolean isUserNamesValid(String name1, String name2){
        if (!name1.isEmpty() && !name1.isEmpty())return true;
        return false;
    }
}
