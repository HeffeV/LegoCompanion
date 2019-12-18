package be.thomasmore.legocompanion;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.legocompanion.Adapters.CustomPartListAdapter;
import be.thomasmore.legocompanion.Adapters.CustomSetListAdapter;
import be.thomasmore.legocompanion.Fragments.PartDetailsFragment;
import be.thomasmore.legocompanion.Fragments.SetDetailsFragment;
import be.thomasmore.legocompanion.Models.DatabaseHelper;
import be.thomasmore.legocompanion.Models.Item;
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

        intent = new Intent(ItemDetailsDetailsActivity.this, MainActivity.class);

        user = MainActivity.getUser();

        itemID = getIntent().getStringExtra("ItemID");
        setBool = getIntent().getBooleanExtra("Set",true);

//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                switch(fragmentName){
//                    case "favorite":
//                        intent.putExtra("Fragment", "FavoriteFragment");
//                        startActivity(intent);
//                        break;
//                    case "wishlist":
//                        intent.putExtra("Fragment", "WishlistFragment");
//                        startActivity(intent);
//                        break;
//                    case "collection":
//                        intent.putExtra("Fragment", "CollectionFragment");
//                        startActivity(intent);
//                        break;
//                    case "browse":
//                        intent.putExtra("Fragment", "BrowseFragment");
//                        startActivity(intent);
//                        break;
//                    default:
//                        startActivity(intent);
//                        break;
//                }
//            }
//        });

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
