package com.example.sun.workout;


import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ArrayAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutListFragment extends ListFragment {


    static interface WorkoutListListener {
        void itemClicked(long id);
        //  Добавить слушателя  к фрагменту.
    }

    ;

    private WorkoutListListener listener;

    public WorkoutListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //массив строк с названием комплексов упражнений
        String[] names = new String[Workout.workouts.length];
        for (int i = 0; i < names.length; i++) {
            names[i] = Workout.workouts[i].getName();
        }
        //адаптер массива
        //получение контекста от инфлатера
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1, names);
        setListAdapter(adapter);
        //связать адаптер массива со списковім представлением
        return super.onCreateView(inflater, container, savedInstanceState);
        //Вызов метода onCreateView() суперкласса предоставляет макет по умолчанию для ListFragment
    }

    @Override
    //Вызывается при присоединении фрагмента к активности.
    public void onAttach(Activity activity) {

        super.onAttach(activity);
        this.listener = (WorkoutListListener) activity;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (listener != null) {
            //  Сообщить слушателю о то м, что
            //  на одном из вариантов ListView  был сделан щелчок
            listener.itemClicked(id);
        }
    }

}
