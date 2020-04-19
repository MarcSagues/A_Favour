package cat.udl.tidic.a_favour.Views;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import cat.udl.tidic.a_favour.FORTESTING;
import cat.udl.tidic.a_favour.ImageHelper;
import cat.udl.tidic.a_favour.ProfileClasses.RecyclerViewManager;
import cat.udl.tidic.a_favour.models.ProfileViewModel;
import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.databinding.ActivityProfileBinding;
import cat.udl.tidic.a_favour.models.UserModel;

import static androidx.core.content.ContextCompat.startActivity;

public class ProfileView extends AppCompatActivity
{
    ProfileViewModel profileViewModel;
    RelativeLayout loadingbar;
    RecyclerViewManager recyclerManager;

    //Layout elements
    ConstraintLayout layout;
    ImageView backArrow;
    TextView userName;
    RatingBar stars;
    ImageView profilImage;
    TextView favoursInfo;
    TextView userLocation;
    TextView showLocation;
    String favoursDone;
    String timesHelped;
    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;
    Boolean ismyProfile;
    ImageView edit;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Binding Data
        ActivityProfileBinding activityProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        profileViewModel = new ProfileViewModel();
        activityProfileBinding.setProfileViewModel(profileViewModel);
        getAllActivityData();
        preparePage();
        //The recycle Manager
        recyclerManager = new RecyclerViewManager(getSupportFragmentManager(), ProfileView.this, ismyProfile);
        //Get all the layout data

        setUpRecyclerView();
        getRecyclerData();
        setUpProfileListeners();
    }



    private void preparePage()
    {

        Bundle b = getIntent().getExtras();
        if (b != null)
        {
            ismyProfile = b.getBoolean("myprofile",true);
            if (!ismyProfile)
            {
                edit.setVisibility(View.GONE);
            }

            System.out.println(String.valueOf(b.getBoolean("myprofile",true)));

        }
        else
            { Log.d("No s'ha passat cap variable",""); ismyProfile =true ;}
    }
    public void backArrowAction(View v)
    {
       onBackPressed();
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }

    public void onEditClick(View v){
        Intent intent = new Intent (v.getContext(), EditProfileView.class);
        startActivityForResult(intent, 0);
    }


    private void setUpRecyclerView()
    {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(recyclerManager);
    }
    private void getAllActivityData()
    {
        layout = findViewById(R.id.constraint);
        loadingbar = findViewById(R.id.loadingPanel);
        backArrow = findViewById(R.id.back_arrow);
        userName = findViewById(R.id.name);
        stars = findViewById(R.id.stars);
        profilImage = findViewById(R.id.profile_image);
        profilImage.setImageBitmap(ImageHelper.getRoundedCornerBitmap(profilImage, ImageHelper.ROUND));
        favoursInfo = findViewById(R.id.favoursInfo);
        userLocation = findViewById(R.id.user_location);
        showLocation = findViewById(R.id.show_location);
        edit = findViewById(R.id.edit);
        if (FORTESTING.dev) {loadingbar.setVisibility(View.GONE);}

        //favoursBtn = findViewById(R.id.favours_btn);
        //favouritesBtn = findViewById(R.id.favourites_btn);
        //opinionsBtn = findViewById(R.id.opinions_btn);
        //Falta crear tot lo relacionat amb els anuncis que ha publicat
    }

    private void getRecyclerData()
    {
        for (int i = 0; i < tabLayout.getTabCount(); i++)
        {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            assert tab != null;
            tab.setCustomView(recyclerManager.setTabTittles(i));
        }
    }
    private void setUpProfileListeners()
    {
        profileViewModel.getUserProfile().observe(this, this::onGetUserData);
    }

    private void onGetUserData(UserModel u)
    {

        if (FORTESTING.dev)
        {
            loadingbar.setVisibility(View.GONE);
        }
        else
        {
            if(u == null)
            {
                Log.d("THE USER IS NULL", "ASD");
                loadingbar.setVisibility(View.GONE);
                setErrorLayout();
            }
            else
            {

                loadingbar.setVisibility(View.GONE);
                layout.setVisibility(View.VISIBLE);
                //El nom de l'usuari
                userName.setText(profileViewModel.getUsername());

                //Poso les estrelles necessaries
                stars.setRating(profileViewModel.getStars());
                //Informació dels facvors que ha fet i que ha rebut
                favoursDone = profileViewModel.getFavoursDone();
                timesHelped = profileViewModel.getTimesHelped();
                String favoursDoneString = getResources().getString(R.string.favoursDone);
                String timesHelpedString = getResources().getString(R.string.timesHelped);
                favoursInfo.setText(String.format("%s %s, %s %s", favoursDone, favoursDoneString, timesHelped, timesHelpedString));

                //Informació de l'ubicació de l'usuari
                userLocation.setText(profileViewModel.getLocation());
            }
        }
    }


    public void setErrorLayout()
    {
        //Si falla la connexió s'haura de posar un layout de "error"
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(R.string.dialogMessage).setTitle(R.string.dialogTitle);

        builder.setPositiveButton(R.string.retry, (dialog, id) ->
        {
            profileViewModel.getUser();
            loadingbar.setVisibility(View.VISIBLE);

        });
        builder.setNegativeButton(R.string.cancel, (dialog, id) ->
        {
            dialog.cancel();
            Intent intent = new Intent (this, LoginView.class);
            startActivityForResult(intent, 0);
        });

        builder.show();
    }

    @Override
    public void onResume() {
        super.onResume();
    }


}

