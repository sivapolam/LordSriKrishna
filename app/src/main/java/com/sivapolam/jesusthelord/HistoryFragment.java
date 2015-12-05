package com.sivapolam.jesusthelord;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by pnaganjane001 on 12/11/15.
 */
public class HistoryFragment extends ListFragment implements Constants{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                inflater.getContext(), android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.history_items_array));
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent detailsIntent = new Intent(getActivity(), HistoryDetailsActivity.class);
        detailsIntent.putExtra(HISTORY_SELECTED_POSITION, position);
        startActivity(detailsIntent);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((Home) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}
