package com.example.braintrainergame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button buttonGo, button0, button1, button2, button3, playAgainButton;
    TextView timerTextView, scoreTextView, textView1, sumTextView, resultTextView;
    ConstraintLayout gameLayout;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locOfCorrectAns;
    int score=0, numberOfQuestions=0;

    public void start(View view)
    {
        buttonGo.setVisibility(View.INVISIBLE);
        playAgain(findViewById(R.id.timerTextView));
        gameLayout.setVisibility(View.VISIBLE);
        button0.setClickable(true);
        button1.setClickable(true);
        button2.setClickable(true);
        button3.setClickable(true);
    }

    public void playAgain(View view){
        score = 0;
        numberOfQuestions = 0;
        button0.setClickable(true);
        button1.setClickable(true);
        button2.setClickable(true);
        button3.setClickable(true);
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        newQustion();
        resultTextView.setText("...");

        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("TIME'S UP :0");
                playAgainButton.setVisibility(View.VISIBLE);
                button0.setClickable(false);
                button1.setClickable(false);
                button2.setClickable(false);
                button3.setClickable(false);
            }
        }.start();
        playAgainButton.setVisibility(View.INVISIBLE);

    }

    public void newQustion(){
        Random random = new Random();

        int a = random.nextInt(31);
        int b = random.nextInt(31);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locOfCorrectAns = random.nextInt(4);

        answers.clear();

        for (int i=0; i<4; i++){
            if (i==locOfCorrectAns){
                answers.add(a+b);
            }
            else {
                int wrongAns = random.nextInt(41);
                while (wrongAns==(a+b)){
                    wrongAns = random.nextInt(41);
                }
                answers.add(wrongAns);
            }

        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    public void chooseAnswer(View view){

        if (Integer.toString(locOfCorrectAns).equals(view.getTag().toString())){
            Log.i("CORRECT","You Got It");
            resultTextView.setText("CORRECT :D");
            score++;
        }

        else{
            Log.i("WRONG", ":/");
            resultTextView.setText("WRONG :(" );
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        newQustion();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonGo=findViewById(R.id.goButton);
        button0=findViewById(R.id.button0);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        playAgainButton=findViewById(R.id.playAgainButton);
        timerTextView=findViewById(R.id.timerTextView);
        scoreTextView=findViewById(R.id.scoreTextView);
        textView1=findViewById(R.id.textView1);
        sumTextView=findViewById(R.id.sumTextView);
        resultTextView=findViewById(R.id.resultTextView);
        gameLayout=findViewById(R.id.gameLayout);

        buttonGo.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);

    }
}
