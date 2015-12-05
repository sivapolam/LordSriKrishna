package com.sivapolam.jesusthelord;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by pnaganjane001 on 14/11/15.
 */
public class ExitFragment extends DialogFragment implements Constants{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        int position = getArguments().getInt(NAVIGATION_POSITION, 0);
        builder.setTitle(getResources().getStringArray(R.array.navigation_items_array)[position]);
        builder.setMessage(getResources().getString(R.string.message_exit));

        builder.setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getActivity().finish();
                dismiss();
            }
        });

        builder.setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });

        return builder.create();
    }
}
