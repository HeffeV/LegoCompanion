package be.thomasmore.legocompanion.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.legocompanion.Adapters.CustomPartListAdapter;
import be.thomasmore.legocompanion.Adapters.CustomSetListAdapter;
import be.thomasmore.legocompanion.BrowseDetailsActivity;
import be.thomasmore.legocompanion.Models.Part;
import be.thomasmore.legocompanion.Models.Set;
import be.thomasmore.legocompanion.Networking.HttpReader;
import be.thomasmore.legocompanion.Networking.JsonHelper;
import be.thomasmore.legocompanion.R;

public class BrowseFragment extends Fragment {

    ListView listViewSets,listViewParts;
    ArrayList<String> Sets;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("Fragment:   ","Browse");
        view = inflater.inflate(R.layout.fragment_browse, container, false);
        listViewSets = (ListView)view.findViewById(R.id.listViewSets);
        listViewParts=(ListView)view.findViewById(R.id.listViewParts);
        readSets();
        readParts();
        return view;
    }

    public void readSets()
    {
        HttpReader httpReader = new HttpReader();
        httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                JsonHelper jsonHelper = new JsonHelper();
                final List<Set> sets = jsonHelper.getSetsWithDetails(result);
                ArrayList<Set> Sets = new ArrayList<>();
                for (int i = 0; i < sets.size(); i++ ) {
                    Sets.add(sets.get(i));
                }

                CustomSetListAdapter adapter = new CustomSetListAdapter(getActivity(),R.layout.list_item,Sets);

                listViewSets.setAdapter(adapter);

                listViewSets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(getActivity(), BrowseDetailsActivity.class);
                        intent.putExtra("ItemID", Long.toString(sets.get(i).getSetID()));
                        intent.putExtra("Set", true);
                        startActivity(intent);
                    }
                });
            }
        });
        httpReader.execute("https://legocompanionapi.azurewebsites.net/api/Set/SetWithParts");

    }

    public void readParts()
    {
        HttpReader httpReader = new HttpReader();
        httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                JsonHelper jsonHelper = new JsonHelper();
                final List<Part> parts = jsonHelper.getPartsWithDetails(result);
                ArrayList<Part> Parts = new ArrayList<>();
                for (int i = 0; i < parts.size(); i++ ) {
                    Parts.add(parts.get(i));
                    Log.i("Part", parts.get(i).getPartName());
                }

                CustomPartListAdapter adapter = new CustomPartListAdapter(getActivity(),R.layout.list_item,Parts);

                listViewParts.setAdapter(adapter);

                listViewParts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(getActivity(), BrowseDetailsActivity.class);
                        intent.putExtra("ItemID", Long.toString(parts.get(i).getPartID()));
                        intent.putExtra("Set", false);
                        startActivity(intent);
                    }
                });
            }
        });
        httpReader.execute("https://legocompanionapi.azurewebsites.net/api/Part/PartsWithSets");

    }
}
