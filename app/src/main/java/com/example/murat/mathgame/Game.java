package com.example.murat.mathgame;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private int tokens, badges, problemtype;
    private List<Equation> equationList;
    private List<String> functionList;

    public Game() {
        tokens = 0;
        badges = 0;
        equationList = new ArrayList<>();
        functionList = new ArrayList<>();
        functionList.add("+");
        functionList.add("-");
        functionList.add("*");
        functionList.add("/");
    }


    //Makes a new list of equations
    public void createNewEquationList(int type) {
        problemtype = type;
        equationList.clear();
        for(int e = 0; e < 4; e++) {
            equationList.add(new Equation(createEquation(e)));
            equationList.get(e).setAnswer(getEquationAnswer(functionList.get(problemtype), e));
            equationList.get(e).setValue(getDifficulty(functionList.get(type), problemtype, e));
        }
    }


    //Creates the "equation"
    private String createEquation(int problem) {
        //Creates numbers based on the difficulty, which is decided on problem order and problem type.

        if(problemtype != 3) {
            String equation = "";
            double num1;
            double num2;

            //Addition and Subtraction
            if (problemtype < 2) {
                if (problem == 0) {
                    num1 = Math.round((Math.random() * 19) + 1);
                    num2 = Math.round((Math.random() * 19) + 1);
                }
                else if (problem == 1) {
                    if (Math.random() >= 0.5) {
                        num1 = Math.round((Math.random() * 19) + 1);
                        num2 = (Math.random() * 19) + 1;
                    }
                    else {
                        num1 = (Math.random() * 19) + 1;
                        num2 = Math.round((Math.random() * 19) + 1);
                    }
                }
                else {
                    num1 = (Math.random() * 19) + 1;
                    num2 = (Math.random() * 19) + 1;
                }
                num1 = roundNum(num1);
                num2 = roundNum(num2);
            }

            //Multiplication
            else {
                if (problem == 0) {
                    num1 = Math.round((Math.random() * 9) + 1);
                    num2 = Math.round((Math.random() * 9) + 1);
                }
                else if (problem == 1) {
                    if (Math.random() >= 0.5) {
                        num1 = Math.round((Math.random() * 9) + 1);
                        num2 = Math.round((Math.random() * 11) + 1);
                    }
                    else {
                        num1 = Math.round((Math.random() * 11) + 1);
                        num2 = Math.round((Math.random() * 9) + 1);
                    }
                }
                else {
                    num1 = Math.round((Math.random() * 11) + 1);
                    num2 = Math.round((Math.random() * 11) + 1);
                }
            }

            equation += (num1 + " ");
            switch (problemtype) {
                case 0:
                    equation += "+ ";
                    break;
                case 1:
                    equation += "- ";
                    break;
                case 2:
                    equation += "* ";
                    break;
                default:
                    break;
            }
            equation += num2;
            return equation;
        }

        //Division
        int num2;
        int answer;
        switch(problem) {
            case 0:
                num2 = (int)(Math.random() * 4) + 1;
                answer = (int)(Math.random() * 4) + 1;
                break;
            case 1:
                num2 = (int)(Math.random() * 4) + 1;
                answer = (int)(Math.random() * 11) + 1;
                break;
            default:
                num2 = (int)(Math.random() * 11) + 1;
                answer = (int)(Math.random() * 11) + 1;
        }
        int num1 = num2 * answer;
        return (num1 + " / " + num2);
    }


    //Calculates the answer to the equation.
    private double getEquationAnswer(String ptype, int problem) {
        double num1;
        double num2;
        double answer;
        num1 = Double.parseDouble(equationList.get(problem).getEquation().substring(0, equationList.get(problem).getEquation().indexOf(ptype) - 1));
        num2 = Double.parseDouble(equationList.get(problem).getEquation().substring(equationList.get(problem).getEquation().indexOf(ptype) + 1));
        switch (ptype) {
            case "+":
                answer = num1 + num2;
                break;
            case "-":
                answer = num1 - num2;
                break;
            case "*":
                answer = num1 * num2;
                break;
            default:
                answer = num1 / num2;
                break;
        }
        answer = roundNum(answer);
        return answer;
    }


    //Sets the difficulty.
    private int getDifficulty(String ptype, int type, int problem) {
        String num1 = equationList.get(problem).getEquation().substring(0, equationList.get(problem).getEquation().indexOf(ptype) - 1);
        String num2 = equationList.get(problem).getEquation().substring(equationList.get(problem).getEquation().indexOf(ptype) + 2);


        //Check for addition/subtraction
        if (type < 2) {
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

        //Check for multiplication
        else if (type == 2) {
            //Easy check
            if (num1.substring(0, num1.indexOf(".")).length() == 1 && num2.substring(0, num2.indexOf(".")).length() == 1) {
                System.out.println("achoice: 1");
                return 1;
            }

            //Hard check
            else if (num1.substring(0, num1.indexOf(".")).length() > 1 && num2.substring(0, num2.indexOf(".")).length() > 1) {
                System.out.println("achoice: 5");
                return 5;
            }
            System.out.println("achoice: 3");
            return 3;
        }

        else {
            //check
            if(equationList.get(problem).getAnswer() <= 10) {
                if(Integer.parseInt(num1) < 10 && Integer.parseInt(num2) < 10) {
                    System.out.println("1");
                    return 1;
                }
                else {
                    System.out.println("3");
                    return 3;
                }
            }
            else {
                System.out.println("5");
                return 5;
            }
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

    //Rounds numbers to the quarter space
    private double roundNum(double num) {
        num = (Math.floor((num+(1/(4*2)))*4))/4;
        return num;
    }

    public int getProblemType() {
        return problemtype;
    }

    public void setTokens(int newtokens) {
        tokens = newtokens;
    }

    public void setBadges(int newbadges) {
        badges = newbadges;
    }

    public Byte getTokens() {
        return (byte)tokens;
    }

    public Byte getBadges() {
        return (byte)badges;
    }

    public List<Equation> getEquationList() {
        return equationList;
    }
}