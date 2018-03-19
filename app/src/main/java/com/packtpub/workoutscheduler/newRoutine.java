package com.packtpub.workoutscheduler;

import android.app.Activity;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import static com.packtpub.workoutscheduler.newRoutine.Exercise.TYPE_BODY;
import static com.packtpub.workoutscheduler.newRoutine.Exercise.TYPE_HEADER;

public class newRoutine extends AppCompatActivity {

    private ListView exerciseListView;
    private ListAdapter exerciseAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_routine);

        exerciseListView = (ListView) findViewById(R.id.listview_Exercise);
        View header = (View)getLayoutInflater().inflate(R.layout.list_headerexerciserow, null);
        exerciseListView.addHeaderView(header,null,false);
        exerciseListView.setAdapter(exerciseAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_list,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_insert:
                newExercise();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void newExercise(){
        // make a custom adapter
        exerciseListView.setAdapter();
    }


    public class Exercise {
        private int sets;
        private int reps;
        private String name;
        private String ratio;
        int header;

        public static final int TYPE_HEADER=0;
        public static final int TYPE_BODY=1;

        public Exercise(String name, int sets, int reps, String ratio, int header){
            this.name = name;
            this.sets = sets;
            this.reps = reps;
            this.ratio = ratio;
            this.header = header;
        }

        public int getHeader(){return header;}
        public int getSets(){ return sets; }
        public int getReps() { return reps; }
        public String getName() { return name; }
        public String getRatio() { return ratio; }

    }


}
