package com.example.ulises_mint.todolistaapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ulises_mint on 8/10/17.
 */

public class TodoDB extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "todo.db";

    public TodoDB(android.content.Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE "+ TaskSchema.TaskColumns.TABLE_NAME + "(" +
                TaskSchema.TaskColumns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TaskSchema.TaskColumns.TITLE + " TEXT NOT NULL, " +
                TaskSchema.TaskColumns.INFORMATION + " TEXT NOT NULL, " +
                TaskSchema.TaskColumns.DATE + " TEXT NOT NULL " +
                ");";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addTask(String title, String informartion, String date)
    {
        ContentValues values = new ContentValues();
        values.put(TaskSchema.TaskColumns.TITLE, title);
        values.put(TaskSchema.TaskColumns.INFORMATION, informartion);
        values.put(TaskSchema.TaskColumns.DATE,date);
        this.getWritableDatabase().insert(TaskSchema.TaskColumns.TABLE_NAME,null,values);
    }

    public Cursor getTasks()
    {
        String columns[] = {TaskSchema.TaskColumns.INFORMATION};
        Cursor result = this.getReadableDatabase().query(TaskSchema.TaskColumns.TABLE_NAME,null,null,null,null,null,null);
        return result;
    }

    public void deleteAll()
    {
        this.getWritableDatabase().delete(TaskSchema.TaskColumns.TABLE_NAME,null,null);
    }

    public String[] getAllTitles()
    {
        String[] titles =  null;
        try {
            int i = 0;
            Cursor result = this.getReadableDatabase().query(TaskSchema.TaskColumns.TABLE_NAME, new String[]{TaskSchema.TaskColumns.TITLE}, null, null, null, null, null);
            titles = new String[result.getCount()];
            while (result.moveToNext()) {
                titles[i] = result.getString(result.getColumnIndex(TaskSchema.TaskColumns.TITLE));
                i = i + 1;
            }
        }catch (Exception ex){
            titles = new String[]{ex.getMessage()};
        }
        return titles;
    }

    public String[] getTaskInformartion(String title)
    {
        Cursor result = this.getReadableDatabase().query(TaskSchema.TaskColumns.TABLE_NAME, null,
                TaskSchema.TaskColumns.TITLE + " LIKE ?",new String[]{title},null,null,null);
        String[] info = new String[3];//colocar cantidad especifica de datos a extraer del result
        while(result.moveToNext())//Siempre hacer esto
        {
            info[0] = result.getString(result.getColumnIndex(TaskSchema.TaskColumns.TITLE));
            info[1] = result.getString(result.getColumnIndex(TaskSchema.TaskColumns.INFORMATION));
            info[2] = result.getString(result.getColumnIndex(TaskSchema.TaskColumns.DATE));
        }
        return info;
    }

    public void deleteTask(String title)
    {
        this.getWritableDatabase().delete(TaskSchema.TaskColumns.TABLE_NAME, TaskSchema.TaskColumns.TITLE + " LIKE ?",new String[]{title});
    }
    public void closeDB()
    {
        this.close();
    }
}
