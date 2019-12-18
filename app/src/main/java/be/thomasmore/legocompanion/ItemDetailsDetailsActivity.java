package be.thomasmore.legocompanion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

import be.thomasmore.legocompanion.Adapters.CustomPartListAdapter;
import be.thomasmore.legocompanion.Adapters.CustomSetListAdapter;
import be.thomasmore.legocompanion.Models.DatabaseHelper;
import be.thomasmore.legocompanion.Models.Part;
import be.thomasmore.legocompanion.Models.Set;
import be.thomasmore.legocompanion.Models.User;
import be.thomasmore.legocompanion.Networking.HttpReader;
import be.thomasmore.legocompanion.Networking.JsonHelper;

public class ItemDetailsDetailsActivity extends AppCompatActivity{
    private static User user;

    String itemID;
    String fragmentName;
    Intent intent;
    Boolean setBool;
    private DatabaseHelper db;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_details_list);

        listView = findViewById(R.id.listViewDetails);

        Toolbar toolbar = findViewById(R.id.toolbarSetDetails);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        intent = new Intent(ItemDetailsDetailsActivity.this, ItemDetailsActivity.class);

        user = MainActivity.getUser();

        itemID = getIntent().getStringExtra("ItemID");
        setBool = getIntent().getBooleanExtra("Set",true);
        fragmentName = getIntent().getStringExtra("FragmentDetails");


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("ItemID", itemID);
                intent.putExtra("FragmentDetails", fragmentName);
                if(setBool){
                    intent.putExtra("Set", true); //setBool == true
                }
                else{
                    intent.putExtra("Set", false); //setBool == false
                }
                startActivity(intent);
            }
        });

        if(setBool){
            readSetParts();
        }
        else{
            readPartSets();
        }

        db = new DatabaseHelper(this);
    }

    public void readSetParts()
    {
        HttpReader httpReader = new HttpReader();
        httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                JsonHelper jsonHelper = new JsonHelper();
                final Set set = jsonHelper.getSetData(result);
                ArrayList<Part> Parts = new ArrayList<>();
                for (int i = 0; i < set.getSetParts().size(); i++ ) {
                    Parts.add(set.getSetParts().get(i).getPart());
                }

                CustomPartListAdapter adapter = new CustomPartListAdapter(ItemDetailsDetailsActivity.this,R.layout.list_item,Parts);

                listView.setAdapter(adapter);
            }
        });
        httpReader.execute(getString(R.string.server)+"/api/Set/" + itemID);

    }

    public void readPartSets()
    {
        HttpReader httpReader = new HttpReader();
        httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                JsonHelper jsonHelper = new JsonHelper();
                final Part part = jsonHelper.getPartData(result);
                ArrayList<Set> Sets = new ArrayList<>();
                for (int i = 0; i < part.getSetParts().size(); i++ ) {
                    Sets.add(part.getSetParts().get(i).getSet());
                }

                CustomSetListAdapter adapter = new CustomSetListAdapter(ItemDetailsDetailsActivity.this,R.layout.list_item,Sets);

                listView.setAdapter(adapter);
            }
        });
        httpReader.execute(getString(R.string.server)+"/api/Part/" + itemID);

    }

}
