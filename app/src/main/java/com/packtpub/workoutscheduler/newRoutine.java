package com.packtpub.workoutscheduler;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
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

import java.util.ArrayList;
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

        exerciseAdapter = new MyListAdapter();
        exerciseListView.setAdapter(exerciseAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_list,menu);
        return true;
    }

    // <TODO> now it saves the list of routine names in shared preferences but doesnt save its data,
    // look into data management for each routine
    // <TODO> check routine list so same routine list name isnt added twice.
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
        if(routineList.contains(routineString)) {
            Toast.makeText(getApplicationContext(),"Routine name already exists!", Toast.LENGTH_LONG).show();
        } else {
            sharedPreference.addRoutine(getApplicationContext(), routineString);
            Toast.makeText(getApplicationContext(), "Routine saved!", Toast.LENGTH_LONG).show();
        }
        Log.d("routine list", sharedPreference.getRoutines(getApplicationContext()).toString());
    }

    public void newExercise(){
        exerciseAdapter.myItems.add(new Exercise("Exercise " + (exerciseAdapter.myItems.size()+1),0, 0,0,"0:0"));
        exerciseListView.setAdapter(exerciseAdapter);
    }


    public class Exercise {
        private int sets;
        private int reps;
        private String name;
        private String ratio;
        private int weight;

        public Exercise(String name, int sets, int reps, int weight, String ratio){
            this.name = name;
            this.sets = sets;
            this.reps = reps;
            this.weight = weight;
            this.ratio = ratio;
        }

        public int getSets(){ return sets; }
        public int getReps() { return reps; }
        public String getName() { return name; }
        public String getRatio() { return ratio; }
        public int getWeight() { return weight; }
    }

    public class MyListAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        public ArrayList<Exercise> myItems = new ArrayList<Exercise>();

        public MyListAdapter() {
            mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            Exercise exercise = new Exercise
                ( "Exercise " + 1, 0,0,0, "0:0");
            myItems.add(exercise);
            notifyDataSetChanged();
        }

        public int getCount(){
            return myItems.size();
        }

        public Object getItem (int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView (int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if(convertView == null)
            {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.list_exerciserow,null);
                holder.name = (EditText) convertView.findViewById(R.id.editTextExercise);
                holder.sets = (EditText) convertView.findViewById(R.id.editTextSets);
                holder.reps = (EditText) convertView.findViewById(R.id.editTextReps);
                holder.ratio = (EditText) convertView.findViewById(R.id.editTextRatio);
                holder.weight = (EditText) convertView.findViewById(R.id.editTextWeight);

                convertView.setTag(holder);
            } else
                {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.name.setText(myItems.get(position).name);
            holder.ratio.setText(myItems.get(position).ratio);
            holder.sets.setText("" + myItems.get(position).sets);
            holder.reps.setText("" + myItems.get(position).reps);
            holder.weight.setText("" + myItems.get(position).weight);
            holder.name.setId(position);
            holder.weight.setId(position);
            holder.ratio.setId(position);
            holder.sets.setId(position);
            holder.reps.setId(position);

            holder.name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean hasFocus) {
                    if(!hasFocus){
                        final int position = view.getId();
                        final EditText Name = (EditText) view;
                        myItems.get(position).name = Name.getText().toString();
                    }
                }
            });
            holder.ratio.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean hasFocus) {
                    if(!hasFocus){
                        final int position = view.getId();
                        final EditText Ratio = (EditText) view;
                        myItems.get(position).ratio = Ratio.getText().toString();
                    }
                }
            });
            holder.weight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean hasFocus) {
                    if(!hasFocus){
                        final int position = view.getId();
                        final EditText Weight = (EditText) view;
                        myItems.get(position).weight = valueOf(Weight.getText().toString());
                    }
                }
            });
            holder.sets.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean hasFocus) {
                    if(!hasFocus){
                        final int position = view.getId();
                        final EditText Sets = (EditText) view;
                        myItems.get(position).sets = valueOf(Sets.getText().toString());
                    }
                }
            });
            holder.reps.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean hasFocus) {
                    if(!hasFocus){
                        final int position = view.getId();
                        final EditText Reps = (EditText) view;
                        myItems.get(position).reps = valueOf(Reps.getText().toString());
                    }
                }
            });

            return convertView;
        }

        class ViewHolder {
            EditText name;
            EditText sets;
            EditText reps;
            EditText weight;
            EditText ratio;
        }

    }


}
