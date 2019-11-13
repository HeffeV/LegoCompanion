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

import com.bumptech.glide.Glide;

import java.util.List;

import be.thomasmore.legocompanion.Models.Part;
import be.thomasmore.legocompanion.R;

public class CustomPartListAdapter extends ArrayAdapter<Part> {

    Context mCtx;
    int resource;
    List<Part> partList;

    public CustomPartListAdapter(Context mCtx, int resource, List<Part>partList){
        super(mCtx,resource,partList);

        this.mCtx = mCtx;
        this.resource = resource;
        this.partList=partList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        View view = inflater.inflate(resource,null);

        TextView textViewTitle = view.findViewById(R.id.textViewTitle);
        TextView textViewDescription = view.findViewById(R.id.description);
        ImageView imageView = view.findViewById(R.id.imageViewItemList);

        Part part = partList.get(position);

        textViewTitle.setText(part.getPartName());
        textViewDescription.setText(Integer.toString(part.getLegoCode()));
        Glide.with(view).load(part.getImages().get(0).getImageUrl()).into(imageView);

        return view;
    }

}
