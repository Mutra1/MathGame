package com.example.murat.mathgame;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class MainActivity extends AppCompatActivity {

    Game game;
    TextView question1, question2, question3, question4, tokenView;
    EditText editText;
    Button addition, subtraction, multiplication, division, shuffle, enterAnswer, shopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game = new Game();
        addition = (Button)findViewById(R.id.addition);
        subtraction = (Button)findViewById(R.id.subtraction);
        multiplication = (Button)findViewById(R.id.multiplication);
        division = (Button)findViewById(R.id.division);
        shuffle = (Button)findViewById(R.id.shuffle);
        enterAnswer = (Button)findViewById(R.id.enterAnswer);
        shopButton = (Button)findViewById(R.id.shopButton);
        editText = (EditText)findViewById(R.id.editText);
        question1 = (TextView)findViewById(R.id.question1);
        question2 = (TextView)findViewById(R.id.question2);
        question3 = (TextView)findViewById(R.id.question3);
        question4 = (TextView)findViewById(R.id.question4);
        tokenView = (TextView)findViewById(R.id.tokenView);


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


        String tokens = game.getTokens() + "";
        tokenView.setText(tokens);
    }


    //Checks to see if the answer is correct. If it is, it updates the labels to show their tokens.
    private void checkAnswer(double guess) {
        if(game.getChosenEquation() != null) {
            if(game.checkAnswer(guess)) {
                System.out.println("wow");
                editText.setText("");
                game.setTokens(game.getTokens() + (1 + game.getChosenEquation().getValue()));
                String text = game.getTokens() + "";
                tokenView.setText(text);
                game.createNewEquationList(game.getEquationList().get(0).getType());
                showProblems();
            }
            else {
                game.getChosenEquation().setValue(game.getChosenEquation().getValue()-1);
                editText.setTextColor(Color.RED);

            }
        }
    }


    //changes the textviews to be each of the problems
    private void showProblems() {
        question1.setText(game.getEquationList().get(0).getEquation());
        question2.setText(game.getEquationList().get(1).getEquation());
        question3.setText(game.getEquationList().get(2).getEquation());
        question4.setText(game.getEquationList().get(3).getEquation());
        question1.setTextColor(0xFFCE8327);
        question2.setTextColor(0xFFCE8327);
        question3.setTextColor(0xFFCE8327);
        question4.setTextColor(0xFFCE8327);
    }
}