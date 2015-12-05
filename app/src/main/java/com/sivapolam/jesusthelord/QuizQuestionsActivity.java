package com.sivapolam.jesusthelord;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sivapolam.jesusthelord.data.QuizJsonResponse;
import com.sivapolam.jesusthelord.data.QuizModel;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Created by pnaganjane001 on 17/11/15.
 */
public class QuizQuestionsActivity extends ActionBarActivity implements Constants{

    private static final String TAG = QuizQuestionsActivity.class.getName();
    ArrayList<QuizModel> quizModelArrayList;
    int questionNumber, totalScore, progressCount, maxTime;
    ProgressBar progressTimer;
    TextView timerText, questionName, questionNumberText;
    RadioButton option1, option2, option3;
    RadioGroup questionGroup;
    Button submitBtn, skipBtn;
    String selectedAnswer, correctAnswer;
    private CountDownTimer mCountDownTimer;
    ArrayList<String> selectedAnswersList;
    private String moduleName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_question_layout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        progressTimer = (ProgressBar)findViewById(R.id.progressbar_quiz);
        timerText = (TextView)findViewById(R.id.timer_text);
        questionName = (TextView)findViewById(R.id.question_text);
        questionNumberText = (TextView)findViewById(R.id.question_number_text);
        option1 = (RadioButton)findViewById(R.id.option1);
        option2 = (RadioButton)findViewById(R.id.option2);
        option3 = (RadioButton)findViewById(R.id.option3);
        submitBtn = (Button)findViewById(R.id.submit_quiz);
        skipBtn = (Button)findViewById(R.id.skip_quiz);
        questionGroup = (RadioGroup)findViewById(R.id.question_group);
        submitBtn.setEnabled(false);
        int position = getIntent().getIntExtra(QUIZ_MODULE_SELECTED_POSITION, 0);
        moduleName = getIntent().getStringExtra(QUIZ_MODULE_SELECTED);

        selectedAnswersList = new ArrayList<String>();

        if (moduleName!=null){
            getSupportActionBar().setTitle(moduleName.trim());
            QuizJsonResponse.getInstance().setQuizQuestions(moduleName);
            quizModelArrayList = QuizJsonResponse.getInstance().getQuizModelArrayList();
            displayCurrentQuestion(questionNumber, "default");
        }

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                questionNumber = ++questionNumber;
                if (quizModelArrayList.size()>=questionNumber){
                    if (questionGroup.getCheckedRadioButtonId()!=-1){
                        displayCurrentQuestion(questionNumber, "Submit");
                    }else{
                        Toast.makeText(QuizQuestionsActivity.this, "Select option to Submit", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionNumber = ++questionNumber;
                if (quizModelArrayList.size()>=questionNumber){
                    displayCurrentQuestion(questionNumber, "Skip");
                }
            }
        });

        questionGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int selectedId=group.getCheckedRadioButtonId();
                RadioButton radioSexButton=(RadioButton)findViewById(selectedId);
                if (radioSexButton!=null && radioSexButton.getText()!=null){
                    selectedAnswer = radioSexButton.getText().toString().trim();
                    submitBtn.setEnabled(true);
                }

            }
        });
    }

    private void displayCurrentQuestion(int currentQuestionNumber, String type) {

        questionGroup.clearCheck();

        if (type.equalsIgnoreCase("Submit") && selectedAnswer != null && correctAnswer != null && correctAnswer.equalsIgnoreCase(selectedAnswer)) {
            ++totalScore;
        }

        if (currentQuestionNumber>=1){
            if (type.equalsIgnoreCase("Submit")){
                selectedAnswersList.add(selectedAnswer);
            }else{
                selectedAnswersList.add("-");
            }
        }

        if (quizModelArrayList !=null && quizModelArrayList.size()>currentQuestionNumber){

            QuizModel quizModel = (QuizModel)quizModelArrayList.get(currentQuestionNumber);
            questionName.setText(quizModel.getQuestion());
            option1.setText(quizModel.getOption1());
            option2.setText(quizModel.getOption2());
            option3.setText(quizModel.getOption3());
            questionNumberText.setText("" + (++currentQuestionNumber) + "/" + quizModelArrayList.size());

            correctAnswer = quizModel.getAnswer().trim();
            submitBtn.setEnabled(false);

            calculateProgressTimer();
        }else {
            if (mCountDownTimer!=null){
                mCountDownTimer.cancel();
                mCountDownTimer = null;
            }

            finish();

            Intent scoreCardIntent = new Intent(getApplicationContext(), QuizScoreFragment.class);
            scoreCardIntent.putExtra(QUIZ_MODULE_SELECTED, moduleName);
            scoreCardIntent.putExtra(QUIZ_ANSWERS_LIST_SELECTED, selectedAnswersList);
            scoreCardIntent.putExtra(QUIZ_QUESTIONS_SIZE, quizModelArrayList.size());
            scoreCardIntent.putExtra(QUIZ_CORRECT_ANSWERS_COUNT, totalScore);
            startActivity(scoreCardIntent);

//            QuizScoreFragment scoreFragment = new QuizScoreFragment();
//            Bundle args = new Bundle();
//            args.putString(QUIZ_MODULE_SELECTED,moduleName);
//            args.putInt(QUIZ_QUESTIONS_SIZE,quizModelArrayList.size());
//            args.putInt(QUIZ_CORRECT_ANSWERS_COUNT,totalScore);
//            args.putSerializable(QUIZ_ANSWERS_LIST_SELECTED, selectedAnswersList);
//            scoreFragment.setArguments(args);
//            scoreFragment.show(getSupportFragmentManager(), "ScoreDialogFragment");

        }

    }

    private void calculateProgressTimer() {
        progressCount = 0;
        progressTimer.setProgress(progressCount);
        maxTime = 30;

        if (mCountDownTimer!=null){
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }

        mCountDownTimer=new CountDownTimer(30000,1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                progressCount++;
                progressTimer.setProgress(progressCount);


                NumberFormat formatter = new DecimalFormat("00");
                if ((millisUntilFinished/1000)>=0){
                    String timer = formatter.format(millisUntilFinished/1000);
                    timerText.setText("00:"+timer);
                }

            }

            @Override
            public void onFinish() {
                //Do what you want
                progressCount++;
                progressTimer.setProgress(progressCount);
                timerText.setText("00:00");
                questionNumber = ++questionNumber;
                if (quizModelArrayList.size()>questionNumber){
                    displayCurrentQuestion(questionNumber, "TimeUp");
                }
            }
        };
        mCountDownTimer.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
