package be.thomasmore.legocompanion.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.legocompanion.Adapters.CustomPartListAdapter;
import be.thomasmore.legocompanion.Adapters.CustomSetListAdapter;
import be.thomasmore.legocompanion.ItemDetailsActivity;
import be.thomasmore.legocompanion.MainActivity;
import be.thomasmore.legocompanion.Models.Part;
import be.thomasmore.legocompanion.Models.Set;
import be.thomasmore.legocompanion.Models.Theme;
import be.thomasmore.legocompanion.Models.User;
import be.thomasmore.legocompanion.Networking.HttpReader;
import be.thomasmore.legocompanion.Networking.JsonHelper;
import be.thomasmore.legocompanion.R;

public class BrowseFragment extends Fragment {

    ListView listView;
    ArrayList<String> Sets;
    View view;
    FloatingActionButton fab;
    TabLayout tabLayout;
    User user;
    ArrayList<String> themesList;
    String filterTheme,filterName;
    Boolean filterAZ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        Log.d("Fragment:   ","Browse");
        view = inflater.inflate(R.layout.fragment_browse, container, false);
        user=MainActivity.getUser();
        listView = (ListView)view.findViewById(R.id.listViewBrowse);
        tabLayout = (TabLayout)view.findViewById(R.id.TabLayoutBrowse);
        fab = (FloatingActionButton)view.findViewById(R.id.fabFilterBrowse);
        themesList = readThemes();
        SetUpViews();
        readSets();
        filterAZ=false;
        filterTheme="";
        filterName="";

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFilterDialog();
            }
        });

        return view;
    }

    private void SetUpViews(){
        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {

            //andere list laden depending on tab selected (parts of sets)
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

                listView.setAdapter(adapter);

                //onclicked item in een Intent variable stoppen, doorsturen naar itemdetails activity
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(getActivity(), ItemDetailsActivity.class);
                        intent.putExtra("ItemID", Long.toString(sets.get(i).getSetID()));
                        intent.putExtra("Set", true);
                        intent.putExtra("FragmentDetails","browse");
                        //activity openen, (itemdetails)
                        startActivity(intent);
                    }
                });
            }
        });
        httpReader.execute(getString(R.string.server)+"/api/Set/SetWithParts");

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

                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(getActivity(), ItemDetailsActivity.class);
                        intent.putExtra("ItemID", Long.toString(parts.get(i).getPartID()));
                        intent.putExtra("Set", false);
                        intent.putExtra("FragmentDetails","browse");
                        startActivity(intent);
                    }
                });
            }
        });
        httpReader.execute(getString(R.string.server)+"/api/Part/PartsWithSets");

    }

    public void readFilteredSets(boolean az, String name, String theme)
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

                listView.setAdapter(adapter);

                //onclicked item in een Intent variable stoppen, doorsturen naar itemdetails activity
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(getActivity(), ItemDetailsActivity.class);
                        intent.putExtra("ItemID", Long.toString(sets.get(i).getSetID()));
                        intent.putExtra("Set", true);
                        intent.putExtra("FragmentDetails","browse");
                        //activity openen, (itemdetails)
                        startActivity(intent);
                    }
                });
            }
        });
        httpReader.execute(getString(R.string.server)+"/api/Set/FilterSets?name="+name+"&theme="+theme+"&az="+az);

    }

    public ArrayList<String> readThemes()
    {
        final ArrayList<String> Themes = new ArrayList<>();
        HttpReader httpReader = new HttpReader();
        httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                JsonHelper jsonHelper = new JsonHelper();
                final List<Theme> themes = jsonHelper.getThemes(result);
                for (int i = 0; i < themes.size(); i++ ) {
                    Themes.add(themes.get(i).getThemeName());
                }
            }
        });
        httpReader.execute(getString(R.string.server)+"/api/Part/GetThemes");
        return Themes;
    }

    public void initializeDialogSpinner(final Spinner dropdown){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, themesList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                filterTheme=themesList.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                filterTheme="";
            }
        });
    }


    public void showFilterDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        LayoutInflater inflater = this.getLayoutInflater();

        final View viewInflaterDialog = inflater.inflate(R.layout.dialog_filter, null,false);
        final Spinner dropdown = (Spinner)viewInflaterDialog.findViewById(R.id.spinnerDropdownThemes);

        initializeDialogSpinner(dropdown);

        builder.setTitle("Filters:")
                .setView(viewInflaterDialog)
                .setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        EditText editNaam = (EditText) viewInflaterDialog.findViewById(R.id.naam);
                        filterName = editNaam.getText().toString();

                        filterAZ = ((CheckBox)viewInflaterDialog.findViewById(R.id.checkbox)).isChecked();

                        readFilteredSets(filterAZ,filterName,filterTheme);

                        //dismiss on apply
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }




}
