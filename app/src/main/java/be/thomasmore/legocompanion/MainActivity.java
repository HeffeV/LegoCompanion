package be.thomasmore.legocompanion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import be.thomasmore.legocompanion.Models.Set;
import be.thomasmore.legocompanion.Networking.HttpReader;
import be.thomasmore.legocompanion.Networking.JsonHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readSets();
    }

    private void readSets()
    {
        HttpReader httpReader = new HttpReader();
        httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                JsonHelper jsonHelper = new JsonHelper();
                List<Set> sets = jsonHelper.getSets(result);
                String text = " - ";
                for (int i = 0; i < sets.size(); i++ ) {
                    text += sets.get(i).getName() + " - ";
                }
                render(text);
            }
        });
        httpReader.execute("https://legocompanionapi.azurewebsites.net/api/Set");
    }

    private void render(String text)
    {
        TextView label = (TextView)findViewById(R.id.label);
        label.setText(text);
    }
}
