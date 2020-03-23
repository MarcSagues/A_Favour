package cat.udl.tidic.a_favour.Views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;

import cat.udl.tidic.a_favour.models.ProfileViewModel;
import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.databinding.ActivityProfileBinding;
import cat.udl.tidic.a_favour.models.UserModel;

public class ProfileView extends AppCompatActivity
{
    ProfileViewModel profileViewModel;

    //Layout elements
    ImageView back_arrow;
    TextView user_name;
    ImageView[] stars;
    ImageView profil_image;
    TextView favours_info;
    TextView user_location;
    TextView show_location;
    Button favours_btn;
    Button favourites_btn;
    Button opinions_btn;

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
        Intent intent = new Intent (v.getContext(), Login.class);
        startActivityForResult(intent, 0);
    }

    private void getAllActivityData()
    {
        back_arrow = findViewById(R.id.back_arrow);
        user_name = findViewById(R.id.name);

        stars = new ImageView[5];
        stars[0] = findViewById(R.id.star1);
        stars[1] = findViewById(R.id.star2);
        stars[2] = findViewById(R.id.star3);
        stars[3] = findViewById(R.id.star4);
        stars[4] = findViewById(R.id.star5);

        profil_image = findViewById(R.id.profile_image);
        favours_info = findViewById(R.id.favours_info);
        user_location = findViewById(R.id.user_location);
        show_location = findViewById(R.id.show_location);

        favours_btn = findViewById(R.id.favours_btn);
        favourites_btn = findViewById(R.id.favourites_btn);
        opinions_btn = findViewById(R.id.opinions_btn);
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
        user_name.setText(profileViewModel.getUsername());

        //Poso les estrelles necessaries
        stars = profileViewModel.getStars(stars);

        //Informació dels facvors que ha fet i que ha rebut
        favours_info.setText(profileViewModel.getFavoursInfo());

        //Informació de l'ubicació de l'usuari
        user_location.setText(profileViewModel.getLocation());
    }

    public void setErrorLayout()
    {

    }
}
