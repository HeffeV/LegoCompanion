package be.thomasmore.legocompanion.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.tabs.TabLayout;

import be.thomasmore.legocompanion.Adapters.CustomPartListAdapter;
import be.thomasmore.legocompanion.Adapters.CustomSetListAdapter;
import be.thomasmore.legocompanion.ItemDetailsActivity;
import be.thomasmore.legocompanion.Models.User;
import be.thomasmore.legocompanion.Networking.HttpReader;
import be.thomasmore.legocompanion.Networking.JsonHelper;
import be.thomasmore.legocompanion.R;

public class WishlistFragment extends Fragment {

    View view;
    User user;
    ListView listView;
    TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("Fragment:   ","Wishlist");

        view = inflater.inflate(R.layout.fragment_wishlist, container, false);
        listView = (ListView)view.findViewById(R.id.listViewWishlist);
        tabLayout = (TabLayout)view.findViewById(R.id.TabLayoutWishlist);
        SetUpViews();
        getUser();
        return view;
    }

    private void SetUpViews(){
        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==0){
                    readSets();
                }
                else{
                    readParts();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if(tab.getPosition()==0){
                    readSets();
                }
                else{
                    readParts();
                }
            }
        });
    }

    private void readSets(){
            CustomSetListAdapter adapter = new CustomSetListAdapter(getActivity(), R.layout.list_item, user.getWishlistSets());

            listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ItemDetailsActivity.class);
                intent.putExtra("ItemID", Long.toString(user.getWishlistSets().get(i).getSetID()));
                intent.putExtra("Set", true);
                intent.putExtra("FragmentDetails","wishlist");
                startActivity(intent);
            }
        });
    }
    private void readParts(){
            CustomPartListAdapter adapter = new CustomPartListAdapter(getActivity(), R.layout.list_item, user.getWishlistParts());

            listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ItemDetailsActivity.class);
                intent.putExtra("ItemID", Long.toString(user.getWishlistParts().get(i).getPartID()));
                intent.putExtra("Set", false);
                intent.putExtra("FragmentDetails","wishlist");
                startActivity(intent);
            }
        });
    }

    private void getUser(){
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());
        if (acct != null) {
            final JsonHelper jsonHelper = new JsonHelper();

            HttpReader httpReader= new HttpReader();
            httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
                @Override
                public void resultReady(String result) {
                    user = jsonHelper.getUserData(result);
                    readSets();
                }
            });
            httpReader.execute(getString(R.string.server)+"/api/User/UserData?id="+acct.getId()+"&email="+acct.getEmail());
        }
    }
}
