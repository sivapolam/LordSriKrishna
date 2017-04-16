package com.makemobiapps.jesusthelord.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.makemobiapps.jesusthelord.R;
import com.makemobiapps.jesusthelord.ui.activities.HomeActivity;
import com.makemobiapps.jesusthelord.util.Constants;

import java.util.Locale;

/**
 * Created by pnaganjane001 on 12/11/15.
 */
public class AstotrasListFragment extends ListFragment implements Constants, AdapterView.OnItemClickListener {

    private String selectedQuote;
    private TextToSpeech textToSpeech;
    private TextView textView;
    private int selectedPosition;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        textToSpeech = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.UK);
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "TextToSpeech not supporting", Toast.LENGTH_LONG).show();
                }
            }
        });
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getListView().setSelector(R.drawable.list_selector_quotes);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(), R.layout.quotes_list_item, R.id.module_list_item_text,
                getResources().getStringArray(R.array.ashtottaras_list));
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
//        getListView().setBackgroundResource(R.drawable.krishna3);

    }

    public void onPause() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        getActivity().overridePendingTransition(R.anim.activity_open_scale, R.anim.activity_close_translate_animation);
        super.onPause();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((HomeActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        selectedQuote = getResources().getStringArray(R.array.ashtottaras_list)[position];
        getListView().setSelection(position);
//        getListView().getChildAt(position).setBackgroundResource(R.drawable.list_selector_quotes);
        if (textToSpeech != null && !textToSpeech.isSpeaking()) {
            textToSpeech.speak(selectedQuote, TextToSpeech.QUEUE_FLUSH, null);
        } else {
            if (selectedPosition == position) {
                textToSpeech.stop();
            } else {
                textToSpeech.speak(selectedQuote, TextToSpeech.QUEUE_FLUSH, null);
            }
        }

        selectedPosition = position;
    }
}
