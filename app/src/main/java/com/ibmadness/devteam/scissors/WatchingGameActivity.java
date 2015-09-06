package com.ibmadness.devteam.scissors;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by f mgregor on 06/09/2015.
 */
public class WatchingGameActivity extends Activity implements View.OnClickListener {

    Button pressGo, pressBack;
    ImageView p1Weapon, p2Weapon;
    TextView p1Score, p2Score, statusMessage, p1Name, p2Name;
    int playerInput;
    RockGame game = new RockGame(true);

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        game.reset();
        setContentView(R.layout.watchinggamelayout);
//        maybe remove these later

        pressGo = (Button) findViewById(R.id.bGo);
        p1Weapon = (ImageView) findViewById(R.id.iSelectionP1);
        p2Weapon = (ImageView) findViewById(R.id.iSelectionP2);
        p1Score = (TextView) findViewById(R.id.score1);
        p2Score = (TextView) findViewById(R.id.score2);
        p1Name = (TextView) findViewById(R.id.name1);
        p2Name = (TextView) findViewById(R.id.name2);
        statusMessage = (TextView) findViewById(R.id.tSummary);

        pressGo.setOnClickListener(this);
        playerInput = 3;  //default value is random
        p1Name.setText(game.players.get(0).name);
        p2Name.setText(game.players.get(1).name);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.bGo :
                game.update(playerInput);
                updateScreen();   // if i put all the game logic in its own class this is needed
                break;
        }
    }

    public void updateScreen(){
        if (game.round<5) {
            for (int i = 0; i < 2; i++) {

            }
            if (game.players.get(0).getChoice() == Player.PlayerChoice.Rock) {
                System.out.println("p1rock");
                p1Weapon.setImageDrawable(getResources().getDrawable(R.drawable.therockrs));
            } else if (game.players.get(0).getChoice() == Player.PlayerChoice.Paper) {
                System.out.println("p1paper");
                p1Weapon.setImageDrawable(getResources().getDrawable(R.drawable.paper));
            } else if (game.players.get(0).getChoice() == Player.PlayerChoice.Scissors) {
                System.out.println("p1sicossors");
                p1Weapon.setImageDrawable(getResources().getDrawable(R.drawable.scissors));
            }

            if (game.players.get(1).getChoice() == Player.PlayerChoice.Rock) {
                System.out.println("p2rock");
                p2Weapon.setImageDrawable(getResources().getDrawable(R.drawable.therockrs));
            } else if (game.players.get(1).getChoice() == Player.PlayerChoice.Paper) {
                System.out.println("p2paper");
                p2Weapon.setImageDrawable(getResources().getDrawable(R.drawable.paper));
            } else if (game.players.get(1).getChoice() == Player.PlayerChoice.Scissors) {
                System.out.println("p2sicossors");
                p2Weapon.setImageDrawable(getResources().getDrawable(R.drawable.scissors));
            }
            p1Score.setText(game.players.get(0).getScore());
            System.out.println("p1 score: " + game.players.get(0).getScore());
            System.out.println("p2 score: " + game.players.get(1).getScore());
            p2Score.setText(game.players.get(1).getScore());

        }else {
            statusMessage.setText(game.getSummaryMsg());
        }
    }

}
