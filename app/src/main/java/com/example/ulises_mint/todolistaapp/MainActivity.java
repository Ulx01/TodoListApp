package com.example.ulises_mint.todolistaapp;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button addButton;
    Button deleteButton;
    TodoDB db;
    ListView todoList;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new TodoDB(this);
        addButton = (Button)findViewById(R.id.addButton);
        todoList = (ListView)findViewById(R.id.todoList);
        todoList.setVisibility(View.VISIBLE);
        addButton.setOnClickListener(this);
        todoList.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String info[], element;
                element = (String)todoList.getItemAtPosition(i);
                info = db.getTaskInformartion(element);
                intent = new Intent(MainActivity.this, TaskInformation.class);
                intent.putExtra("INFO", info);
                startActivity(intent);
                //Toast.makeText(getApplicationContext(),info[0],Toast.LENGTH_SHORT).show();
            }
        });
        setTitles();
        db.closeDB();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.addButton :
                finish();
                intent = new Intent(this, InsertTask.class);
                startActivity(intent);
               // db.closeDB();//si no se cierra
                break;
        }
    }



    public void setTitles()
    {
        try {
            String titles[] = db.getAllTitles();
            ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, titles);
            //Toast.makeText(this, titles[0], Toast.LENGTH_SHORT).show();
            todoList.setAdapter(listAdapter);
        }catch (SQLException ex)
        {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
