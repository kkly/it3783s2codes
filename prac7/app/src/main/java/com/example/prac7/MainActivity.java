package com.example.prac7;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.prac7.dataadapters.TaskAdapter;
import com.example.prac7.datamanagers.TaskDataManager;
import com.example.prac7.datamodels.Task;
import com.example.prac7.helpers.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    public static TaskDataManager taskDataManager = new TaskDataManager();

    // UI widgets
    //
    FloatingActionButton buttonAdd;
    GridView gridViewTasks;

    static int TASK_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper.initialize(getApplicationContext());

        gridViewTasks = findViewById(R.id.gridViewTasks);
        gridViewTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {

                // When the taps on one of the items in the GridVIew, show a popup dialog
                // that allows user to select one of the following choices:
                //  Edit Task / Complete Task
                //
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("What do you want to do?")
                        .setItems(new String[]{"Edit Task", "Complete Task"}, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int itemSelected) {

                                final Task task = (Task) gridViewTasks.getItemAtPosition(position);

                                if (itemSelected == 0)
                                {
                                    // "Edit Task" was selected.
                                    //
                                    Intent intent = new Intent(MainActivity.this, EditActivity.class);
                                    intent.putExtra("id", task.getId());
                                    startActivityForResult(intent, TASK_REQUEST_CODE);
                                }
                                else
                                {
                                    // "Complete Task" was selected.
                                    // Let's show an alert to ask user for confirmation before removing
                                    // the task from view.
                                    //
                                    new AlertDialog.Builder(MainActivity.this)
                                            .setMessage("Complete and remove task?")
                                            .setNegativeButton("No", null)
                                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    task.setStatus("COMPLETE");
                                                    MainActivity.taskDataManager.updateTask(task);

                                                    refreshList();
                                                }
                                            })
                                            .show();
                                }
                            }
                        })
                        .show();

            }
        });

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Show our EditActivity.
                //
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                intent.putExtra("id", 0);
                startActivityForResult(intent, TASK_REQUEST_CODE);

            }
        });

        refreshList();
    }

    public void refreshList()
    {
        Task[] tasks = taskDataManager.getAllTasks();

        TaskAdapter adapter = new TaskAdapter(this, tasks);
        gridViewTasks.setAdapter(adapter);

    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TASK_REQUEST_CODE) {
            refreshList();
        }
    }
}