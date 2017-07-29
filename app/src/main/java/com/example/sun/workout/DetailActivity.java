package com.example.sun.workout;

import android.app.Activity;
import android.os.Bundle;

public class DetailActivity extends Activity {
    public static final String EXTRA_WORKOUT_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //получить ссылку на фрагмент
        WorkoutDetailFragment workoutDetailFragment = (WorkoutDetailFragment) getFragmentManager().findFragmentById(R.id.detail_frag);
        //Получить идентификатор комплекса, выбранного пользователем, из интента.
        int workoutId = (int) getIntent().getExtras().get(EXTRA_WORKOUT_ID);
        //Передать идентификатор комплекса фрагменту
        workoutDetailFragment.setWorkout(workoutId);

    }
}
