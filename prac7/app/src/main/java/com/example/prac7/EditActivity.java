package com.example.prac7;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prac7.datamodels.Task;
import com.example.prac7.helpers.DateHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EditActivity extends AppCompatActivity {

    public static int OK_RESULT_CODE = 1;

    Button buttonSave;
    TextView textViewDateDue;
    EditText editTextDescription;
    RadioButton radioButtonGreen;
    RadioButton radioButtonRed;
    RadioButton radioButtonBlue;

    Task task = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        if (id == 0)
        {
            task = new Task(0, "", "G", "ACTIVE", DateHelper.convertTodayToDate());
        }
        else
        {
            task = MainActivity.taskDataManager.getTaskById(id);
        }

        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

        textViewDateDue = findViewById(R.id.textViewDateDue);
        textViewDateDue.setText(dateFormat.format(task.getDateDue()));
        textViewDateDue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // We want to show a date picker dialog here
                //
                Date dateDue = task.getDateDue();
                DateHelper currentDateDue = DateHelper.convertDateToYearMonthDay(dateDue);

                DatePickerDialog datePickerDialog = new DatePickerDialog(EditActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                        // year, month, day parameters store the date selected by the user!
                        //
                        Date newDateDue = DateHelper.convertYearMonthDayToDate(year, month, day);
                        task.setDateDue(newDateDue);

                        textViewDateDue.setText(dateFormat.format(task.getDateDue()));
                    }
                }, currentDateDue.year, currentDateDue.month, currentDateDue.day);

                // Remember to show the date picker dialog after creating it.
                //
                datePickerDialog.show();

            }
        });

        radioButtonGreen = findViewById(R.id.radioButtonGreen);
        radioButtonBlue = findViewById(R.id.radioButtonBlue);
        radioButtonRed = findViewById(R.id.radioButtonRed);

        if (task.getColour().equals("G"))
            radioButtonGreen.setChecked(true);
        if (task.getColour().equals("B"))
            radioButtonBlue.setChecked(true);
        if (task.getColour().equals("R"))
            radioButtonRed.setChecked(true);

        editTextDescription = findViewById(R.id.editTextDescription);
        editTextDescription.setText(task.getDescription());

        buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // When the user clicks on the save button, let's perform
                // some validation first.
                //
                if (editTextDescription.getText().toString().equals(""))
                {
                    editTextDescription.setError("Please enter a description");
                    return;
                }

                // Then we retrieve all values from the UI widgets
                //
                task.setDescription(editTextDescription.getText().toString());

                if (radioButtonGreen.isChecked())
                    task.setColour("G");
                if (radioButtonBlue.isChecked())
                    task.setColour("B");
                if (radioButtonRed.isChecked())
                    task.setColour("R");

                // This segment here saves the task to the database
                //
                if (task.getId() == 0)
                {
                    MainActivity.taskDataManager.addTask(task);
                    Toast.makeText(EditActivity.this, "New task added", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    MainActivity.taskDataManager.updateTask(task);
                    Toast.makeText(EditActivity.this, "Task updated", Toast.LENGTH_SHORT).show();
                }

                // Close off the activity.
                setResult(OK_RESULT_CODE);
                finish();
            }
        });
    }
}