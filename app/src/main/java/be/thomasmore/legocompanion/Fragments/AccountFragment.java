package be.thomasmore.legocompanion.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import java.util.List;

import be.thomasmore.legocompanion.MainActivity;
import be.thomasmore.legocompanion.Models.Part;
import be.thomasmore.legocompanion.Models.Set;
import be.thomasmore.legocompanion.Models.User;
import be.thomasmore.legocompanion.R;

public class AccountFragment extends Fragment {
    User user;
    int userID;
    String set, part;
    List<Set> wishlistSets, collectionSets;
    List<Part> wishlistParts, collectionParts;
    GoogleSignInClient mGoogleSignInClient;
    TextView userName,userEmail;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("Fragment:   ","Account");
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        user = MainActivity.getUser();

        userName = view.findViewById(R.id.accountName);
        userEmail = view.findViewById(R.id.accountEmail);

        wishlistSets = user.getWishlistSets();
        wishlistParts= user.getWishlistParts();
        TextView text = view.findViewById(R.id.wishlistCount);
        if(wishlistSets.size() > 1){set = "sets";}else{set = "set";}
        if(wishlistParts.size() > 1){part = "parts";}else{part = "part";}
        text.setText("You currently have " + wishlistSets.size() + " "+ set + " and " + wishlistParts.size() +" " + part + " in your wishlist");

        collectionSets = user.getCollectionSets();
        collectionParts = user.getCollectionParts();
        if(collectionSets.size() > 1){set = "sets";}else{set = "set";}
        if(collectionParts.size() > 1){part = "parts";}else{part = "part";}
        text = view.findViewById(R.id.collectionCount);
        text.setText("You currently have " +collectionSets.size()+ " "+ set + " and " + collectionParts.size() +" " + part + " in your collection");

        GetUser();

        return view;
    }

    private void GetUser(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this.getContext(),gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this.getContext());
        if (acct != null) {
            Uri personPhoto = acct.getPhotoUrl();
            userName.setText(acct.getDisplayName());
            userEmail.setText(acct.getEmail());
        }
    }



}
