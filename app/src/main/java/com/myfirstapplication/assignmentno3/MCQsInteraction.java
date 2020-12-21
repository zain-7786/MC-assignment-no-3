package com.myfirstapplication.assignmentno3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class MCQsInteraction extends AppCompatActivity {

    boolean[] CorrectedMCQs;
    String[] CorrectOptions;
    ArrayList<MCQ> MCQsArray;

    RadioGroup radioGroup;
    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;
    RadioButton radioButton4;
    TextView Question;
    TextView mytimer;

    int MCQ_NO;
    int myindex;

    private static final long START_TIME_IN_MILLIS = 15000;
    private CountDownTimer mCountDownTimer;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }
            @Override
            public void onFinish() {
                GotoNextMCQ(null);
            }
        }.start();
    }
    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        mytimer.setText(timeLeftFormatted);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mcqs_interaction);
        mytimer = findViewById(R.id.mytimer);
        MCQsArray = GenerateAllMCQs();
        CorrectedMCQs = new boolean[MCQsArray.size()];
        CorrectOptions = new String[MCQsArray.size()];
        myindex = 0;
        for(int i=0;i<MCQsArray.size();i++)
        {
            CorrectedMCQs[i] = false;
        }
        MCQ_NO = 1;

        radioGroup = findViewById(R.id.radioGroup);
        radioButton1 = findViewById(R.id.radioButton);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        radioButton4 = findViewById(R.id.radioButton4);
        Question = findViewById(R.id.question);
        Question.setText("1) "+ MCQsArray.get(0).Description);
        radioButton1.setText(MCQsArray.get(0).options.get(0));
        radioButton2.setText(MCQsArray.get(0).options.get(1));
        radioButton3.setText(MCQsArray.get(0).options.get(2));
        radioButton4.setText(MCQsArray.get(0).options.get(3));
        startTimer();
    }

    public ArrayList<MCQ> GenerateAllMCQs()
    {
        String[]  MCQArray = getResources().getStringArray(R.array.MCQsData);
        ArrayList<MCQ> ListOfMCQs = new ArrayList<MCQ>();
        for(int i=0;i<MCQArray.length;i=i+5)
        {
            MCQ mcq = new MCQ();
            mcq.Description = MCQArray[i];
            mcq.options.add(MCQArray[i+1]);
            mcq.options.add(MCQArray[i+2]);
            mcq.options.add(MCQArray[i+3]);
            mcq.options.add(MCQArray[i+4]);
            mcq.CorrectOption = MCQArray[i+4];
            Collections.shuffle(mcq.options);
            ListOfMCQs.add(mcq);
        }
        Collections.shuffle(ListOfMCQs);
        return ListOfMCQs;
    }

    public void GotoNextMCQ(View view) {
        mCountDownTimer.cancel();
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        String selcted_option = "";
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.radioButton:
                selcted_option = radioButton1.getText().toString();
                break;
            case R.id.radioButton2:
                selcted_option = radioButton2.getText().toString();
                break;
            case R.id.radioButton3:
                selcted_option = radioButton3.getText().toString();
                break;
            case R.id.radioButton4:
                selcted_option = radioButton4.getText().toString();
                break;
        }
        radioGroup.clearCheck();
        if(MCQsArray.get(MCQ_NO-1).CorrectOption.equals(selcted_option))
        {
            CorrectedMCQs[MCQ_NO-1]=true;
        }
        else
        {
            CorrectOptions[myindex] = selcted_option+":"+MCQsArray.get(MCQ_NO-1).CorrectOption;
            myindex++;
        }
        if(MCQ_NO==10)
        {
            Intent intent = new Intent(this,ResultOfQuiz.class);
            intent.putExtra("result",CorrectedMCQs);
            intent.putExtra("correctOptions",CorrectOptions);
            startActivity(intent);
        }
        else
        {
            MCQ_NO++;
            Question.setText(MCQ_NO+") "+MCQsArray.get(MCQ_NO-1).Description);
            radioButton1.setText(MCQsArray.get(MCQ_NO-1).options.get(0));
            radioButton2.setText(MCQsArray.get(MCQ_NO-1).options.get(1));
            radioButton3.setText(MCQsArray.get(MCQ_NO-1).options.get(2));
            radioButton4.setText(MCQsArray.get(MCQ_NO-1).options.get(3));
            startTimer();
        }
    }
}