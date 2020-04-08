package cat.udl.tidic.a_favour.Views;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;

import cat.udl.tidic.a_favour.R;

@SuppressLint("Registered")
public class UploadFavour extends AppCompatActivity
{

    public int total_categories=4;
    TextInputLayout title;
    TextInputLayout desc;
    TextInputLayout amount;
    Button upload;
    ImageView[] imageArrays;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anunci);
        getComponents();
        setListeners();
    }

    private void getComponents()
    {
        title = findViewById(R.id.title);
        desc = findViewById(R.id.description);
        amount = findViewById(R.id.amount);

        imageArrays = new ImageView[total_categories];
        imageArrays[0] = findViewById(R.id.daytoday);
        imageArrays[1] = findViewById(R.id.computing);
        imageArrays[2] = findViewById(R.id.reparation);
        imageArrays[3] = findViewById(R.id.others);

        upload = findViewById(R.id.upload);
    }

    private void setListeners()
    {
        for (ImageView i : imageArrays)
        {
            i.setOnClickListener(v -> selectCategory(i));
        }
    }

    private void selectCategory(ImageView imageView)
    {
        unselecttAll(imageArrays);
        imageView.setBackgroundColor(getResources().getColor(R.color.AfavourColor,null));
    }

    private void unselecttAll(ImageView[] imageViews)
    {
        for (ImageView i : imageViews)
        {
            i.setBackgroundColor(getResources().getColor(R.color.Unselected, null));
        }
    }

    //private void setCategory()
    //{ }

    //private void setText(TextView tv, String text)
    //{
    //    tv.setText(text);
    //}
}
