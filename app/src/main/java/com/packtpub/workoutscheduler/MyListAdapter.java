package com.packtpub.workoutscheduler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import java.util.ArrayList;

import static java.lang.Integer.valueOf;

public class MyListAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    public ArrayList<Exercise> myItems = new ArrayList<Exercise>();

    public MyListAdapter(Context mContext) {
        mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

        holder.name.setText(myItems.get(position).getName());
        holder.ratio.setText(myItems.get(position).getRatio());
        holder.sets.setText("" + myItems.get(position).getSets());
        holder.reps.setText("" + myItems.get(position).getReps());
        holder.weight.setText("" + myItems.get(position).getWeight());
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
                    myItems.get(position).setName( Name.getText().toString() );
                }
            }
        });
        holder.ratio.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    final int position = view.getId();
                    final EditText Ratio = (EditText) view;
                    myItems.get(position).setRatio( Ratio.getText().toString() );
                }
            }
        });
        holder.weight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    final int position = view.getId();
                    final EditText Weight = (EditText) view;
                    myItems.get(position).setWeight( valueOf(Weight.getText().toString()) );
                }
            }
        });
        holder.sets.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    final int position = view.getId();
                    final EditText Sets = (EditText) view;
                    myItems.get(position).setSets( valueOf(Sets.getText().toString()) );
                }
            }
        });
        holder.reps.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    final int position = view.getId();
                    final EditText Reps = (EditText) view;
                    myItems.get(position).setReps ( valueOf(Reps.getText().toString()) );
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
