package com.example.ulises_mint.todolistaapp;

import android.content.Intent;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertTask extends AppCompatActivity implements View.OnClickListener{

    Button addButton;
    EditText title;
    EditText information;
    EditText date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_task);
        addButton = (Button)findViewById(R.id.addButton);
        addButton.setOnClickListener(this);
        title = (EditText)findViewById(R.id.textTitle);
        information = (EditText)findViewById(R.id.textInformation);
        date = (EditText)findViewById(R.id.textDate);
    }

    @Override
    public void onClick(View view) {
        //Toast.makeText(this.getApplicationContext(), "Presionado", Toast.LENGTH_SHORT).show();
        TodoDB db = new TodoDB(this);//si no inserta, aqui esta el posible error
        try{
            db.addTask(title.getText().toString(),information.getText().toString(),date.getText().toString());
            Toast.makeText(this.getApplicationContext(),"Task added to the list",Toast.LENGTH_SHORT).show();
            finish();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }catch (SQLException ex){
            Toast.makeText(this.getApplicationContext(),ex.getMessage(),Toast.LENGTH_SHORT).show();
        }
        db.closeDB();
    }
}
