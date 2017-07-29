package com.example.sun.workout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.FragmentTransaction;
import android.view.View;

public class MainActivity extends Activity implements WorkoutListFragment.WorkoutListListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Возвращает ссылку на фрагмент WorkoutDetailFragment.
        //В макете активности этому фрагменту присвоен идентификатор detail_frag.

//        WorkoutDetailFragment frag = (WorkoutDetailFragment) getFragmentManager().findFragmentById(R.id.detail_frag);
//        frag.setWorkout(1);
    }




    @Override
    public void itemClicked(long id) {
        //получить ссылку на фрейм, содержащий WorkoutDetailFragment.
        // Будет существовать, если прилож. выполняетья
        // на устростве с большим экраном
        View fragmentContainer = findViewById(R.id.fragment_container);

        /**Код выполняеться только в том случае,
        * если фрейм присутствует в макете*/

        if (fragmentContainer != null) {
            WorkoutDetailFragment details = new WorkoutDetailFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction(); //начало транзакции
            details.setWorkout(id);
            //Заменить фрагмент и добавить его в стек возврата.
            ft.replace(R.id.fragment_container, details);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE); //Включить анимацию растворения и проявления фрагментов.
            ft.addToBackStack(null);
            ft.commit(); //закрепить транзакцию
        } else {

            /**Если фрейм отсутствует, значит,
             приложение выполняется на устройстве
             с малым экраном. В этом случае следует
             запустить активность DetailActivity
             и передать ей идентификатор комплекса.*/

            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_WORKOUT_ID, (int)id);
            startActivity(intent);
        }
    }
}
