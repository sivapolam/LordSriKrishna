package com.makemobiapps.jesusthelord.ui.fragments;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.makemobiapps.jesusthelord.R;
import com.makemobiapps.jesusthelord.adapters.NamesListAdapter;
import com.makemobiapps.jesusthelord.data.NamesModel;
import com.makemobiapps.jesusthelord.ui.activities.HomeActivity;
import com.makemobiapps.jesusthelord.util.Constants;

import java.io.InputStream;
import java.util.ArrayList;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

/**
 * Created by pnaganjane001 on 12/17/16.
 */

public class NamesListFragment extends android.support.v4.app.Fragment {
    private static final String TAG = NamesListFragment.class.getSimpleName();
    private ArrayList<NamesModel> namesModelArrayList;
    private RecyclerView recyclerView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((HomeActivity) activity).onSectionAttached(
                getArguments().getInt(Constants.ARG_SECTION_NUMBER));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.recycler_view, container, false);

        recyclerView = (RecyclerView) view. findViewById(R.id.recycler_view);

        namesModelArrayList = new ArrayList<>();
        readExcel();

        if (namesModelArrayList.size() >0 ) {
            setAdapterData();
        }

        return view;
    }

    private void setAdapterData() {
        NamesListAdapter namesListAdapter = new NamesListAdapter(getActivity(), namesModelArrayList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(namesListAdapter);
    }

    private void readExcel() {
        try {

            AssetManager assetManager=getActivity().getAssets();// If this line gives you ERROR then try AssetManager assetManager=getActivity().getAssets();
            InputStream inputStream=assetManager.open("lord_krishna_names_meanings.xls");
            Workbook workbook =Workbook.getWorkbook(inputStream);
            Sheet sheet=workbook.getSheet(0);
            int row =sheet.getRows();
            int columns=sheet.getColumns();
            String xx="";
            for(int i=0;i<row;i++)
            {
                NamesModel namesModel = new NamesModel();

                if (i > 0) {
                    for (int c=0;c<columns;c++)
                    {
                        Cell z=sheet.getCell(c,i);

                        if (c == 0) {
                            Log.e(TAG, "name--> "+z.getContents());
                            namesModel.setName(z.getContents());
                        } else if (c == 1) {
                            Log.e(TAG, "meaning--> "+z.getContents());
                            namesModel.setMeaning(z.getContents());
                        }

//                    setDataToModel(c, z.getContents(), namesModel);
                    }
                    namesModelArrayList.add(namesModel);

                }


            }
        }

        catch (Exception e)
        {

        }
    }

    private void setDataToModel(int c, String contents, NamesModel namesModel) {




    }


}
