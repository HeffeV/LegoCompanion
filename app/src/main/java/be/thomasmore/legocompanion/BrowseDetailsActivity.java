package be.thomasmore.legocompanion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;

import be.thomasmore.legocompanion.Models.Part;
import be.thomasmore.legocompanion.Models.Set;
import be.thomasmore.legocompanion.Models.User;
import be.thomasmore.legocompanion.Networking.HttpReader;
import be.thomasmore.legocompanion.Networking.JsonHelper;

public class BrowseDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    String userID;
    Boolean setBool;
    Set set;
    Part part;
    String itemID;
    Button buttonFavorite,buttonWishlist,buttonCollection;
    MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_details);

        Toolbar toolbar = findViewById(R.id.toolbarSetDetails);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BrowseDetailsActivity.this, MainActivity.class);
                intent.putExtra("Fragment", "BrowseFragment");
                startActivity(intent);
            }
        });

        buttonFavorite = findViewById(R.id.buttonAddFavorite);
        buttonWishlist = findViewById(R.id.buttonAddWishList);
        buttonCollection = findViewById(R.id.buttonAddCollection);

        userID = getIntent().getStringExtra("UserID");
        itemID = getIntent().getStringExtra("ItemID");
        setBool = getIntent().getBooleanExtra("Set",true);

        if(setBool){
            getSet(itemID);
        }
        else{
            getPart(itemID);
        }
        InitializeButtons();
    }

    private void InitializeButtons(){
       buttonFavorite.setOnClickListener(this);
       buttonCollection.setOnClickListener(this);
       buttonWishlist.setOnClickListener(this);
    }

    private void getSet(String itemID){
        HttpReader httpReader = new HttpReader();
        httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                JsonHelper jsonHelper = new JsonHelper();
                set = jsonHelper.getSetData(result);

                getSupportActionBar().setTitle(set.getSetName());
                //TextView textView = (TextView)findViewById(R.id.textViewDetailsTitle);
                //textView.setText(set.getSetName());

                ImageView imageView = (ImageView)findViewById(R.id.imageViewDetails);
                Glide.with(BrowseDetailsActivity.this).load(set.getImages().get(0).getImageUrl()).into(imageView);
            }
        });
        httpReader.execute(getString(R.string.server)+"/api/Set/"+itemID);
    }

    private void getPart(String itemID){
        HttpReader httpReader = new HttpReader();
        httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                JsonHelper jsonHelper = new JsonHelper();
                part = jsonHelper.getPartData(result);

                getSupportActionBar().setTitle(part.getPartName());
                //TextView textView = (TextView)findViewById(R.id.textViewDetailsTitle);
                //textView.setText(part.getPartName());

                ImageView imageView = (ImageView)findViewById(R.id.imageViewDetails);
                Glide.with(BrowseDetailsActivity.this).load(part.getImages().get(0).getImageUrl()).into(imageView);
            }
        });
        httpReader.execute(getString(R.string.server)+"/api/Part/"+itemID);
    }

    private void AddSetToX(String itemID, String userID,int type){
        HttpReader httpReader = new HttpReader();
        httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                JsonHelper jsonHelper = new JsonHelper();
                final User user = jsonHelper.getUserData(result);
            }
        });
        if(type==1){
            httpReader.execute(getString(R.string.server)+"/api/Set/AddSetToWishList?userId="+userID+"&setId="+itemID);
        }
        else if(type==2){
            httpReader.execute(getString(R.string.server)+"/api/Set/AddSetToFavorites?userId="+userID+"&setId="+itemID);
        }
        else if(type==3){
            httpReader.execute(getString(R.string.server)+"/api/Set/AddSetToCollection?userId="+userID+"&setId="+itemID);
        }
    }

    private void AddPartToX(String itemID, String userID,int type){
        HttpReader httpReader = new HttpReader();
        httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                JsonHelper jsonHelper = new JsonHelper();
                final User user = jsonHelper.getUserData(result);
            }
        });
        if(type==1){
            httpReader.execute(getString(R.string.server)+"/api/Part/AddPartToWishList?userId="+userID+"&partId="+itemID);
        }
        else if(type==2){
            httpReader.execute(getString(R.string.server)+"/api/Part/AddPartToFavorites?userId="+userID+"&partId="+itemID);
        }
        else if(type==3){
            httpReader.execute(getString(R.string.server)+"/api/Part/AddPartToCollection?userId="+userID+"&partId="+itemID);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonAddWishList:
                if(setBool){
                    AddSetToX(itemID,userID,1);
                    Toast.makeText(BrowseDetailsActivity.this,set.getSetName() +" added to your wishlist!",Toast.LENGTH_SHORT).show();
                }
                else{
                    AddPartToX(itemID,userID,1);
                    Toast.makeText(BrowseDetailsActivity.this,part.getPartName() +" added to your wishlist!",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.buttonAddFavorite:
                if(setBool){
                    AddSetToX(itemID,userID,2);
                    Toast.makeText(BrowseDetailsActivity.this,set.getSetName() +" added to your favorites!",Toast.LENGTH_SHORT).show();
                }
                else{
                    AddPartToX(itemID,userID,2);
                    Toast.makeText(BrowseDetailsActivity.this,part.getPartName() +" added to your wishlist!",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.buttonAddCollection:
                if(setBool){
                    AddSetToX(itemID,userID,3);
                    Toast.makeText(BrowseDetailsActivity.this,set.getSetName() +" added to your collection!",Toast.LENGTH_SHORT).show();
                }
                else{
                    AddPartToX(itemID,userID,3);
                    Toast.makeText(BrowseDetailsActivity.this,part.getPartName() +" added to your wishlist!",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
