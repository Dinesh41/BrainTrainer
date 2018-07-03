package com.stalnobcrs.braintrainertem;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button start,a1,a2,a3,a0,playAgain;
    ArrayList<Integer> answers=new ArrayList<Integer>();
    int indexOfCorrectAns,scoreVal,totQues;
    TextView time,score,result,question;
    RelativeLayout rl;
    public void playAgain(View view){
        scoreVal=0;
        totQues=0;
        score.setText("0/0");
        timer();
        generateQues();
        playAgain.setVisibility(View.INVISIBLE);
    }
    public void timer() {
        CountDownTimer ct = new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long l) {
                time.setText(""+(l/1000)+"s");
            }

            @Override
            public void onFinish() {
                playAgain.setVisibility(View.VISIBLE);
                result.setText("Your score "+scoreVal+"/"+totQues);
            }
        };
        ct.start();
    }
    public void checkAns(View view){
        if(view.getTag().toString().equals(Integer.toString(indexOfCorrectAns))){
            result.setText("Correct");
            scoreVal++;
        }
        else{
            result.setText("Wrong");
        }
        totQues++;
        score.setText(scoreVal+"/"+totQues);
        generateQues();
    }
    public void updateAnsUI()
    {
        a0.setText(Integer.toString(answers.get(0)));
        a1.setText(Integer.toString(answers.get(1)));
        a2.setText(Integer.toString(answers.get(2)));
        a3.setText(Integer.toString(answers.get(3)));
    }
    public void generateQues()
    {
        Random r=new Random();
        int a=r.nextInt(21);
        int b=r.nextInt(21);
        question.setText(Integer.toString(a)+"+"+Integer.toString(b));
        indexOfCorrectAns=r.nextInt(4);
        answers.clear();
        int wrongAns;
        for(int i=0;i<4;i++) {
            if (i == indexOfCorrectAns) {
                answers.add(a + b);
            } else {
                wrongAns = r.nextInt(41);
                while (wrongAns == a + b) {
                    wrongAns = r.nextInt(41);
                }
                answers.add(wrongAns);
            }
        }
        updateAnsUI();
    }
    public void start(View view)
    {
        start.setVisibility(View.INVISIBLE);
        rl.setVisibility(View.VISIBLE);
        scoreVal=0;
        totQues=0;
        timer();
        generateQues();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start=(Button)findViewById(R.id.startButton);
        a0=(Button)findViewById(R.id.button0);
        a1=(Button)findViewById(R.id.button1);
        a2=(Button)findViewById(R.id.button2);
        a3=(Button)findViewById(R.id.button3);
        time=(TextView)findViewById(R.id.time);
        score=(TextView)findViewById(R.id.score);
        result=(TextView)findViewById(R.id.result);
        question=(TextView)findViewById(R.id.question);
        playAgain=(Button)findViewById(R.id.playAgain);
        rl=(RelativeLayout)findViewById(R.id.gameui);
        rl.setVisibility(View.INVISIBLE);

    }
}
