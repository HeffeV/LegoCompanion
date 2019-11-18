package be.thomasmore.legocompanion.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import be.thomasmore.legocompanion.BrowseDetailsActivity;
import be.thomasmore.legocompanion.Models.Set;
import be.thomasmore.legocompanion.R;

public class SetDetailsFragment extends Fragment {

    View view;
    Set set;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("Fragment:   ","SetDetails");
        set = BrowseDetailsActivity.GetSet();

        View view = inflater.inflate(R.layout.fragment_setdetails, container, false);

        TextView text = view.findViewById(R.id.LegoAgeData);
        text.setText(Integer.toString(set.getAge()));

        text = view.findViewById(R.id.LegoThemeData);
        text.setText(set.getTheme());

        text = view.findViewById(R.id.LegoCodeData);
        text.setText(Integer.toString(set.getLegoCode()));

        text = view.findViewById(R.id.LegoPartsData);
        text.setText(Integer.toString(set.getPieces()));

        text = view.findViewById(R.id.LegoPriceData);
        text.setText(Double.toString(set.getPrice())+ " Euro");

        text = view.findViewById(R.id.LegoYearData);
        text.setText(Integer.toString(set.getReleaseYear()));



        return view;
    }


}
