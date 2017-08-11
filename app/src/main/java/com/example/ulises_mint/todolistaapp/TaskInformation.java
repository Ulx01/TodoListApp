package com.example.ulises_mint.todolistaapp;

import android.content.Intent;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TaskInformation extends AppCompatActivity implements View.OnClickListener{
    String[] info;
    TextView title;
    EditText information;
    TextView date;
    Button buttonDelete;
    Button buttonUpdate;
    TodoDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_information);
        Intent intent = getIntent();
        info = intent.getStringArrayExtra("INFO");
        title = (TextView)findViewById(R.id.textView4);
        information = (EditText)findViewById(R.id.editText);
        date = (TextView)findViewById(R.id.textView6);
        buttonDelete = (Button)findViewById(R.id.buttonDelete);
        buttonUpdate = (Button)findViewById(R.id.buttonUpdate);
        buttonDelete.setOnClickListener(this);
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
                db.deleteTask(title.getText().toString());
                finish();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
