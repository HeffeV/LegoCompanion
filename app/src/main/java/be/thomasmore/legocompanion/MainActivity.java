package be.thomasmore.legocompanion;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;

import be.thomasmore.legocompanion.Fragments.BrowseFragment;
import be.thomasmore.legocompanion.Fragments.CollectionFragment;
import be.thomasmore.legocompanion.Fragments.FavoriteFragment;
import be.thomasmore.legocompanion.Fragments.HomeFragment;
import be.thomasmore.legocompanion.Fragments.WishlistFragment;
import be.thomasmore.legocompanion.Models.User;
import be.thomasmore.legocompanion.Networking.HttpReader;
import be.thomasmore.legocompanion.Networking.JsonHelper;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public User user;

    GoogleSignInClient mGoogleSignInClient;
    TextView userName,userEmail;
    ImageView userImage;
    private DrawerLayout drawer;

    String fragmentName;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        user = new User();
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        fragmentName = getIntent().getStringExtra("Fragment");
        setFragmentStart(navigationView);

        View header = navigationView.getHeaderView(0);
        userName = header.findViewById(R.id.userName);
        userEmail = header.findViewById(R.id.userEmail);
        userImage = header.findViewById(R.id.userIcon);
        GetUser();
    }

    private void setFragmentStart(NavigationView navigationView){
        Fragment fragment = new Fragment();
        FragmentManager fm;
        FragmentTransaction transaction;

        if(fragmentName==null){
            fragment = new HomeFragment();
            navigationView.setCheckedItem(R.id.nav_home);
        }
        else if(fragmentName.equals("BrowseFragment")){
            fragment = new BrowseFragment();
            navigationView.setCheckedItem(R.id.nav_search);
            Log.i("FragmentLoad", fragmentName);
        }
        fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment;
        FragmentManager fm;
        FragmentTransaction transaction;
        switch (menuItem.getItemId()){
            case R.id.nav_home:
                fragment = new HomeFragment();
                fm = getSupportFragmentManager();
                transaction = fm.beginTransaction();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.commit();
                break;
            case R.id.nav_search:
                fragment = new BrowseFragment();
                fm = getSupportFragmentManager();
                transaction = fm.beginTransaction();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.commit();
                break;
            case R.id.nav_wishlist:
                fragment = new WishlistFragment();
                fm = getSupportFragmentManager();
                transaction = fm.beginTransaction();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.commit();
                break;
            case R.id.nav_favorite:
                fragment = new FavoriteFragment();
                fm = getSupportFragmentManager();
                transaction = fm.beginTransaction();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.commit();
                break;
            case R.id.nav_collection:
                fragment = new CollectionFragment();
                fm = getSupportFragmentManager();
                transaction = fm.beginTransaction();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.commit();
                break;
            case R.id.nav_logout:
                signOut();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void GetUser(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);

        //userImage = findViewById(R.id.userImage)

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            Uri personPhoto = acct.getPhotoUrl();
            userName.setText(acct.getDisplayName());
            userEmail.setText(acct.getEmail());
            Glide.with(this).load(acct.getPhotoUrl()).apply(RequestOptions.circleCropTransform()).into(userImage);
            GetUserData(acct.getId(),acct.getEmail());
        }
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(MainActivity.this,"Signed out",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }

    private void GetUserData(String id,String email){
        final JsonHelper jsonHelper = new JsonHelper();

        HttpReader httpReader= new HttpReader();
        httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                user = jsonHelper.getUserData(result);
                Toast.makeText(MainActivity.this,user.getEmail(),Toast.LENGTH_SHORT).show();
            }
        });
        httpReader.execute(getString(R.string.server)+"/api/User/UserData?id="+id+"&email="+email);

    }
}
