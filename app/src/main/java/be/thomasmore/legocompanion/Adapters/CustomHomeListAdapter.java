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

import be.thomasmore.legocompanion.Models.Item;
import be.thomasmore.legocompanion.R;

public class CustomHomeListAdapter extends ArrayAdapter<Item> {

    Context mCtx;
    int resource;
    List<Item> itemList;

    public CustomHomeListAdapter(Context mCtx, int resource, List<Item>itemList){
        super(mCtx,resource,itemList);

        this.mCtx = mCtx;
        this.resource = resource;
        this.itemList=itemList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        View view = inflater.inflate(resource,null);

        TextView textViewTitle = view.findViewById(R.id.textViewTitle);
        TextView textViewDescription = view.findViewById(R.id.description);
        ImageView imageView = view.findViewById(R.id.imageViewItemList);

        Item item = itemList.get(position);

        textViewTitle.setText(item.getName());
        textViewDescription.setText(item.getDescription());
        Glide.with(view).load(item.getImageUrl()).into(imageView);

        return view;
    }


}
