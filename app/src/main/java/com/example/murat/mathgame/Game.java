package com.example.murat.mathgame;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private int tokens, badges;
    private List<Equation> equationList;

    public Game() {
        tokens = 0;
        badges = 0;
        equationList = new ArrayList<>();
    }


    //Makes a new list of equations
    public void createNewEquationList(int type) {
        equationList.clear();
        for(int e = 0; e < 4; e++) {
            equationList.add(new Equation(createEquation(type), type));
        }
    }


    //Sets the equation to be chosen, and the rest of the equations to not be chosen.
    public void setChosen(int choice) {
        for(int i = 0; i < equationList.size(); i++) {
            if(i == choice) {
                equationList.get(i).setChosen(true);
            }
            else {
                equationList.get(i).setChosen(false);
            }
        }
    }


    //Creates the "equation"
    private String createEquation(int type) {
        String equation = "";
        double num1 = (Math.random() * 19) + 1;
        double num2 = (Math.random() * 19) + 1;
//        System.out.println("\nnum1: " + num1);
//        System.out.println("num2: " + num2);
        num1 = roundQuarter(num1);
        num2 = roundQuarter(num2);
        equation += (num1 + " ");
        switch(type) {
            case 0: equation += "+ "; break;
            case 1: equation += "- "; break;
            case 2: equation += "* "; break;
            case 3: equation += "/ "; break;
            default: break;
        }
        equation += num2;
        return equation;
    }


    //Checks to see if the answer the player gave is the same as the real answer.
    public boolean checkAnswer(double guess) {
        if(guess == getChosenEquation().getAnswer()) {
            return true;
        }
        return false;
    }


    //Returns the equation that has been selected.
    public Equation getChosenEquation() {
        for(Equation equation : equationList) {
            if(equation.isChosen()) {
                return equation;
            }
        }
        return null;
    }


    //Rounds numbers to the tenth place.
//    private double changeNum(double num) {
//        num*=100;
//        num+=.50;
//        num = Math.floor(num);
//        num/=100;
//        return num;
//    }

    //Rounds numbers to the quarter space
    private double roundQuarter(double num) {
        System.out.println("\nnum: " + num);
        num = (Math.floor((num+0.12)*4))/4;
        return num;
    }

    public void setTokens(int newtokens) {
        tokens = newtokens;
    }

    public void setBadges(int newbadges) {
        badges = newbadges;
    }

    public int getTokens() {
        System.out.println("tokens: " + tokens);
        return tokens;
    }

    public int getBadges() {
        return badges;
    }

    public List<Equation> getEquationList() {
        return equationList;
    }
}