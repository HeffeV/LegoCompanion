package be.thomasmore.legocompanion.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import be.thomasmore.legocompanion.MainActivity;
import be.thomasmore.legocompanion.Models.Part;
import be.thomasmore.legocompanion.Models.Set;
import be.thomasmore.legocompanion.Models.User;
import be.thomasmore.legocompanion.R;

public class AccountFragment extends Fragment {
    User user;
    String userEmail;
    String userGoogleID;
    List<Set> wishlistSets;
    List<Part> wishlistParts;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("Fragment:   ","Account");
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        user = MainActivity.getUser();
        userEmail = user.getEmail();
        userGoogleID = user.getGoogleID();

        TextView text = view.findViewById(R.id.accountName);
        text.setText(userGoogleID);

        text = view.findViewById(R.id.accountEmail);
        text.setText(userEmail);

        wishlistSets = user.getWishlistSets();
        text = view.findViewById(R.id.wishlistCountSets);
        text.setText("You currently have " + wishlistSets.size() + " Sets in your wishlist");

        wishlistParts= user.getWishlistParts();
        text = view.findViewById(R.id.wishlistCountParts);
        text.setText("You currently have " + wishlistParts.size() + " parts in your wishlist");

        return view;
    }



}
