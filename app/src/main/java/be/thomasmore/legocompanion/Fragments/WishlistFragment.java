package be.thomasmore.legocompanion.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import be.thomasmore.legocompanion.Adapters.CustomSetListAdapter;
import be.thomasmore.legocompanion.MainActivity;
import be.thomasmore.legocompanion.Models.User;
import be.thomasmore.legocompanion.R;

public class WishlistFragment extends Fragment {
    User user;
    ListView listView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("Fragment:   ","Wishlist");
        MainActivity mainActivity = (MainActivity)getActivity();
        user=mainActivity.user;
        Log.d("Fragment: User:   ",user.getEmail());
        View view = inflater.inflate(R.layout.fragment_wishlist, container, false);
        listView = (ListView)view.findViewById(R.id.listViewSets);

        CustomSetListAdapter adapter = new CustomSetListAdapter(getActivity(),R.layout.list_item,user.getWishlistSets());

        listView.setAdapter(adapter);

        return view;
    }
}
