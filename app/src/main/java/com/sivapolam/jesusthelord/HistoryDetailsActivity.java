package com.sivapolam.jesusthelord;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by pnaganjane001 on 12/11/15.
 */
public class HistoryDetailsActivity extends ActionBarActivity implements Constants{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int position = getIntent().getIntExtra(HISTORY_SELECTED_POSITION,0);

        TextView descriptionText = (TextView)findViewById(R.id.description_detail);
        if (getResources().getStringArray(R.array.history_descriptions_array).length > 0
                && getResources().getStringArray(R.array.history_descriptions_array).length > position){
            descriptionText.setText(getResources().getStringArray(R.array.history_descriptions_array)[position]);
            getSupportActionBar().setTitle(getResources().getStringArray(R.array.history_items_array)[position]);
        } else {
            descriptionText.setText("No Data Found");
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
