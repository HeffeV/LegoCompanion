package be.thomasmore.legocompanion.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import be.thomasmore.legocompanion.ItemDetailsActivity;
import be.thomasmore.legocompanion.Models.Part;
import be.thomasmore.legocompanion.R;

public class PartDetailsFragment extends Fragment {

    View view;
    Part part;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("Fragment:   ","PartDetails");
        part = ItemDetailsActivity.GetPart();

        View view = inflater.inflate(R.layout.fragment_partdetails, container, false);

        TextView text = view.findViewById(R.id.LegoCodeDataPart);
        text.setText(Integer.toString(part.getLegoCode()));

        text = view.findViewById(R.id.LegoColorDataPart);
        text.setText(part.getColor());

        text = view.findViewById(R.id.LegoPriceDataPart);
        text.setText(Double.toString(part.getPrice())+" Euro");

        return view;
    }


}
