package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //0=Yellow 1=red

    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int activePlayer=0;
    boolean gameActive=true;

    public void fadeIn(View view) {
        ImageView coin = (ImageView) view;
        int tappedCounter = Integer.parseInt(coin.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameActive) {
            gameState[tappedCounter] = activePlayer;
            coin.setTranslationY(-1500);
            coin.animate().translationYBy(1500).rotation(3600).setDuration(300);


            if (activePlayer == 0) {
                coin.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                coin.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            for (int[] winningPosition : winningPositions) {

                String winner = "";
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    //some one won
                    gameActive=false;
                    if (activePlayer == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }
                    //Toast.makeText(this, winner + " has won", Toast.LENGTH_SHORT).show();
                    Button playAgainButton=(Button)findViewById(R.id.playAgainButton);
                    TextView winnerTextView=(TextView) findViewById(R.id.winnerTextView);
                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setText(winner + " has won");
                    winnerTextView.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void replay(View view){
        Button playAgainButton=(Button)findViewById(R.id.playAgainButton);
        TextView winnerTextView=(TextView) findViewById(R.id.winnerTextView);
        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);
        //EMPTY THE LAYOUT AND PLAY AGAIN
        GridLayout gridLayout=(GridLayout) findViewById(R.id.gridLayout);

        for(int i =0;i<gridLayout.getChildCount();i++){
            ImageView coin=(ImageView) gridLayout.getChildAt(i);
            //REMOVE SOURCE IMAGE FROM  VIEW..
            coin.setImageDrawable(null);
            }

        //NEED TO MAKE DEFAULT THE GAME STATE AND ACTIVE PLAYERS...)
        //gameState={2,2,2,2,2,2,2,2,2}; WE CAN'T CHANGE SIMPLY ARRAY TO DEFAULT SO WE NEED TO MAKE LOOP
        for( int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        activePlayer=0;
        gameActive=true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}