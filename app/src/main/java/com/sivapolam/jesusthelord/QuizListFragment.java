package com.sivapolam.jesusthelord;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.sivapolam.jesusthelord.adapters.QuizModulesListAdapter;
import com.sivapolam.jesusthelord.data.QuizJsonResponse;

import java.util.ArrayList;

/**
 * Created by pnaganjane001 on 12/11/15.
 */
public class QuizListFragment extends ListFragment implements Constants{

    private TextView textListItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        QuizJsonResponse.getInstance().readQuizJson(getActivity());
        if (QuizJsonResponse.getInstance().getQuizModulesList()!=null && QuizJsonResponse.getInstance().getQuizModulesList().size()>0){
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                    inflater.getContext(), R.layout.quiz_module_list_item,R.id.module_list_item_text,
//                    QuizJsonResponse.getInstance().getQuizModulesList());
//            setListAdapter(adapter);
            setListAdapter(new QuizModulesListAdapter(getActivity().getApplicationContext(), QuizJsonResponse.getInstance().getQuizModulesList()));

        }

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        textListItem = (TextView)v.findViewById(R.id.module_list_item_text);
        Intent detailsIntent = new Intent(getActivity(), QuizQuestionsActivity.class);
        detailsIntent.putExtra(QUIZ_MODULE_SELECTED_POSITION, position);
        detailsIntent.putExtra(QUIZ_MODULE_SELECTED, textListItem.getText().toString());
        startActivity(detailsIntent);

//        if (QuizJsonResponse.getInstance().getQuizModelsAttemptedList()!=null){
//            if (!QuizJsonResponse.getInstance().getQuizModelsAttemptedList().contains(position)){
//                ArrayList<Integer> arrayList = new ArrayList<Integer>();
//                arrayList.add(position);
//                QuizJsonResponse.getInstance().setQuizModelsAttemptedList(arrayList);
//            }
//
//        }else {
            ArrayList<Integer> arrayList = new ArrayList<Integer>();
            arrayList.add(position);
            QuizJsonResponse.getInstance().setQuizModelsAttemptedList(position);
//        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((Home) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        QuizJsonResponse.getInstance().clear();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (QuizJsonResponse.getInstance().getQuizModulesList()!=null && QuizJsonResponse.getInstance().getQuizModulesList().size()>0){
            setListAdapter(new QuizModulesListAdapter(getActivity().getApplicationContext(), QuizJsonResponse.getInstance().getQuizModulesList()));
        }
    }

}
