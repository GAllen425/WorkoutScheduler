package com.packtpub.workoutscheduler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_NewRoutine = (Button) findViewById(R.id.button_NewRoutine);
        button_NewRoutine.setOnClickListener(this);

        Button button_LogActivity = (Button) findViewById(R.id.button_LogWorkout);
        button_LogActivity.setOnClickListener(this);

        Button button_LiftNumbers = (Button) findViewById(R.id.button_liftNumbers);
        button_LiftNumbers.setOnClickListener(this);

        Button button_MyRoutine = (Button) findViewById(R.id.button_MyRoutine);
        button_MyRoutine.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()) {
            case R.id.button_NewRoutine:
                i = new Intent(this,newRoutine.class);
                startActivity(i);
                break;
            case R.id.button_LogWorkout:
                i = new Intent(this,logWorkout.class);
                startActivity(i);
                break;
            case R.id.button_liftNumbers:
                i = new Intent(this,liftNumbers.class);
                startActivity(i);
                break;
            case R.id.button_MyRoutine:
                i = new Intent(this,myRoutine.class);
                startActivity(i);
                break;
        }
    }
}
