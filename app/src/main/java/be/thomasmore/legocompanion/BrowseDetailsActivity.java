package be.thomasmore.legocompanion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;

import be.thomasmore.legocompanion.Models.Part;
import be.thomasmore.legocompanion.Models.Set;
import be.thomasmore.legocompanion.Networking.HttpReader;
import be.thomasmore.legocompanion.Networking.JsonHelper;

public class BrowseDetailsActivity extends AppCompatActivity {

    Boolean set;
    String itemID;

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

        itemID = getIntent().getStringExtra("ItemID");
        set = getIntent().getBooleanExtra("Set",true);

        if(set){
            getSet(itemID);
        }
        else{
            getPart(itemID);
        }
    }

    private void getSet(String itemID){
        HttpReader httpReader = new HttpReader();
        httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                JsonHelper jsonHelper = new JsonHelper();
                final Set set = jsonHelper.getSetData(result);

                getSupportActionBar().setTitle(set.getSetName());
                TextView textView = (TextView)findViewById(R.id.textViewDetailsTitle);
                textView.setText(set.getSetName());

                ImageView imageView = (ImageView)findViewById(R.id.imageViewDetails);
                Glide.with(BrowseDetailsActivity.this).load(set.getImages().get(0).getImageUrl()).into(imageView);
            }
        });
        httpReader.execute("https://legocompanionapi.azurewebsites.net/api/Set/"+itemID);
    }

    private void getPart(String itemID){
        HttpReader httpReader = new HttpReader();
        httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                JsonHelper jsonHelper = new JsonHelper();
                final Part part = jsonHelper.getPartData(result);

                getSupportActionBar().setTitle(part.getPartName());
                TextView textView = (TextView)findViewById(R.id.textViewDetailsTitle);
                textView.setText(part.getPartName());

                ImageView imageView = (ImageView)findViewById(R.id.imageViewDetails);
                Glide.with(BrowseDetailsActivity.this).load(part.getImages().get(0).getImageUrl()).into(imageView);
            }
        });
        httpReader.execute("https://legocompanionapi.azurewebsites.net/api/Part/"+itemID);
    }
}
