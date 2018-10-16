package com.example.murat.mathgame;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    EditText studentAnswer;
    Button addition, subtraction, multiplication, division;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        file = new File(getFilesDir(), "tokensandbadges.txt");
        game = new Game();
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
