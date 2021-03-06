package com.ibmadness.devteam.scissors;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by f mgregor on 05/09/2015.
 */
public class GameActivity extends Activity implements View.OnClickListener {

    int round, mode, playerInput;
    Button pressGo, pressRock, pressPaper, pressScissors, pressBack;
    ImageView p1Weapon, p2Weapon;
    TextView p1Score, p2Score, p1Name, p2Name, statusMessage;

    RockGame game = new RockGame(false);

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        mode = 0;
        game.reset();
//        use mode variable to determine whether human or computer controlled
        setContentView(R.layout.gamelayout);
//        maybe remove these later
        pressRock = (Button) findViewById(R.id.bRock);
        pressPaper = (Button) findViewById(R.id.bPaper);
        pressScissors = (Button) findViewById(R.id.bScissors);
        pressGo = (Button) findViewById(R.id.bGo);
        p1Weapon = (ImageView) findViewById(R.id.iSelectionP1);
        p2Weapon = (ImageView) findViewById(R.id.iSelectionP2);
        p1Score = (TextView) findViewById(R.id.score1);
        p2Score = (TextView) findViewById(R.id.score2);
        p1Name = (TextView) findViewById(R.id.name1);
        p2Name = (TextView) findViewById(R.id.name2);
        statusMessage = (TextView) findViewById(R.id.tSummary);
        pressRock.setOnClickListener(this);
        pressPaper.setOnClickListener(this);
        pressScissors.setOnClickListener(this);
        pressGo.setOnClickListener(this);
        playerInput = 3;  //default value
        p1Name.setText(game.players.get(0).name);
        p2Name.setText(game.players.get(1).name);
    }

    public void update(){

        game.update(playerInput);
    }

    public void updateScreen(){

        if (game.round < 5) {
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
        }else{
            statusMessage.setText(game.getSummaryMsg());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bRock :
                playerInput = 0;
                break;
            case R.id.bPaper :
                playerInput = 1;
                break;
            case R.id.bScissors :
                playerInput = 2;
                break;
            case  R.id.bGo :
                update();
                updateScreen();   // if i put all the game logic in its own class this is needed
                break;
        }
    }

    public int whoWins(Player p1, Player p2){
//        calculates who wins out of 2 players choices.
//        returns an int, 0 is draw, 1 is p1, 2 is p2, 4 is error
        if (p1.choice == Player.PlayerChoice.Rock){
            if (p2.choice == Player.PlayerChoice.Rock){
                return 0;
            }else if(p2.choice == Player.PlayerChoice.Paper){
                return 2;
            }else if (p2.choice == Player.PlayerChoice.Scissors){
                return 1;
            }

        }else if(p1.choice == Player.PlayerChoice.Paper){
            if (p2.choice == Player.PlayerChoice.Rock){
                return 1;
            }else if(p2.choice == Player.PlayerChoice.Paper){
                return 0;
            }else if (p2.choice == Player.PlayerChoice.Scissors){
                return 2;
            }

        }else if (p1.choice == Player.PlayerChoice.Scissors){
            if (p2.choice == Player.PlayerChoice.Rock){
                return 2;
            }else if(p2.choice == Player.PlayerChoice.Paper){
                return 1;
            }else if (p2.choice == Player.PlayerChoice.Scissors){
                return 0;
            }

        }
        return 4;
    }

}
