package com.sivapolam.jesusthelord.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.sivapolam.jesusthelord.QuizAnswersListActivity;
import com.sivapolam.jesusthelord.R;
import com.sivapolam.jesusthelord.data.QuizModel;

import java.util.ArrayList;

/**
 * Created by pnaganjane001 on 19/11/15.
 */
public class QuizAnswersAdapter extends BaseAdapter {
    private Context context;
    ArrayList<QuizModel> quizModelArrayList;
    ArrayList<String> selectedAnswersList;
    LayoutInflater inflater;
    public QuizAnswersAdapter(Context c, ArrayList<QuizModel> quizModelArrayList, ArrayList<String> selectedAnswersList) {
        context = c;
        this.quizModelArrayList = quizModelArrayList;
        this.selectedAnswersList = selectedAnswersList;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return this.quizModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = inflater.inflate(R.layout.quiz_answers_list_item,null);
        ViewHolder holder = new ViewHolder();
        holder.questionText = (TextView)v.findViewById(R.id.question_name);
        holder.answerFullText = (TextView)v.findViewById(R.id.answer_full);
        holder.selectedAnswer = (TextView)v.findViewById(R.id.selected_answer);
        holder.correctAnswer = (TextView)v.findViewById(R.id.correct_answer);

        QuizModel model = (QuizModel)this.quizModelArrayList.get(position);
        holder.questionText.setText((position+1)+". "+model.getQuestion());
        holder.answerFullText.setText(model.getFullAnswer());

        if (selectedAnswersList!=null && selectedAnswersList.size()>position){
            if (selectedAnswersList.get(position).trim().equalsIgnoreCase(model.getAnswer().trim())){
                holder.selectedAnswer.setVisibility(View.GONE);
            }else {
                holder.selectedAnswer.setVisibility(View.VISIBLE);
                holder.selectedAnswer.setText("Your Answer: "+this.selectedAnswersList.get(position));
            }

        }

        holder.correctAnswer.setText("Correct Answer: "+model.getAnswer());

        return v;
    }

    class ViewHolder{
        TextView questionText;
        TextView answerFullText;
        TextView selectedAnswer;
        TextView correctAnswer;
    }
}
