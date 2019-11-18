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

    User user;
    String userID;
    Boolean setBool,inWishlist = false,inCollection=false,inFavorites=false;
    Set set;
    Part part;
    String itemID;
    Button buttonFavorite,buttonWishlist,buttonCollection;

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

        user = MainActivity.getUser();

        itemID = getIntent().getStringExtra("ItemID");
        setBool = getIntent().getBooleanExtra("Set",true);

        if(setBool){
            getSet(itemID);
        }
        else{
            getPart(itemID);
        }
    }

    private void InitializeButtons(){
       buttonFavorite.setOnClickListener(this);
       buttonCollection.setOnClickListener(this);
       buttonWishlist.setOnClickListener(this);

       if(setBool){
           for (int i=0; i<user.getWishlistSets().size(); i++) {
               if(user.getWishlistSets().get(i).getSetID()==set.getSetID()){
                   inWishlist = true;
               }
           }
           for (int i=0; i<user.getCollectionSets().size(); i++) {
               if(user.getCollectionSets().get(i).getSetID()==set.getSetID()){
                   inCollection = true;
               }
           }
           for (int i=0; i<user.getFavoriteSets().size(); i++) {
               if(user.getFavoriteSets().get(i).getSetID()==set.getSetID()){
                   inFavorites = true;
               }
           }
       }
       else
       {
           for (int i=0; i<user.getWishlistParts().size(); i++) {
               if(user.getWishlistParts().get(i).getPartID()==part.getPartID()){
                   inWishlist = true;
               }
           }
           for (int i=0; i<user.getCollectionParts().size(); i++) {
               if(user.getCollectionParts().get(i).getPartID()==part.getPartID()){
                   inCollection = true;
               }
           }
           for (int i=0; i<user.getFavoriteParts().size(); i++) {
               if(user.getFavoriteParts().get(i).getPartID()==part.getPartID()){
                   inFavorites = true;
               }
           }
       }

        if(inWishlist){
            buttonWishlist.setText("Remove from wishlist");
        }
        if(inFavorites){
            buttonFavorite.setText("Remove from favorites");
        }
        if(inCollection){
            buttonCollection.setText("Remove from collection");
        }

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
                InitializeButtons();
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
                InitializeButtons();
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
                user = jsonHelper.getUserData(result);
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
                user = jsonHelper.getUserData(result);
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

    private void RemoveSetFromX(String itemID, String userID,int type){
        HttpReader httpReader = new HttpReader();
        httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                JsonHelper jsonHelper = new JsonHelper();
                user = jsonHelper.getUserData(result);
            }
        });
        if(type==1){
            httpReader.execute(getString(R.string.server)+"/api/Set/RemoveSetFromWishlist?userId="+userID+"&setId="+itemID);
        }
        else if(type==2){
            httpReader.execute(getString(R.string.server)+"/api/Set/RemoveSetFromFavorites?userId="+userID+"&setId="+itemID);
        }
        else if(type==3){
            httpReader.execute(getString(R.string.server)+"/api/Set/RemoveSetFromCollection?userId="+userID+"&setId="+itemID);
        }
    }

    private void RemovePartFromX(String itemID, String userID,int type){
        HttpReader httpReader = new HttpReader();
        httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                JsonHelper jsonHelper = new JsonHelper();
                user = jsonHelper.getUserData(result);
            }
        });
        if(type==1){
            httpReader.execute(getString(R.string.server)+"/api/Part/RemovePartFromWishlist?userId="+userID+"&partId="+itemID);
        }
        else if(type==2){
            httpReader.execute(getString(R.string.server)+"/api/Part/RemovePartFromFavorites?userId="+userID+"&partId="+itemID);
        }
        else if(type==3){
            httpReader.execute(getString(R.string.server)+"/api/Part/RemovePartFromCollection?userId="+userID+"&partId="+itemID);
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonAddWishList:
                if(setBool){

                    if(!inWishlist){
                        AddSetToX(itemID,Long.toString(user.getUserID()),1);
                        Toast.makeText(BrowseDetailsActivity.this,set.getSetName() +" added to your wishlist!",Toast.LENGTH_SHORT).show();
                        buttonWishlist.setText("Remove from wishlist");
                        inWishlist=true;
                    }
                    else{
                        RemoveSetFromX(itemID,Long.toString(user.getUserID()),1);
                        Toast.makeText(BrowseDetailsActivity.this,set.getSetName() +" removed from your wishlist!",Toast.LENGTH_SHORT).show();
                        buttonWishlist.setText("Add to wishlist");
                        inWishlist=false;
                    }
                }
                else{
                    if(!inWishlist){
                        AddPartToX(itemID,Long.toString(user.getUserID()),1);
                        Toast.makeText(BrowseDetailsActivity.this,part.getPartName() +" added to your wishlist!",Toast.LENGTH_SHORT).show();
                        buttonWishlist.setText("Remove from wishlist");
                        inWishlist=true;
                    }
                    else{
                        RemovePartFromX(itemID,Long.toString(user.getUserID()),1);
                        Toast.makeText(BrowseDetailsActivity.this,part.getPartName() +" removed from your wishlist!",Toast.LENGTH_SHORT).show();
                        buttonWishlist.setText("Add to wishlist");
                        inWishlist=false;
                    }
                }
                break;
            case R.id.buttonAddFavorite:
                if(setBool){
                    if(!inFavorites){
                        AddSetToX(itemID,Long.toString(user.getUserID()),2);
                        Toast.makeText(BrowseDetailsActivity.this,set.getSetName() +" added to your favorites!",Toast.LENGTH_SHORT).show();
                        buttonFavorite.setText("Remove from favorites");
                        inFavorites=true;
                    }
                    else{
                        RemoveSetFromX(itemID,Long.toString(user.getUserID()),2);
                        Toast.makeText(BrowseDetailsActivity.this,set.getSetName() +" removed from your favorites!",Toast.LENGTH_SHORT).show();
                        buttonFavorite.setText("Add to favorites");
                        inFavorites=false;
                    }
                }
                else{
                    if(!inFavorites){
                        AddPartToX(itemID,Long.toString(user.getUserID()),2);
                        Toast.makeText(BrowseDetailsActivity.this,part.getPartName() +" added to your favorites!",Toast.LENGTH_SHORT).show();
                        buttonFavorite.setText("Remove from favorites");
                        inFavorites=true;
                    }
                    else{
                        RemovePartFromX(itemID,Long.toString(user.getUserID()),2);
                        Toast.makeText(BrowseDetailsActivity.this,part.getPartName() +" removed from your favorites!",Toast.LENGTH_SHORT).show();
                        buttonFavorite.setText("Add to favorites");
                        inFavorites=false;
                    }
                }
                break;
            case R.id.buttonAddCollection:
                if(setBool){
                    if(!inCollection){
                        AddSetToX(itemID,Long.toString(user.getUserID()),3);
                        Toast.makeText(BrowseDetailsActivity.this,set.getSetName() +" added to your collection!",Toast.LENGTH_SHORT).show();
                        buttonCollection.setText("Remove from collection");
                        inCollection=true;
                    }
                    else{
                        RemoveSetFromX(itemID,Long.toString(user.getUserID()),3);
                        Toast.makeText(BrowseDetailsActivity.this,set.getSetName() +" removed from your collection!",Toast.LENGTH_SHORT).show();
                        buttonCollection.setText("Add to collection");
                        inCollection=false;
                    }
                }
                else{
                    if(!inCollection){
                        AddPartToX(itemID,Long.toString(user.getUserID()),3);
                        Toast.makeText(BrowseDetailsActivity.this,part.getPartName() +" added to your collection!",Toast.LENGTH_SHORT).show();
                        buttonCollection.setText("Remove from collection");
                        inCollection=true;
                    }
                    else{
                        RemovePartFromX(itemID,Long.toString(user.getUserID()),3);
                        Toast.makeText(BrowseDetailsActivity.this,part.getPartName() +" removed from your collection!",Toast.LENGTH_SHORT).show();
                        buttonCollection.setText("Add to collection");
                        inCollection=false;
                    }
                }
                break;
        }
    }
}
