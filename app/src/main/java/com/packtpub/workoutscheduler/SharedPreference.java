package com.packtpub.workoutscheduler;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SharedPreference {
    public static final String PREFS_NAME = "ROUTINE_APP";
    public static final String ROUTINES = "Routines_Saved";

    public SharedPreference() {
        super();
    }

    // This four methods are used for maintaining favorites.
    public void saveRoutines(Context context, ArrayList<String> routines) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(routines);

        editor.putString(ROUTINES, jsonFavorites);

        editor.commit();
    }

    public void addRoutine(Context context, String routineString) {
        ArrayList<String> routines = getRoutines(context);
        if (routines == null)
            routines = new ArrayList<String>();
        routines.add(routineString);
        saveRoutines(context, routines);
    }

    public void removeRoutine(Context context, String routineString) {
        ArrayList<String> favorites = getRoutines(context);
        if (favorites != null) {
            favorites.remove(routineString);
            saveRoutines(context, favorites);
        }
    }

    public ArrayList<String> getRoutines(Context context) {
        SharedPreferences settings;
        List<String> routines;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(ROUTINES)) {
            String jsonFavorites = settings.getString(ROUTINES, null);
            Gson gson = new Gson();
            String[] routineItems = gson.fromJson(jsonFavorites, String[].class);
            routines = Arrays.asList(routineItems);
            routines = new ArrayList<String>(routines);
        } else
            return null;

        return (ArrayList<String>) routines;
    }
}
