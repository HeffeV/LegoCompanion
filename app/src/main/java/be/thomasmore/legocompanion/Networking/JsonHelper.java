package be.thomasmore.legocompanion.Networking;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.legocompanion.Models.Set;

public class JsonHelper {

    public List<Set> getSets(String jsonText) {
        List<Set> list = new ArrayList<Set>();

        try {
            JSONArray jsonArraySet = new JSONArray(jsonText);
            for (int i = 0; i < jsonArraySet.length(); i++) {
                JSONObject jsonObjectSet = jsonArraySet.getJSONObject(i);

                Set set = new Set();
                set.setSetID(jsonObjectSet.getLong("setID"));
                set.setSetName(jsonObjectSet.getString("setName"));
                JSONArray json = jsonObjectSet.getJSONArray("setParts");
                Log.d("JsonArray", json.toString());
                list.add(set);
            }
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        return list;
    }



}