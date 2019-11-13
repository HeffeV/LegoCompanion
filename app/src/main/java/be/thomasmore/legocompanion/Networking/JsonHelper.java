package be.thomasmore.legocompanion.Networking;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import be.thomasmore.legocompanion.Models.Part;
import be.thomasmore.legocompanion.Models.Set;
import be.thomasmore.legocompanion.Models.User;

public class JsonHelper {

    public List<Set> getSetsWithDetails(String jsonText) {
        Gson gson = new Gson();
        Set[] sets = gson.fromJson(jsonText,Set[].class);
        List<Set> list = new ArrayList<Set>(Arrays.asList(sets));
        return list;
    }

    public List<Part> getPartsWithDetails(String jsonText) {
        Gson gson = new Gson();
        Part[] parts = gson.fromJson(jsonText,Part[].class);
        List<Part> list = new ArrayList<Part>(Arrays.asList(parts));
        return list;
    }

    public Set getSetData(String jsonText){
        Gson gson = new Gson();
        Set set = gson.fromJson(jsonText,Set.class);
        return set;
    }

    public Part getPartData(String jsonText){
        Gson gson = new Gson();
        Part part = gson.fromJson(jsonText,Part.class);
        return part;
    }

    public User getUserData(String jsonText){
        Gson gson = new Gson();
        User user = gson.fromJson(jsonText,User.class);
        return user;
    }


}