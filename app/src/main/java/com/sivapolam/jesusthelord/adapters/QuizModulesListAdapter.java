package com.sivapolam.jesusthelord.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sivapolam.jesusthelord.R;
import com.sivapolam.jesusthelord.data.QuizJsonResponse;
import com.sivapolam.jesusthelord.data.QuizModel;

import java.util.ArrayList;

/**
 * Created by pnaganjane001 on 19/11/15.
 */
public class QuizModulesListAdapter extends BaseAdapter {
    private Context context;
    ArrayList<String> selectedAnswersList;
    LayoutInflater inflater;
    public QuizModulesListAdapter(Context c, ArrayList<String> selectedAnswersList) {
        context = c;
        this.selectedAnswersList = selectedAnswersList;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return this.selectedAnswersList.size();
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
        View v = inflater.inflate(R.layout.quiz_module_list_item,null);
        ViewHolder holder = new ViewHolder();
        holder.textView = (TextView)v.findViewById(R.id.module_list_item_text);
        holder.textView.setText(selectedAnswersList.get(position));

        if (QuizJsonResponse.getInstance().getQuizModelsAttemptedList()!=null) {
            if (QuizJsonResponse.getInstance().getQuizModelsAttemptedList().contains(position)) {
                holder.textView.setTextColor(context.getResources().getColor(R.color.black));
            }
        }

        return v;
    }

    class ViewHolder{
        TextView textView;
    }
}
