package com.example.snehitsagi.plan;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.widget.DatePicker;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Snehit Sagi on 11-Jun-17.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(),
                this, year, month, day);
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar cal = new GregorianCalendar(year, month, day);
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String finalDate = dateFormat.format(cal.getTime());
        Intent in = new Intent(getActivity(),input.class);
        in.putExtra("finaldate",finalDate);

    }
}
