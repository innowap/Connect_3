package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // 0 = yellow, 1 = red
    int activePlayer = 0;

    // 2 means unplayed
    //{1,0,1,0,1,}
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    // is game state active?

    boolean gameIsActive = true;

    //Arrays holding winning positions
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    //dropIn Method
    public void dropIn(View view){

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameIsActive) {
            gameState[tappedCounter] = activePlayer;
            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);

                activePlayer = 0;
            }
            counter.setTranslationY(-1000f);
            counter.animate().translationYBy(1000f)
                    .rotation(360f)
                    .setDuration(500);
        }
        for (int[] winningPosition : winningPositions){
            if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                    gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                    gameState[winningPosition[0]] != 2){

                // someone has won
                gameIsActive = false;
                String winner = "Red";
                if (gameState[winningPosition[0]] == 0){
                    winner = "Yellow";
                }
                TextView winnerMessage = findViewById(R.id.winnerMessage);
                winnerMessage.setText(winner + " has Won!");
                LinearLayout playAgainLayout = findViewById(R.id.playAgainLayout);
                playAgainLayout.setVisibility(View.VISIBLE);

            }
            else {
                boolean gameOver = true;
               for (int counterState : gameState){
                   if (counterState == 2)
                       gameOver = false;
               }
               if (gameOver){
                   TextView winnerMessage = findViewById(R.id.winnerMessage);
                   winnerMessage.setText("It's a draw!");
                   LinearLayout playAgainLayout = findViewById(R.id.playAgainLayout);
                   playAgainLayout.setVisibility(View.VISIBLE);

               }
            }
        }

    }
    public void playAgain(View view){
        gameIsActive = true;
        LinearLayout playAgainLayout = findViewById(R.id.playAgainLayout);
        playAgainLayout.setVisibility(View.INVISIBLE);

        activePlayer = 0;

            for (int i = 0; i < gameState.length; i++){
                gameState[i] = 2;
            }
        GridLayout gridLayout = findViewById(R.id.gridLayout);
            for (int i = 0; i < gridLayout.getChildCount(); i++){
                ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
            }
    }
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
