package com.example.sun.workout;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutDetailFragment extends Fragment {


    /**
     * У каждого фраг-
     * мента должен
     * быть определен
     * открытый кон-
     * структор без аргументов.
     */

    public WorkoutDetailFragment() {
        // Required empty public constructor
    }

    //Идентификатор комплекса упражнений, выбранного пользователем.
    private long workoutId;

    /**
     * Метод onCreateView() вызывается тогда, когда
     * Android потребуется макет фрагмента.
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        if (savedInstanceState != null) {
            //Задать значение workoutId.
            workoutId = savedInstanceState.getLong("workoutId");
        } else {
            //транзакция выполняеться только тогда, когда ссылка savedInstanceStat будет null

            //использовать транзакцию фрагмента ля добавленияфрагмента с секундомером во фрейм

            FragmentTransaction ft = getChildFragmentManager().beginTransaction();
            StopwatchFragment stopwatch = new StopwatchFragment();
            ft.replace(R.id.stopwatch_container, stopwatch);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();

            // Inflate the layout for this fragment

            /**Сообщает Android, какой макет ис-
             пользуется фрагментом (в данном
             случае fragment_workout_detail)*/
        }
        return inflater.inflate(R.layout.fragment_workout_detail, container, false);
    }


    /**
     * Метод getView() получает корневой объект View фрагмента. Далее полученный
     * объект используется для полуения ссылок
     * на надписи, предназначенные для названия
     * и описания комплекса упражнений.
     * <p>
     * У фрагментов отсутствует
     * метод findViewById(). Чтобы получить ссылку на представления
     * фрагмента, сначала необходимо получить ссылку на корневое пред-
     * ставление фрагмента методом getView() и по этой ссылке найти
     * дочерние представления.
     */

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if (view != null) {
            TextView title = (TextView) view.findViewById(R.id.textTitle);
            Workout workout = Workout.workouts[(int) workoutId];
            title.setText(workout.getName());
            TextView description = (TextView) view.findViewById(R.id.textDescription);
            description.setText(workout.getDescription());
        }
    }

    /**
     * Сохранить значение workoutId в объекте savedInstanceState
     * типа Bundle перед уничтожением фрагмента. Позднее сохра-
     * ненное значение читается в методе onCreateView().
     */

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putLong("workoutId", workoutId);
    }

    //Метод для присваивания идентификатора.
    // Метод используется активностью для передачи значения идентификатора фрагменту

    public void setWorkout(long id) {
        this.workoutId = id;
    }
}
