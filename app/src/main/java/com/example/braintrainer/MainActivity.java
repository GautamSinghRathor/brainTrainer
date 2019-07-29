package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> arrayList = new ArrayList<String>();

    Button btn1, btn2, btn3, btn4, playAgain;
    TextView tv_result, tv_score, tv_question, tv_time;

    int locationOfCurrentAns;
    int totalTurn=0, winCount=0;
    int currentAns;

    boolean gameIsActive = true;

    public void playAgain(View view){

        tv_time.setText("30s");
        tv_result.setText("result");
        tv_score.setText("0/0");
        totalTurn = 0;
        winCount = 0;
        gameIsActive = true;
        playAgain.setVisibility(view.INVISIBLE);
        generateQuestion();
        timer();
    }

    public void timer(){

        new CountDownTimer(30000+100, 1000){


            @Override
            public void onTick(long l) {
                tv_time.setText(l/1000 + "s");
            }

            @Override
            public void onFinish() {

                if(winCount == totalTurn){
                    tv_result.setText("Hurray! Full Score");
                }else {
                    tv_result.setText("score is : "+winCount+"/"+totalTurn);
                }
                gameIsActive = false;
                playAgain.setVisibility(View.VISIBLE);
            }
        }.start();

    }


    public void generateQuestion(){

        Random rand = new Random();

        int a;
        int b;

        a = rand.nextInt(21);
        b = rand.nextInt(21);

        currentAns = a + b;

        tv_question.setText(a +"+"+ b);

        locationOfCurrentAns = rand.nextInt(4);

        arrayList.clear();

        for(int i = 0; i<4 ;i++){

            a = rand.nextInt(21);
            b = rand.nextInt(21);

            if(locationOfCurrentAns == i){

                arrayList.add(Integer.toString(currentAns));

            }else {
                int wrongAns = rand.nextInt(41);

                while(currentAns == wrongAns){
                    wrongAns = rand.nextInt(41);
                }

                arrayList.add(Integer.toString(wrongAns));
            }

        }


        btn1.setText(arrayList.get(0));
        btn2.setText(arrayList.get(1));
        btn3.setText(arrayList.get(2));
        btn4.setText(arrayList.get(3));

    }



    public void checkAnswer(View view){

        if(gameIsActive) {
            String clicked = (String) view.getTag();

            int i = Integer.parseInt(clicked);

            //check for correctness
            if (locationOfCurrentAns == i) {

                tv_result.setText("Correct!");
                winCount++;

            } else
                tv_result.setText("Wrong!");

            totalTurn++;

            tv_score.setText(winCount + "/" + totalTurn);

            generateQuestion();
        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_question = (TextView) findViewById(R.id.tv_question);
        tv_result = (TextView) findViewById(R.id.tv_result);
        tv_score = (TextView) findViewById(R.id.tv_score);
        tv_time = (TextView) findViewById(R.id.tv_time);
        playAgain = (Button) findViewById(R.id.btn_playAgain);

        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn4 = (Button) findViewById(R.id.button4);

        generateQuestion();

        timer();

    }
}
