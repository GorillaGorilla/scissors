package com.ibmadness.devteam.scissors;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by f mgregor on 06/09/2015.
 */
public class RockGame {
    Player vicVinegar = new Player(false, "Vic Vinegar");
    Player hughHoney = new Player(false, "Hugh Honey");
    Player humanP = new Player(false, "Human Player");
    List<Player> players = new ArrayList<Player>();
    int round, mode;

    public RockGame(){
        round = 0;

    }

    public void update(int playermove){


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
            round++;
        }else{
//            5 rounds complete, go to summary screen/ display results
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
