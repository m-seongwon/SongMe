package com.example.songme;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button btnSelectDates;
    private TextView tvSelectedDates;
    private List<String> selectedDates = new ArrayList<>();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSelectDates = findViewById(R.id.btnSelectDates);
        tvSelectedDates = findViewById(R.id.tvSelectedDates);

        btnSelectDates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar selectedDate = Calendar.getInstance();
                        selectedDate.set(year, month, dayOfMonth);
                        String formattedDate = dateFormat.format(selectedDate.getTime());
                        selectedDates.add(formattedDate);
                        updateSelectedDatesTextView();
                    }
                },
                year,
                month,
                dayOfMonth
        );

        // Allow multiple date selection
        datePickerDialog.getDatePicker().setOnDateChangedListener(null);

        datePickerDialog.setButton(DatePickerDialog.BUTTON_POSITIVE, "OK", datePickerDialog);
        datePickerDialog.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Cancel", datePickerDialog);
        datePickerDialog.show();
    }

    private void updateSelectedDatesTextView() {
        StringBuilder message = new StringBuilder("Selected Dates:\n");
        for (String date : selectedDates) {
            message.append(date).append("\n");
        }
        tvSelectedDates.setText(message.toString());
    }
}