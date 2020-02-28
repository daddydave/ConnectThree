package com.example.connectthree;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0 is yellow, 1 is red
    int activePlayer = 0;

    // 0 is yellow, 1 is red, 2 is empty
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8},
                                {0,3,6}, {1,4,7}, {2,5,8},
                                {0,4,8}, {2,4,6}};

    int tag;

    public void appear(View view) {
        ImageView counter = (ImageView) view;

        counter.setTranslationX(-1500);
        counter.setTranslationY(-1500);

        tag = Integer.parseInt(view.getTag().toString());
        gameState[tag] = activePlayer;

        Log.i("INFO", String.format("gameState[%d] = %d", tag, activePlayer));

        if (activePlayer == 0) {
            counter.setImageResource(R.drawable.yellow);
            activePlayer = 1;
        } else {
            counter.setImageResource(R.drawable.red);
            activePlayer = 0;
        }

        counter.animate().translationXBy(1500).translationYBy(1500).rotationBy(360).setDuration(500);

        for (int[] winningPosition : winningPositions) {
            if (gameState[winningPosition[0]] != 2 &&
                    gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                    gameState[winningPosition[1]] == gameState[winningPosition[2]]) {

                String winner;
                winner = (activePlayer == 1) ? "Yellow" : "Red"; // already flipped earlier
                Toast.makeText(this, winner + " is a winner!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
