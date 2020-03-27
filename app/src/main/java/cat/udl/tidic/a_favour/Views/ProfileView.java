package cat.udl.tidic.a_favour.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import cat.udl.tidic.a_favour.models.ProfileViewModel;
import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.databinding.ActivityProfileBinding;
import cat.udl.tidic.a_favour.models.UserModel;

public class ProfileView extends AppCompatActivity
{
    ProfileViewModel profileViewModel;

    //Layout elements
    ImageView backArrow;
    TextView userName;
    RatingBar stars;
    ImageView profilImage;
    TextView favoursInfo;
    TextView userLocation;
    TextView showLocation;
    Button favoursBtn;
    Button favouritesBtn;
    Button opinionsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ActivityProfileBinding activityProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        profileViewModel = new ProfileViewModel();
        activityProfileBinding.setProfileViewModel(profileViewModel);
        getAllActivityData();
        setUpProfileListeners();
    }

    public void backArrowAction(View v){
        Intent intent = new Intent (v.getContext(), LoginView.class);
        startActivityForResult(intent, 0);
    }

    private void getAllActivityData()
    {
        backArrow = findViewById(R.id.back_arrow);
        userName = findViewById(R.id.name);

        stars = findViewById(R.id.stars);

        profilImage = findViewById(R.id.profile_image);
        favoursInfo = findViewById(R.id.favours_info);
        userLocation = findViewById(R.id.user_location);
        showLocation = findViewById(R.id.show_location);

        favoursBtn = findViewById(R.id.favours_btn);
        favouritesBtn = findViewById(R.id.favourites_btn);
        opinionsBtn = findViewById(R.id.opinions_btn);
        //Falta crear tot lo relacionat amb els anuncis que ha publicat
    }

    private void setUpProfileListeners()
    {
        profileViewModel.getUserProfile().observe(this, this::onGetUserData);
    }

    private void onGetUserData(UserModel u)
    {
        //profil_image.setImageResource();

        //El nom de l'usuari
        userName.setText(profileViewModel.getUsername());

        //Poso les estrelles necessaries
        stars.setRating(profileViewModel.getStars());

        //Informació dels facvors que ha fet i que ha rebut
        favoursInfo.setText(profileViewModel.getFavoursInfo());

        //Informació de l'ubicació de l'usuari
        userLocation.setText(profileViewModel.getLocation());
    }

    public void setErrorLayout()
    {
        //Si falla la connexió s'haura de posar un layout de "error"
    }
}
