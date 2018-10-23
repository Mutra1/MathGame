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
        String num1 = equation.substring(0, equation.indexOf(ptype) - 1);
        String num2 = equation.substring(equation.indexOf(ptype) + 1);
        System.out.println("Num1: " + num1);
        System.out.println("num2: "+ num2);


        //Check for addition/subtraction
        if(type < 2) {
            //Easy check
            if (num1.substring(num1.indexOf(".") + 1).equals("0") && num2.substring(num2.indexOf(".") + 1).equals("0")) {
                return 1;
            }

            //Hard check
            else if (!num1.substring(num1.indexOf(".") + 1).equals("0") && !num2.substring(num2.indexOf(".") + 1).equals("0")) {
                return 5;
            }
            return 3;
        }

        //Check for multiplication/division
        else {
            System.out.println("\nwhat?" + num1.substring(0, num1.indexOf(".")));
            System.out.println("excuse me?: " + num2.substring(0, num2.indexOf(".")));
            System.out.println("test1: " + num1.substring(0, num1.indexOf(".")).length());
            System.out.println("test2: " + num2.substring(0, num2.indexOf(".")).length());
            //Easy check
            if(num1.substring(0, num1.indexOf(".")).length() == 2 && num2.substring(0, num2.indexOf(".")).length() == 2) {
                System.out.println("achoice: 1");
                return 1;
            }

            //Hard check
            else if(num1.substring(0, num1.indexOf(".")).length() > 2 && num2.substring(0, num2.indexOf(".")).length() > 2) {
                System.out.println("achoice: 5");
                return 5;
            }
            System.out.println("achoice: 3");
            return 3;
        }
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