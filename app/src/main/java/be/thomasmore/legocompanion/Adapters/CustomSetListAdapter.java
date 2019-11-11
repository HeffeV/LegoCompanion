package be.thomasmore.legocompanion.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import be.thomasmore.legocompanion.Models.Set;
import be.thomasmore.legocompanion.R;

public class CustomSetListAdapter extends ArrayAdapter<Set> {

    Context mCtx;
    int resource;
    List<Set> setList;

    public CustomSetListAdapter(Context mCtx, int resource, List<Set>setList){
        super(mCtx,resource,setList);

        this.mCtx = mCtx;
        this.resource = resource;
        this.setList=setList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        View view = inflater.inflate(resource,null);

        TextView textViewTitle = view.findViewById(R.id.textViewTitle);
        TextView textViewDescription = view.findViewById(R.id.description);
        ImageView imageView = view.findViewById(R.id.imageViewItemList);

        Set set = setList.get(position);

        textViewTitle.setText(set.toString());
        //textViewDescription.setText(set.getTheme().toString());
        //Glide.with(view).load(set.getImages().get(0).getImageUrl()).into(imageView);

        return view;
    }

}
