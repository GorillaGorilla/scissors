package com.ibmadness.devteam.scissors;

import java.util.Random;

/**
 * Created by f mgregor on 05/09/2015.
 */
public class Player {

    String name;
    boolean human;
    boolean chosenYet;
    int[] scores = new int[5];

    public Player(boolean human, String name){
        this.human = human;
        chosenYet = false;
        this.name = name;
    }

    public enum PlayerChoice {
        Rock,
        Paper,
        Scissors
    }
    protected PlayerChoice choice;

    public void randomSelection(){
        int r = randInt(1,3);
        switch (r) {
            case  1 :
                choice = PlayerChoice.Rock;
                chosenYet = true;
                break;
            case 2 :
                choice = PlayerChoice.Paper;
                chosenYet = true;
                break;
            case 3 :
                choice = PlayerChoice.Scissors;
                chosenYet = true;
                break;
        }
    }

    public static int randInt(int min, int max) {

        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public boolean getState(){
        return chosenYet;
    }

    public void setState(boolean chosen){
        chosenYet = chosen;
    }

    public PlayerChoice getChoice(){
        return choice;
    }
    public void resetChoice(){
        chosenYet = false;
    }

    public void addScore(int score, int round){
        scores[round] = score;
    }

    public String getScore(){
        StringBuilder sb = new StringBuilder();
        for (int i : scores){
            sb.append("[");
            sb.append(i);
            sb.append("] ");
        }
        return sb.toString();
    }


}
