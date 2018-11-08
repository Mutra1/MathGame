package com.example.murat.mathgame;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.EditText;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class MainActivity extends AppCompatActivity {

    Game game;
    TextView question1, question2, question3, question4, tokenCount, shopTokens, infoLabel, badgeCount, increaseView;
    EditText editText;
    Button addition, subtraction, multiplication, division, shuffle, enterAnswer, shopButton, purchaseButton, returnButton;
    ImageView badgeShow, tokenShow;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        file = new File(getFilesDir(), "token.txt");
        game = new Game();
        readFile();
        showGame();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
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
        editText.setTextColor(0xFF46AB69);
        question1 = (TextView)findViewById(R.id.question1);
        question2 = (TextView)findViewById(R.id.question2);
        question3 = (TextView)findViewById(R.id.question3);
        question4 = (TextView)findViewById(R.id.question4);
        tokenCount = (TextView)findViewById(R.id.tokenCount);
        increaseView = (TextView)findViewById(R.id.increaseView);
        badgeCount = (TextView)findViewById(R.id.badgeCount);
        badgeShow = (ImageView)findViewById(R.id.badgeShow);
        tokenShow = (ImageView)findViewById(R.id.tokenShow);

        if(game.isFirstTime()) {
            editText.setVisibility(View.GONE);
            enterAnswer.setVisibility(View.GONE);
            shuffle.setVisibility(View.GONE);
            shopButton.setVisibility(View.GONE);
            tokenCount.setVisibility(View.INVISIBLE);
            tokenShow.setVisibility(View.GONE);
            badgeCount.setVisibility(View.INVISIBLE);
            badgeShow.setVisibility(View.GONE);
        }


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
                question1.setTextColor(0xFF3ECD65);
                question2.setTextColor(0xFFDC9552);
                question3.setTextColor(0xFFDC9552);
                question4.setTextColor(0xFFDC9552);
                game.setChosen(0);
                editText.setVisibility(View.VISIBLE);
                enterAnswer.setVisibility(View.VISIBLE);
            }
        });

        question2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question1.setTextColor(0xFFDC9552);
                question2.setTextColor(0xFF3ECD65);
                question3.setTextColor(0xFFDC9552);
                question4.setTextColor(0xFFDC9552);
                game.setChosen(1);
                editText.setVisibility(View.VISIBLE);
                enterAnswer.setVisibility(View.VISIBLE);
            }
        });

        question3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question1.setTextColor(0xFFDC9552);
                question2.setTextColor(0xFFDC9552);
                question3.setTextColor(0xFF3ECD65);
                question4.setTextColor(0xFFDC9552);
                game.setChosen(2);
                editText.setVisibility(View.VISIBLE);
                enterAnswer.setVisibility(View.VISIBLE);
            }
        });

        question4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question1.setTextColor(0xFFDC9552);
                question2.setTextColor(0xFFDC9552);
                question3.setTextColor(0xFFDC9552);
                question4.setTextColor(0xFF3ECD65);
                game.setChosen(3);
                editText.setVisibility(View.VISIBLE);
                enterAnswer.setVisibility(View.VISIBLE);
            }
        });


        String tokens = "x     " + game.getTokens();
        String badges = "x     " + game.getBadges();
        tokenCount.setText(tokens);
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
                if(game.getTokens() >= 15) {
                    game.setBadges(game.getBadges() + 1);
                    game.setTokens(game.getTokens() - 15);
                    String text = "x    " + game.getTokens();
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
        String text = "x     " + game.getTokens();
        shopTokens.setText(text);
    }


    //Checks to see if the answer is correct. If it is, it updates the labels to show their tokens.
    private void checkAnswer(double guess) {
        if(game.getChosenEquation() != null) {
            if(game.checkAnswer(guess)) {
                shopButton.setVisibility(View.VISIBLE);
                tokenShow.setVisibility(View.VISIBLE);
                tokenCount.setVisibility(View.VISIBLE);
                badgeShow.setVisibility(View.VISIBLE);
                badgeCount.setVisibility(View.VISIBLE);
                editText.setTextColor(0xFF46AB69);
                editText.setText("");
                game.setTokens(game.getTokens() + (game.getChosenEquation().getValue()));
                fadeText(game.getChosenEquation().getValue());
                String text = "x     " + game.getTokens();
                String btext = "x     " + game.getBadges();
                tokenCount.setText(text);
                badgeCount.setText(btext);
                game.createNewEquationList(game.getProblemType());
                showProblems();
                saveProgress();

                game.setFirstTime(false);
//                game.playSound(true);
            }
            else {
                if(game.getChosenEquation().getValue() > 0) {
                    game.getChosenEquation().decreaseValue();
                }
                editText.setTextColor(Color.RED);
//                game.playSound(false);
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
        shuffle.setVisibility(View.VISIBLE);
    }


    //Displays how many points the user has just earned, and then has it fade away.
    private void fadeText(int number) {
        increaseView.setText("+ " + number);
        final Animation fadeout = new AlphaAnimation(1.0f, 0.0f);
        fadeout.setDuration(900);
        fadeout.setStartOffset(1100);
        increaseView.startAnimation(fadeout);
        setTimeout(2000, new Runnable() {
            @Override
            public void run() {
                increaseView.setText("");
            }
        });
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
            loadTokens(data);

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }


    //loads in tokens
    private void loadTokens(String data) {
        game.setTokens(Integer.parseInt(data.substring(0,data.indexOf("b"))));
        game.setBadges(Integer.parseInt(data.substring(data.indexOf("b") + 1)));
        if(game.getBadges() == 0 && game.getTokens() == 0) {
            game.setFirstTime(true);
        }
    }


    //save function
    private void saveProgress() {
        try {
            FileOutputStream outputStream = openFileOutput(file.getName(), Context.MODE_PRIVATE);
            String tokens = game.getTokens() + "";
            String badges = game.getBadges() + "";
//            outputStream.write(tokens.getBytes());
            outputStream.write("0".getBytes());
            outputStream.write(("b").getBytes());
            outputStream.write("0".getBytes());
//            outputStream.write(badges.getBytes());
            outputStream.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }


    //timer function
    public void setTimeout(final int delay, Runnable function) {
        new android.os.Handler().postDelayed(function, delay);
    }
}

//Fade text: https://stackoverflow.com/questions/8627211/how-to-make-text-fade-in-and-out-in-android
//Round button: https://stackoverflow.com/questions/6054562/how-to-make-the-corners-of-a-button-round