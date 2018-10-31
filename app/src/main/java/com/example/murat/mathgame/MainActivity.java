package com.example.murat.mathgame;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
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
    TextView question1, question2, question3, question4, tokenView, shopTokens, infoLabel, badgeCount, increaseView;
    EditText editText;
    Button addition, subtraction, multiplication, division, shuffle, enterAnswer, shopButton, purchaseButton, returnButton;
    ImageView badgeShow, tokenShow;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        file = new File(getFilesDir(), "token.txt");
        game = new Game();
        showGame();
        readFile();
    }


    //Changes xml to game
    private void showGame() {
        setContentView(R.layout.activity_main);
        addition = (Button)findViewById(R.id.addition);
        addition.setBackgroundColor(0xFF3B67DB);
        addition.setTextColor(0xFFF1F1F1);
        subtraction = (Button)findViewById(R.id.subtraction);
        subtraction.setBackgroundColor(0xFF3B67DB);
        subtraction.setTextColor(0xFFF1F1F1);
        multiplication = (Button)findViewById(R.id.multiplication);
        multiplication.setBackgroundColor(0xFF3B67DB);
        multiplication.setTextColor(0xFFF1F1F1);
        division = (Button)findViewById(R.id.division);
        division.setBackgroundColor(0xFF3B67DB);
        division.setTextColor(0xFFF1F1F1);
        shuffle = (Button)findViewById(R.id.shuffle);
        shuffle.setBackgroundColor(0xFF3B67DB);
        shuffle.setTextColor(0xFFF1F1F1);
        enterAnswer = (Button)findViewById(R.id.enterAnswer);
        enterAnswer.setBackgroundColor(0xFF3EC563);
        enterAnswer.setTextColor(0xFFF1F1F1);
        shopButton = (Button)findViewById(R.id.shopButton);
        shopButton.setBackgroundColor(0xFFD19828);
        shopButton.setTextColor(0xFFF1F1F1);
        editText = (EditText)findViewById(R.id.editText);
        editText.setTextColor(0xFF46AB69);
        question1 = (TextView)findViewById(R.id.question1);
        question2 = (TextView)findViewById(R.id.question2);
        question3 = (TextView)findViewById(R.id.question3);
        question4 = (TextView)findViewById(R.id.question4);
        tokenView = (TextView)findViewById(R.id.tokenView);
        increaseView = (TextView)findViewById(R.id.increaseView);
        badgeCount = (TextView)findViewById(R.id.badgeCount);
        badgeShow = (ImageView)findViewById(R.id.badgeShow);
        tokenShow = (ImageView)findViewById(R.id.tokenShow);


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
                if(!editText.getText().toString().isEmpty()) {
                    checkAnswer(Double.parseDouble((editText.getText().toString())));
                }
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
                question1.setTextColor(0xFF3EC563);
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
                question2.setTextColor(0xFF3EC563);
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
                question3.setTextColor(0xFF3EC563);
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
                question4.setTextColor(0xFF3EC563);
                game.setChosen(3);
            }
        });


        String tokens = "X     " + game.getTokens();
        String badges = "X     " + game.getBadges();
        tokenView.setText(tokens);
        badgeCount.setText(badges);
    }


    //Changes xml to shop
    private void showShop() {
        setContentView(R.layout.shop);
        purchaseButton = (Button)findViewById(R.id.purchaseButton);
        purchaseButton.setBackgroundColor(0xFF3EC563);
        purchaseButton.setTextColor(0xFFF1F1F1);
        returnButton = (Button)findViewById(R.id.returnButton);
        returnButton.setBackgroundColor(0xFFD19828);
        returnButton.setTextColor(0xFFF1F1F1);
        infoLabel = (TextView)findViewById(R.id.infoLabel);
        shopTokens = (TextView)findViewById(R.id.shopTokens);

        purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(game.getTokens() >= 15) {
                    game.setBadges(game.getBadges() + 1);
                    game.setTokens(game.getTokens() - 15);
                    String text = "X     " + game.getTokens();
                    shopTokens.setText(text);
                    saveProgress();
                }
            }
        });

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGame();
            }
        });
        String text = "X     " + game.getTokens();
        shopTokens.setText(text);
    }


    //Checks to see if the answer is correct. If it is, it updates the labels to show their tokens.
    private void checkAnswer(double guess) {
        if(game.getChosenEquation() != null) {
            if(game.checkAnswer(guess)) {
                editText.setTextColor(0xFF46AB69);
                editText.setText("");
                game.setTokens(game.getTokens() + (game.getChosenEquation().getValue()));
                fadeText(game.getChosenEquation().getValue());
                String text = "X     " + game.getTokens();
                tokenView.setText(text);
                game.createNewEquationList(game.getProblemType());
                showProblems();
                saveProgress();
            }
            else {
                if(game.getChosenEquation().getValue() > 0) {
                    game.getChosenEquation().decreaseValue();
                }
                editText.setTextColor(Color.RED);
            }
        }
    }


    //Changes the text views to be each of the problems
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


    //Displays how many points the user has just earned, and then has it fade away.
    private void fadeText(int number) {
        increaseView.setText("+ " + number);
        final Animation fadein = new AlphaAnimation(0.0f, 1.0f);
        final Animation fadeout = new AlphaAnimation(1.0f, 0.0f);
        AnimationSet textFade = new AnimationSet(true);
        fadein.setDuration(100);
        fadeout.setDuration(1100);
        textFade.addAnimation(fadeout);
        textFade.setStartOffset(3500);
        textFade.addAnimation(fadein);
        increaseView.startAnimation(textFade);
    }


    //Reads the text file containing the amount of badges and tokens the player last had.
    private void readFile() {
        StringBuilder read = new StringBuilder();
        try {
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
            e.printStackTrace();
            System.out.println("\n\n\n\n\n\n\n\nwhoops it failed");
        }
    }


    //loads in tokens
    private void loadTokens(String data) {
//        game.setTokens(Integer.parseInt(data.substring(0,data.indexOf("b"))));
//        game.setBadges(Integer.parseInt(data.substring(data.indexOf("b") + 1)));
        game.setTokens(0);
        game.setBadges(0);
        tokenView.setText("X    " + game.getTokens());
        badgeCount.setText("X    "  + game.getBadges());
    }


    //save function
    private void saveProgress() {
        try {
            FileOutputStream outputStream = openFileOutput(file.getName(), Context.MODE_PRIVATE);
            String tokens = game.getTokens() + "";
            String badges = game.getBadges() + "";
            outputStream.write(tokens.getBytes());
            outputStream.write(("b").getBytes());
            outputStream.write(badges.getBytes());
            outputStream.close();
            System.out.println("\n\n\nSuccess!");
        }
        catch(Exception e) {
            System.out.println("\nsave failed");
        }
    }
}

//Fade text: https://stackoverflow.com/questions/8627211/how-to-make-text-fade-in-and-out-in-android