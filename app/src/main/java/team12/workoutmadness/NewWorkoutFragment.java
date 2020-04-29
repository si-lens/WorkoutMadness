package team12.workoutmadness;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Objects;

import team12.workoutmadness.models.Day;
import team12.workoutmadness.models.Workout;

public class NewWorkoutFragment extends Fragment {
    private static final String TAG = "NewWorkoutFragment";
    private Button btnSave;
    private EditText workout_name;
    private CheckBox mon,tue,wed,thur,fri,sat,sun;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.new_workout, container, false);
        setViews(view);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createWorkout(getContext());
            }
        });

        return view;
    }
    private void createWorkout(Context context) {
        if(!workout_name.getText().toString().isEmpty()) {
            String workoutName = workout_name.getText().toString();
            workout_name.getText().clear();
            Workout workout = new Workout(workoutName, getSelectedDays());
            ((MainActivity) Objects.requireNonNull(getActivity())).setWorkout(workout);
            ((MainActivity)getActivity()).setViewPager(0);
        } else {
            Toast.makeText(context,"Name your workout and select days",Toast.LENGTH_SHORT).show();
        }
    }
    private void setViews(View view){
        //Button
        btnSave = view.findViewById(R.id.btnSave);
        //EditText
        workout_name = view.findViewById(R.id.workout_name);
        //CheckBox
        mon= view.findViewById(R.id.monday_box);
        tue= view.findViewById(R.id.tuesday_box);
        wed= view.findViewById(R.id.wednesday_box);
        thur= view.findViewById(R.id.thursday_box);
        fri= view.findViewById(R.id.friday_box);
        sat= view.findViewById(R.id.saturday_box);
        sun= view.findViewById(R.id.sunday_box);
    }



    private ArrayList<Day> getSelectedDays(){
        CheckBox[] checkBoxes = new CheckBox[]{mon,tue,wed,thur,fri,sat,sun};
        ArrayList<Day> days = new ArrayList<>();
        for (CheckBox cb: checkBoxes) {
            if(cb.isChecked()){
                days.add(new Day(cb.getText().toString()));
                cb.setChecked(false);
            }
        }
        return days;
    }


}
