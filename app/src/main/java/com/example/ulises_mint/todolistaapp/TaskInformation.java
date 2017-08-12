package com.example.ulises_mint.todolistaapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TaskInformation extends AppCompatActivity implements View.OnClickListener{
    String[] info;
    EditText title;
    EditText information;
    EditText date;
    Button buttonDelete;
    Button buttonUpdate;
    TodoDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_information);
        Intent intent = getIntent();
        info = intent.getStringArrayExtra("INFO");
        title = (EditText) findViewById(R.id.textView4);
        information = (EditText)findViewById(R.id.editText);
        date = (EditText) findViewById(R.id.textView6);
        buttonDelete = (Button)findViewById(R.id.buttonDelete);
        buttonUpdate = (Button)findViewById(R.id.buttonUpdate);
        buttonDelete.setOnClickListener(this);
        buttonUpdate.setOnClickListener(this);
        title.setText(info[0]);
        information.setText(info[1]);
        date.setText(info[2]);
        db = new TodoDB(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.buttonDelete :
                new AlertDialog.Builder(this).setTitle("Delete").setMessage("Do you want to delete this?").setPositiveButton("Yeap",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                db.deleteTask(title.getText().toString());
                                finish();
                                Intent intent = new Intent(TaskInformation.this, MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("Nop",null)
                        .show();
                break;
            case R.id.buttonUpdate :
                db.updateTask(title.getText().toString(), information.getText().toString(),date.getText().toString());
                break;
        }
    }
}
