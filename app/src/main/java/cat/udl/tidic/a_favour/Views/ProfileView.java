package cat.udl.tidic.a_favour.Views;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import cat.udl.tidic.a_favour.FORTESTING;
import cat.udl.tidic.a_favour.ImageHelper;
import cat.udl.tidic.a_favour.MainPageClasses.DataModel;
import cat.udl.tidic.a_favour.models.Favour;
import cat.udl.tidic.a_favour.ProfileClasses.RecyclerViewManager;
import cat.udl.tidic.a_favour.models.ProfileViewModel;
import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.databinding.ActivityProfileBinding;
import cat.udl.tidic.a_favour.models.UserModel;
import cat.udl.tidic.a_favour.preferences.PreferencesProvider;

public class ProfileView extends AppCompatActivity
{
    ProfileViewModel profileViewModel;
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
        profileViewModel = new ProfileViewModel(this);
        activityProfileBinding.setProfileViewModel(profileViewModel);
        getAllActivityData();
        preparePage();
        setUpProfileListeners();
    }

    private void preparePage()
    {
        SharedPreferences mPreferences = PreferencesProvider.providePreferences();

        Bundle b = getIntent().getExtras();
        if (b != null)
        {
            Favour currentFavour = (Favour) getIntent().getSerializableExtra("favour");
            assert currentFavour != null;
            ismyProfile = currentFavour.getOwner_id() == mPreferences.getInt("id",-1);

            if (!ismyProfile)
            {
                edit.setVisibility(View.GONE);
                profileViewModel.getAnotherUser(String.valueOf(currentFavour.getOwner_id()));
                profileViewModel.getMyFavoursVoid(String.valueOf((currentFavour.getOwner_id())));
            }
        }
        //Si no s'ha passat cap variable... vol dir que és el meu perfil
        else
            { Log.d("No s'ha passat cap variable", "");
                    ismyProfile = true;
                    profileViewModel.getUser();
                    profileViewModel.getMyFavoursVoid(String.valueOf(mPreferences.getInt("id",0)));
            }
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

    private void getAllActivityData()
    {
        layout = findViewById(R.id.constraint);
        backArrow = findViewById(R.id.back_arrow);
        userName = findViewById(R.id.name);
        stars = findViewById(R.id.stars);
        profilImage = findViewById(R.id.profile_image);
        profilImage.setImageBitmap(ImageHelper.getRoundedCornerBitmap(profilImage, ImageHelper.ROUND));
        favoursInfo = findViewById(R.id.favoursInfo);
        userLocation = findViewById(R.id.user_location);
        showLocation = findViewById(R.id.show_location);
        edit = findViewById(R.id.edit);
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


    private void getRecyclerData()
    {
        setUpRecyclerView();
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
        if(FORTESTING.dev)
        {
            DataModel.Favour[] list = FORTESTING.getExampleList();
            DataModel.Opinion[] op = FORTESTING.getExampleListOPINION();
            //recyclerManager = new RecyclerViewManager(getSupportFragmentManager(), ProfileView.this, ismyProfile,list, list, op );
            getRecyclerData();
        }
        else {
            profileViewModel.getMyFavours_().observe(this,this::setMyFavoursList);
        }
    }

    private void setMyFavoursList(List<Favour> favours)
    {
        Favour[] favoursarray = new Favour[favours.size()];
        favoursarray = favours.toArray(favoursarray);
        DataModel.Opinion[] opinions = FORTESTING.getExampleListOPINION();
        recyclerManager = new RecyclerViewManager(getSupportFragmentManager(), ProfileView.this, this.ismyProfile,favoursarray, favoursarray, opinions );
        getRecyclerData();
    }

    private void onGetUserData(UserModel u)
    {

        if (!FORTESTING.dev)
        {
            if(u == null)
            {
                Log.d("THE USER IS NULL", "ASD");
            }
            else
            {
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
    //Intent intent = new Intent (this, LoginView.class);
    //startActivityForResult(intent, 0);

    @Override
    public void onResume()
    {
        super.onResume();
    }


}

