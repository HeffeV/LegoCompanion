package be.thomasmore.legocompanion.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import be.thomasmore.legocompanion.Adapters.CustomHomeListAdapter;
import be.thomasmore.legocompanion.Models.DatabaseHelper;
import be.thomasmore.legocompanion.Models.Item;
import be.thomasmore.legocompanion.R;

public class HomeFragment extends Fragment {

    View view;
    List<Item> items = new ArrayList<Item>();
    ListView listView;

    private DatabaseHelper db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("Fragment:   ","Home");
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        db = new DatabaseHelper(getActivity());

        listView = (ListView)view.findViewById(R.id.listViewHome);

        items = db.GetItems();
        Collections.reverse(items);

        readItems();

        return view;
    }

    private void readItems(){
        CustomHomeListAdapter adapter = new CustomHomeListAdapter(getActivity(),R.layout.list_item,items);

        listView.setAdapter(adapter);

    }


}
