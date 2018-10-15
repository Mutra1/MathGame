package com.example.murat.mathgame;

import java.util.List;

public class Game {
    private int tokens, badges;
    private List<Equation> equationList;

    public Game() {
        tokens = 0;
        badges = 0;
    }

    public void setTokens(int newtokens) {
        tokens = newtokens;
    }

    public void setBadges(int newbadges) {
        badges = newbadges;
    }

    //Makes a new list of equations
    public void createNewEquationList(int type) {
        equationList.clear();
        for(int e = 0; e < 4; e++) {
            equationList.add(new Equation(createEquation(type)));
        }
    }

    //Creates the "equation"
    private String createEquation(int type) {
        String equation = "";
        double num1 = (Math.random() * 19) + 1;
        double num2 = (Math.random() * 19) + 1;
        System.out.println("\nnum1: " + num1);
        System.out.println("num2: " + num2);
        equation += (num1 + " ");
        switch(type) {
            case 0: equation += "+ "; break;
            case 1: equation += "- "; break;
            case 2: equation += "* "; break;
            case 3: equation += "/ "; break;
            default: break;
        }
        equation += num2;
        System.out.println("Equation: " + equation);
        return equation;
    }

    public int getTokens() {
        return tokens;
    }

    public int getBadges() {
        return badges;
    }

    public List<Equation> getEquationList() {
        return equationList;
    }
}