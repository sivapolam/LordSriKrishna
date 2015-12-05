package com.sivapolam.jesusthelord.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sivapolam.jesusthelord.R;
import com.sivapolam.jesusthelord.data.QuizJsonResponse;

/**
 * Created by pnaganjane001 on 19/11/15.
 */
public class NavigationListAdapter extends BaseAdapter {
    private Context context;
    int[] selectedAnswersList;
    LayoutInflater inflater;
    public NavigationListAdapter(Context c, int[] selectedAnswersList) {
        context = c;
        this.selectedAnswersList = selectedAnswersList;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return selectedAnswersList.length;
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
        View v = inflater.inflate(R.layout.navigation_list_item,null);
        ViewHolder holder = new ViewHolder();
        holder.moduleName = (TextView)v.findViewById(R.id.navigation_module_name);
        holder.moduleIcon = (ImageView)v.findViewById(R.id.navigation_icon);

        if (this.selectedAnswersList!=null)
            holder.moduleIcon.setImageResource(this.selectedAnswersList[position]);

        if (context.getResources().getStringArray(R.array.navigation_items_array)!=null)
            holder.moduleName.setText(context.getResources().getStringArray(R.array.navigation_items_array)[position]);

        return v;
    }

    class ViewHolder{
        TextView moduleName;
        ImageView moduleIcon;
    }
}
