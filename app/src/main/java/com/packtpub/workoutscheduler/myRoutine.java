package com.packtpub.workoutscheduler;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class myRoutine extends AppCompatActivity {

    SharedPreference sharedPreference = new SharedPreference();
    public static final String ROUTINE_NAME_SHARED_PREFERENCES = "Routine_names";
    Spinner spinner_exercise;
    Spinner spinner_routine;

    private ListView exerciseListView;
    private MyListAdapter exerciseAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_routine);

        //Routine Spinner
        spinner_routine = findViewById(R.id.spinner_Routine);
        loadRoutineSpinnerData();
        spinner_routine.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loadExerciseSpinnerData();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Exercise Spinner
        spinner_exercise = findViewById(R.id.spinner_Exercise);
        loadExerciseSpinnerData();

        //Specific Exercise logged data
        //<TODO> change Exercise -> Date
        // <TODO> change Day Ratio ->
        exerciseListView = findViewById(R.id.listview_Exercise);
        exerciseListView.setItemsCanFocus(true);

        View header = (View)getLayoutInflater().inflate(R.layout.list_headerexerciserow, null);
        exerciseListView.addHeaderView(header,null,false);
        exerciseAdapter = new MyListAdapter(this);
        exerciseListView.setAdapter(exerciseAdapter);
    }

    public List<String> getExercisesByRoutine(String routine)
    {
        SQLiteDatabase database = new SQLiteDBHelper(this).getReadableDatabase();
        Cursor cursor = database.query(
                "routine",
                new String[]{"name","exercise"},
                "name =\"" + routine + "\"",
                null,
                null,
                null,
                null);

        ArrayList<String> exerciseArrayList = new ArrayList<>();
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            // The Cursor is now set to the right position
            exerciseArrayList.add(cursor.getString(1));
            Log.d("EXERCISE list", exerciseArrayList.toString());
        }
        cursor.close();

        return exerciseArrayList;
    }

    public void loadRoutineSpinnerData () {
        ArrayAdapter<String> routineSpinnerAdapter = new ArrayAdapter<String>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                sharedPreference.getRoutines(this));
        spinner_routine.setAdapter(routineSpinnerAdapter);
    }

    public void loadExerciseSpinnerData() {
        ArrayAdapter<String> exerciseSpinnerAdapter = new ArrayAdapter<String>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                getExercisesByRoutine(spinner_routine.getSelectedItem().toString()));
        spinner_exercise.setAdapter(exerciseSpinnerAdapter);
    }

}
