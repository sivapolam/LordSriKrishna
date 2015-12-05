package com.sivapolam.jesusthelord;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.sivapolam.jesusthelord.adapters.QuizAnswersAdapter;
import com.sivapolam.jesusthelord.data.QuizJsonResponse;
import com.sivapolam.jesusthelord.data.QuizModel;

import java.util.ArrayList;

/**
 * Created by pnaganjane001 on 19/11/15.
 */
public class QuizAnswersListActivity extends ActionBarActivity implements Constants{

    private String moduleName;
    private ArrayList<QuizModel> quizModelArrayList;
    ArrayList<String> selectedAnswersList;
    android.app.ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_answers_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListView listView = (ListView)findViewById(R.id.list_answers);

        moduleName = getIntent().getStringExtra(QUIZ_MODULE_SELECTED);
        selectedAnswersList = (ArrayList<String>) getIntent().getSerializableExtra(QUIZ_ANSWERS_LIST_SELECTED);

        if (moduleName!=null){
            getSupportActionBar().setTitle(moduleName.trim());
            QuizJsonResponse.getInstance().setQuizQuestions(moduleName);
            quizModelArrayList = QuizJsonResponse.getInstance().getQuizModelArrayList();
            listView.setAdapter(new QuizAnswersAdapter(getApplicationContext(),quizModelArrayList,selectedAnswersList));
        }
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
