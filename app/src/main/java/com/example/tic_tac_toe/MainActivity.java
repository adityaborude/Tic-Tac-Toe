package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    //0: X, 1: O
    int activePlayer = 0;
    int[] positions = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    String msg;
    boolean gameActive = true;

    public boolean positionsIsFull() {
        boolean flag = true;
        for(int x: positions)
            if(x==-1) {
                flag = false;
                break;
            }
        return flag;
    }

    public void put(View view) throws InterruptedException {
        TextView message = (TextView) findViewById(R.id.textView);
        ImageView block = (ImageView) view;
        Button button = (Button)findViewById(R.id.button);
        block.setTranslationY(-1500);
        int cell = Integer.parseInt(block.getTag().toString());
        if(positions[cell]==-1 && gameActive) {
            positions[cell] = activePlayer;
            if (activePlayer == 0) {
                block.setImageResource(R.drawable.x_image);
                activePlayer = 1;
            } else {
                block.setImageResource(R.drawable.o_image);
                activePlayer = 0;
            }
            block.animate().translationYBy(1500).setDuration(500);
            for (int[] i : winningPositions) {
                if (positions[i[0]] == positions[i[1]] && positions[i[1]] == positions[i[2]] && positions[i[0]] != -1) {
                    if (activePlayer == 1)
                        msg = "X has won!";
                    else
                        msg = "O has won!";
                    message.setText(msg);
                    gameActive = false;
                    message.setVisibility(View.VISIBLE);
                    button.setVisibility(View.VISIBLE);
                }
            }
            if(positionsIsFull()) {
                msg = "Game Tied!";
                message.setText(msg);
                gameActive = false;
                message.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
            }
        }
    }

    public void newGame(View view) {
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        TextView message = (TextView) findViewById(R.id.textView);
        Button button = (Button)findViewById(R.id.button);
        button.setVisibility(View.INVISIBLE);
        message.setVisibility(View.INVISIBLE);
        Arrays.fill(positions, -1);
        for(int i = 0; i<gridLayout.getChildCount(); i++) {
            ImageView block = (ImageView) gridLayout.getChildAt(i);
            block.setImageResource(0);
        }
        msg = "";
        activePlayer = 0;
        gameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
