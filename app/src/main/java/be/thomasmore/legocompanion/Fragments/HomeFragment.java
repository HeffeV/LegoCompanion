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
import java.util.List;

import be.thomasmore.legocompanion.Adapters.CustomSetListAdapter;
import be.thomasmore.legocompanion.Models.Set;
import be.thomasmore.legocompanion.Networking.HttpReader;
import be.thomasmore.legocompanion.Networking.JsonHelper;
import be.thomasmore.legocompanion.R;

public class HomeFragment extends Fragment {
    ListView listView;
    ArrayList<String> Sets;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("Fragment:   ","Home");
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        listView = (ListView)view.findViewById(R.id.listViewSets);
        readSets();

        return view;
    }

    private void readSets()
    {
        HttpReader httpReader = new HttpReader();
        httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                JsonHelper jsonHelper = new JsonHelper();
                List<Set> sets = jsonHelper.getSets(result);
                ArrayList<Set> Sets = new ArrayList<>();
                for (int i = 0; i < sets.size(); i++ ) {
                    Sets.add(sets.get(i));
                    Log.d("Set:",sets.get(i).toString());
                }

                CustomSetListAdapter adapter = new CustomSetListAdapter(getActivity(),R.layout.list_item,Sets);

                listView.setAdapter(adapter);

                //TextView textView = (TextView)getView().findViewById(R.id.homeText);
                //textView.setText(text);
            }
        });
        httpReader.execute("https://legocompanionapi.azurewebsites.net/api/Set/SetWithParts");

    }
}
