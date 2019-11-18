package be.thomasmore.legocompanion.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

import be.thomasmore.legocompanion.Adapters.CustomPartListAdapter;
import be.thomasmore.legocompanion.Adapters.CustomSetListAdapter;
import be.thomasmore.legocompanion.MainActivity;
import be.thomasmore.legocompanion.Models.User;
import be.thomasmore.legocompanion.R;

public class CollectionFragment extends Fragment {

    View view;
    User user;
    ListView listView;
    TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("Fragment:   ","Collection");
        view = inflater.inflate(R.layout.fragment_collection, container, false);
        user=MainActivity.getUser();
        listView = (ListView)view.findViewById(R.id.listViewCollection);
        tabLayout = (TabLayout)view.findViewById(R.id.TabLayoutCollection);
        SetUpViews();
        readSets();
        return view;
    }

    private void SetUpViews(){
        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==0){
                    readSets();
                }
                else{
                    readParts();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if(tab.getPosition()==0){
                    readSets();
                }
                else{
                    readParts();
                }
            }
        });
    }

    private void readSets(){
            CustomSetListAdapter adapter = new CustomSetListAdapter(getActivity(),R.layout.list_item,user.getCollectionSets());

            listView.setAdapter(adapter);
    }
    private void readParts(){
            CustomPartListAdapter adapter = new CustomPartListAdapter(getActivity(),R.layout.list_item,user.getCollectionParts());

            listView.setAdapter(adapter);
    }
}
