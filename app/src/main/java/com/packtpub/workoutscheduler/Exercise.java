package com.packtpub.workoutscheduler;

/**
 * Created by Gareth on 08/07/2018.
 */

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

    public void setName(String name) { this.name = name; }
    public void setRatio(String ratio) { this.ratio = ratio; }
    public void setReps(int reps) { this.reps = reps; }
    public void setSets(int sets) { this.sets = sets; }
    public void setWeight(int weight) { this.weight = weight; }
}
