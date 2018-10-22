package com.example.murat.mathgame;

public class Equation {
    private String equation;
    private int value, type;
    private double answer;
    private boolean chosen;

    public Equation(String pequation, int ptype) {
        equation = pequation;
        type = ptype;
        chosen = false;
        switch(type) {
            case 0: setAnswer("+"); value = getDifficulty("+"); break;
            case 1: setAnswer("-"); value = getDifficulty("-"); break;
            case 2: setAnswer("*"); value = getDifficulty("*"); break;
            case 3: setAnswer("/"); value = getDifficulty("/"); break;
        }
    }


    //Calculates the answer to the equation.
    private void setAnswer(String ptype) {
        double num1;
        double num2;
        num1 = Double.parseDouble(equation.substring(0, equation.indexOf(ptype) - 1));
        num2 = Double.parseDouble(equation.substring(equation.indexOf(ptype) + 1));
        switch(ptype) {
            case "+": answer = num1+num2; answer = changeNum(answer); System.out.println(num1 + " + " + num2 + " = " + answer); break;
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

    //Sets the difficulty.
    private int getDifficulty(String ptype) {
        String num1 = equation.substring(equation.indexOf(ptype) + 1);
        String num2 = equation.substring(0, equation.indexOf(ptype) - 1);
        System.out.println("\n\nnum1: " + num1);
        System.out.println("num2: " + num2);
        System.out.println("\nnum1r: " + num1.substring(num1.indexOf(".")));
        System.out.println("num2r: " + num2.substring(num2.indexOf(".")));

        //Easy check
        if(num1.substring(num1.indexOf(".")).equals("0") && num2.substring(num2.indexOf(".")).equals("0")) {
            System.out.println("\nchoice: 1");
            return 1;
        }

        //Hard check
        else if(!num1.substring(num1.indexOf(".")).equals("0") && !num2.substring(num2.indexOf(".")).equals("0")) {
            System.out.println("choice: 5");
            return 5;
        }
        System.out.println("choice: 3");
        return 3;
    }

    //Decreases the value of the problem.
    public void decreaseValue() {
        value--;
    }

    public void setChosen(boolean choice) {
        chosen = choice;
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

    public int getType() {
        return type;
    }

    public boolean isChosen() {
        return chosen;
    }
}