package com.packtpub.workoutscheduler;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Settings extends AppCompatActivity implements View.OnClickListener {

    SharedPreference sharedPreference = new SharedPreference();
    public static final String ROUTINE_NAME_SHARED_PREFERENCES = "Routine_names";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button button_Settings = (Button) findViewById(R.id.button_clearRoutine);
        button_Settings.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()) {
            //<TODO> clear the database too
            case R.id.button_clearRoutine:
                ArrayList<String> routineList = sharedPreference.getRoutines(getApplicationContext());
                for ( String routine : routineList) {
                    sharedPreference.removeRoutine(getApplicationContext(), routine);
                }
                SQLiteDatabase database = new SQLiteDBHelper(this).getWritableDatabase();
                ContentValues values = new ContentValues();
                getApplicationContext().deleteDatabase("routine_database");
                Toast.makeText(getApplicationContext(),"Routines deleted", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
