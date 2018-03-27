package com.packtpub.workoutscheduler;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class myRoutine extends AppCompatActivity {

    public static final String ROUTINE_NAME_SHARED_PREFERENCES = "Routine_names";

    private ListView exerciseListView;
    private newRoutine.MyListAdapter exerciseAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_routine);

        exerciseListView = (ListView) findViewById(R.id.listview_Exercise);
        exerciseListView.setItemsCanFocus(true);

        View header = (View)getLayoutInflater().inflate(R.layout.list_headerexerciserow, null);
        exerciseListView.addHeaderView(header,null,false);


        SharedPreferences routineSharedPref = PreferenceManager.
                getDefaultSharedPreferences(this.getApplicationContext());

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
    }
}
