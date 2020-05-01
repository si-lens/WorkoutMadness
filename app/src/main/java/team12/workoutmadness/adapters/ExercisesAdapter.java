package team12.workoutmadness.adapters;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;

import team12.workoutmadness.R;
import team12.workoutmadness.models.Day;
import team12.workoutmadness.models.Exercise;
import team12.workoutmadness.models.Set;

public class ExercisesAdapter extends ArrayAdapter<Exercise> {

    private ArrayList<Exercise> exercises;

    public ExercisesAdapter(@NonNull Context context, @NonNull ArrayList<Exercise> exercises) {
        super(context, 0, exercises);
        this.exercises = exercises;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {

        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.exercise_lv_element,null);
        }
        //Get current exercise that's being loaded
        Exercise exercise = exercises.get(position);
        TextView exercise_name = view.findViewById(R.id.exercise_name);
        LinearLayout exerciseLinearLayout = view.findViewById(R.id.exercise_linear_layout);
        exerciseLinearLayout.removeAllViews();
        exerciseLinearLayout.addView(exercise_name);
        CardView exerciseCard = view.findViewById(R.id.exercise_card);
        //Set the name for currently loaded exercise
        exercise_name.setText(exercise.getName());
        int exercisesCount = exercise.getSets().size();

        //For each exercise, load all sets with reps and weight details
        for(int i=0; i<exercisesCount; i++){
            TextView row = new TextView(view.getContext());
            Set currentSet = exercise.getSets().get(i);
            String exerciseDetails = setDetails(i+1,currentSet);
            row.setTextSize(30);
            row.setGravity(Gravity.CENTER);
            row.setText(exerciseDetails);
            exerciseLinearLayout.addView(row);
        }
        return view;
    }
    //This method return set information depending on its content
    private String setDetails(int setNumber,Set set){
        String s = setNumber+"s: "+set.getReps();
        if(set.getWeight() == null) {
        return s;
        } else
            return s+" x "+set.getWeight()+"kg";
    }
}


