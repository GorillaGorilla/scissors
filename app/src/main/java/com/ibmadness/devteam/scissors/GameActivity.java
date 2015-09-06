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

    Player vicVinegar = new Player(false, "Vic Vinegar");
    Player hughHoney = new Player(false, "Hugh Honey");
    Player humanP = new Player(false, "Human Player");
    List<Player> players = new ArrayList<Player>();
    int round, mode;
    Button pressGo, pressRock, pressPaper, pressScissors, pressBack;
    ImageView p1Weapon, p2Weapon;
    TextView p1Score, p2Score;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        mode = 0;
        if (extras != null) {
            mode = extras.getInt("mode");
            System.out.println("mode " + mode);
        }
//        use mode variable to determine whether human or computer controlled
        if (mode == 1){
            players.add(vicVinegar);
            players.add(hughHoney);
        }else{
            players.add(vicVinegar);
            players.add(humanP);
        }
        setContentView(R.layout.gamelayout);
//        maybe remove these later
        pressRock = (Button) findViewById(R.id.bRock);
        pressPaper = (Button) findViewById(R.id.bPaper);
        pressScissors = (Button) findViewById(R.id.bScissors);
        pressGo = (Button) findViewById(R.id.bGo);
        pressBack = (Button) findViewById(R.id.bBack);
        p1Weapon = (ImageView) findViewById(R.id.iSelectionP1);
        p2Weapon = (ImageView) findViewById(R.id.iSelectionP2);
        p1Score = (TextView) findViewById(R.id.score1);
        p2Score = (TextView) findViewById(R.id.score2);
        pressRock.setOnClickListener(this);
        pressPaper.setOnClickListener(this);
        pressScissors.setOnClickListener(this);
        pressGo.setOnClickListener(this);
        pressBack.setOnClickListener(this);
        round = 0;
    }

    public void update(){
        if (round < 5) {
//        cycle player
            for (Player p : players) {
//            if not chosen yet is either computer or human wants to pick random
                if (!p.getState()) {
//                    make random selection for player
                    p.randomSelection();
                }
            }
//        find out who won
            int winner = whoWins(players.get(0), players.get(1));
            switch  (winner) {
                case 1 :
                    players.get(0).addScore(1,round);
                    players.get(1).addScore(-1,round);
                    break;
                case 2 :
                    players.get(0).addScore(-1,round);
                    players.get(1).addScore(1,round);
                    break;
                case 3 :
                    players.get(0).addScore(0,round);
                    players.get(1).addScore(0,round);
                    break;
                case 4 :
//                    returns error in which case its recorded as a draw
                    System.out.println("whoWins returned an error... posting from update");
                    players.get(0).addScore(0,round);
                    players.get(1).addScore(0,round);
                    break;
            }
            players.get(0).resetChoice();
            players.get(1).resetChoice();

            updateScreen();
            round++;
        }else{
//            5 rounds complete, go to summary screen/ display results
        }
    }

    public void updateScreen(){
        for (int i =0; i <2; i++) {

        }
        if (players.get(0).getChoice()==Player.PlayerChoice.Rock) {
            System.out.println("p1rock");
            p1Weapon.setImageDrawable(getResources().getDrawable(R.drawable.therockrs));
        } else if (players.get(0).getChoice()==Player.PlayerChoice.Paper){
            System.out.println("p1paper");
            p1Weapon.setImageDrawable(getResources().getDrawable(R.drawable.paper));
        } else if (players.get(0).getChoice()==Player.PlayerChoice.Scissors){
            System.out.println("p1sicossors");
            p1Weapon.setImageDrawable(getResources().getDrawable(R.drawable.scissors));
        }

        if (players.get(1).getChoice()==Player.PlayerChoice.Rock) {
            System.out.println("p2rock");
            p2Weapon.setImageDrawable(getResources().getDrawable(R.drawable.therockrs));
        } else if (players.get(1).getChoice()==Player.PlayerChoice.Paper){
            System.out.println("p2paper");
            p2Weapon.setImageDrawable(getResources().getDrawable(R.drawable.paper));
        } else if (players.get(1).getChoice()==Player.PlayerChoice.Scissors){
            System.out.println("p2sicossors");
            p2Weapon.setImageDrawable(getResources().getDrawable(R.drawable.scissors));
        }
        p1Score.setText(players.get(0).getScore());
        System.out.println("p1 score: "+players.get(0).getScore());
        System.out.println("p2 score: "+players.get(1).getScore());
        p2Score.setText(players.get(1).getScore());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bRock :
                humanP.choice = Player.PlayerChoice.Rock;
                humanP.setState(true);
                break;
            case R.id.bPaper :
                humanP.choice = Player.PlayerChoice.Paper;
                humanP.setState(true);
                break;
            case R.id.bScissors :
                humanP.choice = Player.PlayerChoice.Scissors;
                humanP.setState(true);
                break;
            case  R.id.bGo :
                update();
//                updateScreen();   // if i put all the game logic in its own class this is needed
                break;
            case R.id.bBack :
                try {
                    Intent ourIntent = new Intent("android.intent.action.MAIN");
                    startActivity(ourIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
