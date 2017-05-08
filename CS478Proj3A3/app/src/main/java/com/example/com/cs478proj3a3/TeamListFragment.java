package com.example.com.cs478proj3a3;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;


public class TeamListFragment extends ListFragment {
    public ListSelectionListener listener = null;

    public List<String> team;

    public void setTeam(List<String> t){
        team = t;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }//end onCreateView(...)


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            //set the ListSelectionListener for communicating with the activity
            listener = (ListSelectionListener) activity;

        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnArticleSelectedListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);

        //set the list adapter for the ListView
        setListAdapter(new ArrayAdapter<String>(getActivity(), R.layout.team_list_item, team));

        //set the list choice mode to allow only one selection at a time
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        //indicates the selected item has been checked
        getListView().setItemChecked(position, true);

        //inform the activity that the item in position "position" has been selected
        listener.onListSelection(position);
    }
}//end TeamListFragment
