package com.packtpub.workoutscheduler;

import android.app.Application;

import java.util.List;

public class GlobalState extends Application {
    private List<String> test = null;

    public List<String> getTest() {
        return test;
    }
    public void setTest (List<String> test){
        this.test = test;
    }
}
