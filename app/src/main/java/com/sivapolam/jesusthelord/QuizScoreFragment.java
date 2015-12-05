package com.sivapolam.jesusthelord;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by pnaganjane001 on 19/11/15.
 */
public class QuizScoreFragment extends ActionBarActivity implements Constants{

    private String moduleName;
    private ArrayList<String> selectedAnswersList;
    private int correctAnswers, totalQuestions, wrongAnswers;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_score_card);

        moduleName = getIntent().getStringExtra(QUIZ_MODULE_SELECTED);
        selectedAnswersList = (ArrayList<String>) getIntent().getSerializableExtra(QUIZ_ANSWERS_LIST_SELECTED);
        correctAnswers = getIntent().getIntExtra(QUIZ_CORRECT_ANSWERS_COUNT,0);
        totalQuestions = getIntent().getIntExtra(QUIZ_QUESTIONS_SIZE,0);
        wrongAnswers = totalQuestions - correctAnswers;

//        getSupportActionBar().setTitle("Your Score");

        TextView totalQuestionsTextView = (TextView)findViewById(R.id.total_questions);
        TextView correctAnswersTextView = (TextView)findViewById(R.id.correct_answers);
        TextView wrongAnswersTextView = (TextView)findViewById(R.id.wrong_answers);
        Button showAnswers = (Button)findViewById(R.id.btn_show_answers);


        totalQuestionsTextView.setText("" + totalQuestions);
        correctAnswersTextView.setText("" + correctAnswers);
        wrongAnswersTextView.setText("" + wrongAnswers);

        showAnswers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent answersListIntent = new Intent(getApplicationContext(), QuizAnswersListActivity.class);
                answersListIntent.putExtra(QUIZ_MODULE_SELECTED, moduleName);
                answersListIntent.putExtra(QUIZ_ANSWERS_LIST_SELECTED, selectedAnswersList);
                startActivity(answersListIntent);
            }
        });


    }

}
