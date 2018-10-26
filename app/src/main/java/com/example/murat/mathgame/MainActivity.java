package com.example.murat.mathgame;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.EditText;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;

public class MainActivity extends AppCompatActivity {

    Game game;
    TextView question1, question2, question3, question4, tokenView, shopTokens, infoLabel, badgeCount;
    EditText editText;
    Button addition, subtraction, multiplication, division, shuffle, enterAnswer, shopButton, purchaseButton, returnButton;
    ImageView badgeShow;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        file = new File(getFilesDir(), "token.txt");
        game = new Game();
        showGame();
    }


    //Changes xml to game
    private void showGame() {
        setContentView(R.layout.activity_main);
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
        badgeCount = (TextView)findViewById(R.id.badgeCount);
        badgeShow = (ImageView)findViewById(R.id.badgeShow);


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
                game.createNewEquationList(game.getProblemType());
                showProblems();
            }
        });

        enterAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(Double.parseDouble((editText.getText().toString())));
            }
        });

        shopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShop();
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


        String tokens = "Tokens: " + (int)game.getTokens();
        String badges = "X     " + (int)game.getBadges();
        tokenView.setText(tokens);
        badgeCount.setText(badges);
    }


    //Changes xml to shop
    private void showShop() {
        setContentView(R.layout.shop);
        purchaseButton = (Button)findViewById(R.id.purchaseButton);
        returnButton = (Button)findViewById(R.id.returnButton);
        infoLabel = (TextView)findViewById(R.id.infoLabel);
        shopTokens = (TextView)findViewById(R.id.shopTokens);

        purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((int)game.getTokens() >= 15) {
                    game.setBadges((int)game.getBadges() + 1);
                    game.setTokens((int)game.getTokens() - 15);
                    String text = "Tokens: " + (int)game.getTokens();
                    shopTokens.setText(text);
                }
            }
        });

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGame();
            }
        });
        String text = "Tokens: " + (int)game.getTokens();
        shopTokens.setText(text);
    }


    //Checks to see if the answer is correct. If it is, it updates the labels to show their tokens.
    private void checkAnswer(double guess) {
        if(game.getChosenEquation() != null) {
            if(game.checkAnswer(guess)) {
                editText.setText("");
                game.setTokens(game.getTokens() + (1 + game.getChosenEquation().getValue()));
                String text = "Tokens: " + game.getTokens();
                tokenView.setText(text);
                game.createNewEquationList(game.getProblemType());
                showProblems();
            }
            else {
                game.getChosenEquation().decreaseValue();
                editText.setTextColor(Color.RED);
            }
        }
    }


    //changes the text views to be each of the problems
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


    //reads file
    private void readFile() {
        StringBuilder read = new StringBuilder();
        try {
            FileInputStream inputStream = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while((line = br.readLine()) != null) {
                read.append(line);
            }
            br.close();
            String data = new String(read);
            System.out.println("\n\n\nData: " + data);
            loadTokens(data);
        }
        catch(Exception e) {
            System.out.println("whoops");
            e.printStackTrace();
        }
    }


    //loads in tokens
    private void loadTokens(String data) {
        game.setTokens(Integer.parseInt(data.substring(0,data.indexOf("b"))));
        game.setBadges(Integer.parseInt(data.substring(data.indexOf("b"))));
    }


    //save function
    private void saveTokens() {
        try {
            FileOutputStream outputStream = openFileOutput(file.getName(), Context.MODE_PRIVATE);
            outputStream.write(game.getTokens());
            outputStream.write(("b").getBytes());
            outputStream.write(game.getBadges());
            outputStream.close();
        }
        catch(Exception e) {
            System.out.println("save failed");
        }
    }
}