package com.example.murat.mathgame;

public class Equation {
    private String equation;
    private int value;
    private double answer;

    public Equation(String pequation, int ptype) {
        equation = pequation;
        switch(ptype) {
            case 0: setAnswer("+"); break;
            case 1: setAnswer("-"); break;
            case 2: setAnswer("*"); break;
            case 3: setAnswer("/"); break;
        }
    }

    //Calculates the answer to the equation.
    private void setAnswer(String type) {
        double num1 = Integer.parseInt(equation.substring(0, equation.indexOf(type) - 1));
        double num2 = Integer.parseInt(equation.substring(equation.indexOf(type) + 1));
        switch(type) {
            case "+": answer = num1+num2; break;
            case "-": answer = num1-num2; break;
            case "*": answer = num1*num2; break;
            case "/": answer = num1/num2; break;
        }
    }

    public String getEquation() {
        return equation;
    }

    public double getAnswer() {
        return answer;
    }

//    public void setEquation() {
//
//    }
}