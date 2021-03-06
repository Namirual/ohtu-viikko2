package ohtu;

import java.util.ArrayList;

public class Submission {

    private int week;
    private int hours;
    private ArrayList<Integer> exercises;

    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeek() {
        return week;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public ArrayList<Integer> getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList<Integer> exercises) {
        this.exercises = exercises;
    }

    @Override
    public String toString() {
        String string = "";
        string = "Tehtyjä tehtäviä yhteensä " + getExercises().size()
                + ", aikaa kului " + getHours() + " tuntia, tehdyt tehtävät";
        for (int tehtava : getExercises()) {
            string += " " + tehtava;
        }
        return string;
    }

}
