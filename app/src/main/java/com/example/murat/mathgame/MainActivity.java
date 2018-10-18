package com.example.murat.mathgame;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class MainActivity extends AppCompatActivity {

    File file;
    Game game;
    TextView question1, question2, question3, question4;
    EditText editText;
    Button addition, subtraction, multiplication, division, shuffle, enterAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        file = new File(getFilesDir(), "tokensandbadges.txt");
        game = new Game();



        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.createNewEquationList(0);
                showProblems();
            }
        });

        subtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.createNewEquationList(1);
                showProblems();
            }
        });

        multiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.createNewEquationList(2);
                showProblems();
            }
        });

        division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.createNewEquationList(3);
                showProblems();
            }
        });

        shuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.createNewEquationList(game.getEquationList().get(0).getType());
                showProblems();
            }
        });

        enterAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(Double.parseDouble((editText.getText().toString())));
            }
        });



        question1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question1.setTextColor(0xFF2FBD45);
                question2.setTextColor(0xFFCE8327);
                question3.setTextColor(0xFFCE8327);
                question4.setTextColor(0xFFCE8327);
                game.setChosen(0);
            }
        });

        question2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question1.setTextColor(0xFFCE8327);
                question2.setTextColor(0xFF2FBD45);
                question3.setTextColor(0xFFCE8327);
                question4.setTextColor(0xFFCE8327);
                game.setChosen(1);
            }
        });

        question3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question1.setTextColor(0xFFCE8327);
                question2.setTextColor(0xFFCE8327);
                question3.setTextColor(0xFF2FBD45);
                question4.setTextColor(0xFFCE8327);
                game.setChosen(2);
            }
        });

        question4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question1.setTextColor(0xFFCE8327);
                question2.setTextColor(0xFFCE8327);
                question3.setTextColor(0xFFCE8327);
                question4.setTextColor(0xFF2FBD45);
                game.setChosen(3);
            }
        });



//        loadProgress();
    }


    //Checks to see if the answer is correct. If it is, it updates the labels to show their tokens.
    private void checkAnswer(double guess) {
        if(game.getChosenEquation() != null) {
            if(game.checkAnswer(guess)) {
                game.setTokens(game.getTokens() + (1 + game.getChosenEquation().getValue()));
                //saveProgress();
                //update token labels
            }
        }
    }


    //changes the textviews to be each of the problems
    private void showProblems() {
        question1.setText(game.getEquationList().get(0).getEquation());
        question2.setText(game.getEquationList().get(1).getEquation());
        question3.setText(game.getEquationList().get(2).getEquation());
        question4.setText(game.getEquationList().get(3).getEquation());
    }


    //load function
    private void loadProgress() {
        try {
            BufferedReader bf = new BufferedReader(new FileReader(file));
            String line;
            while((line = bf.readLine()) != null) {
                System.out.println("line: " + line);
                if(line.substring(0,1).equals("t")) {
                    game.setTokens(Integer.parseInt(line.substring(1)));
                }
                else {
                    game.setBadges(Integer.parseInt(line.substring(1)));
                }
            }
            bf.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    //save function
    private void saveProgress() {
        System.out.println("Saving");
        try {
            FileOutputStream fileOutputStream = openFileOutput(file.getName(), Context.MODE_PRIVATE);
            fileOutputStream.write("t".getBytes());
            fileOutputStream.write(game.getTokens());
            fileOutputStream.write("\n".getBytes());
            fileOutputStream.write("b".getBytes());
            fileOutputStream.write(game.getBadges());
            fileOutputStream.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}