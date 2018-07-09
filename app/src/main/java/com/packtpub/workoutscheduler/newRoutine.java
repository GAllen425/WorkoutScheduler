package com.packtpub.workoutscheduler;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.Inflater;

import static java.lang.Integer.valueOf;


public class newRoutine extends AppCompatActivity {

    SharedPreference sharedPreference = new SharedPreference();
    public static final String ROUTINE_NAME_SHARED_PREFERENCES = "Routine_names";

    private ListView exerciseListView;
    private MyListAdapter exerciseAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_routine);

        exerciseListView = (ListView) findViewById(R.id.listview_Exercise);
        exerciseListView.setItemsCanFocus(true);

        View header = (View)getLayoutInflater().inflate(R.layout.list_headerexerciserow, null);
        exerciseListView.addHeaderView(header,null,false);

        exerciseAdapter = new MyListAdapter(this);
        exerciseListView.setAdapter(exerciseAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_list,menu);
        return true;
    }

    // <TODO> now it saves the list of routine names in shared preferences but doesnt save its data,
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_exercise:
                newExercise();
                return true;
            case R.id.menu_save:
                saveButton();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void saveButton(){
        String routineString = ((EditText)findViewById(R.id.editText_RoutineName)).getText().toString();
        ArrayList<String> routineList = sharedPreference.getRoutines(getApplicationContext());
        if(routineList != null && routineList.contains(routineString)) {
            Toast.makeText(getApplicationContext(),"Routine name already exists!", Toast.LENGTH_LONG).show();
        } else {
            sharedPreference.addRoutine(getApplicationContext(), routineString);
            Toast.makeText(getApplicationContext(), "Routine saved!", Toast.LENGTH_LONG).show();
        }
        Log.d("routine list",  sharedPreference.getRoutines(getApplicationContext()).toString());

        SQLiteDatabase database = new SQLiteDBHelper(this).getWritableDatabase();
        ContentValues values = new ContentValues();

        String dateString = DateFormat.getDateTimeInstance().format(new Date());

        // <TODO> loop over exercise rows
        for (int i=0; i< exerciseAdapter.myItems.size(); i++) {
            values.put(SQLiteDBHelper.ROUTINE_COLUMN_NAME, routineString);
            values.put(SQLiteDBHelper.ROUTINE_COLUMN_DATE, dateString);

            Exercise exercise = exerciseAdapter.myItems.get(i);
            String exerciseString = "" + exercise.getName();
            Log.d ("exerciseString", exerciseString);
            String weightString = "" + exercise.getWeight();
            String setsString = "" + exercise.getSets();
            String repsString = "" + exercise.getReps();
            String ratioString = "" + exercise.getRatio();

            values.put(SQLiteDBHelper.EXERCISE_COLUMN_NAME, exerciseString);
            values.put(SQLiteDBHelper.EXERCISE_COLUMN_WEIGHT, weightString);
            values.put(SQLiteDBHelper.EXERCISE_COLUMN_SETS, setsString);
            values.put(SQLiteDBHelper.EXERCISE_COLUMN_REPS, repsString);
            values.put(SQLiteDBHelper.EXERCISE_COLUMN_RATIO, ratioString);

            long newRowId = database.insert(SQLiteDBHelper.ROUTINE_TABLE_NAME, null, values);
        }
        //Toast.makeText(this, "The new Row Id is " + newRowId, Toast.LENGTH_LONG).show();
    }

    public void newExercise(){
        exerciseAdapter.myItems.add(new Exercise("Exercise " + (exerciseAdapter.myItems.size()+1),0, 0,0,"0:0"));
        exerciseListView.setAdapter(exerciseAdapter);
    }
}
