package com.example.murat.mathgame;

public class Equation {
    private String equation;
    private int value, type;
    private double answer;
    private boolean chosen;

    public Equation(String pequation, int ptype, int difficulty) {
        equation = pequation;
        type = ptype;
        chosen = false;
        value = difficulty;
        switch(type) {
            case 0: setAnswer("+"); break;
            case 1: setAnswer("-"); break;
            case 2: setAnswer("*"); break;
            case 3: setAnswer("/"); break;
        }
    }


    //Calculates the answer to the equation.
    private void setAnswer(String type) {
        double num1;
        double num2;
        if(Integer.parseInt(type) < 2) {
            num1 = Double.parseDouble(equation.substring(0, equation.indexOf(type) - 1));
            num2 = Double.parseDouble(equation.substring(equation.indexOf(type) + 1));
        }
        else {
            num1 = Math.floor(Double.parseDouble(equation.substring(0, equation.indexOf(type) - 1)));
            num2 = Math.floor(Double.parseDouble(equation.substring(equation.indexOf(type) + 1)));
        }
        switch(type) {
            case "+": answer = num1+num2; answer = changeNum(answer); break;
            case "-": answer = num1-num2; answer = changeNum(answer); break;
            case "*": answer = num1*num2; answer = changeNum(answer); break;
            case "/": answer = num1/num2; answer = changeNum(answer); break;
        }
    }

    //Rounds numbers to the tenth place.
    private double changeNum(double num) {
        num*=100;
        num+=.50;
        num = Math.floor(num);
        num/=100;
        return num;
    }

    public void setChosen(boolean choice) { chosen = choice; }

    public String getEquation() {
        return equation;
    }

    public double getAnswer() {
        return answer;
    }

    public int getValue() {
        return value;
    }

    public int getType() {
        return type;
    }

    public boolean isChosen() { return chosen; }
}