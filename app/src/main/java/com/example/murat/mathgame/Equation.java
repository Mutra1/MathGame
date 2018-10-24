package com.example.murat.mathgame;

public class Equation {
    private String equation;
    private int value;
    private double answer;
    private boolean chosen;

    public Equation(String pequation) {
        equation = pequation;
        chosen = false;
    }

    //Decreases the value of the problem. Called upon the player getting the problem incorrect.
    public void decreaseValue() {
        value--;
    }

    public void setChosen(boolean choice) {
        chosen = choice;
    }

    public void setAnswer(double panswer) {
        answer = panswer;
    }

    public void setValue(int pvalue) {
        value = pvalue;
    }

    public String getEquation() {
        return equation;
    }

    public double getAnswer() {
        return answer;
    }

    public int getValue() {
        return value;
    }

    public boolean isChosen() {
        return chosen;
    }
}