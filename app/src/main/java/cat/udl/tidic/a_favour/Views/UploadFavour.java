package cat.udl.tidic.a_favour.Views;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.util.Objects;
import cat.udl.tidic.a_favour.MainPageClasses.CategoryManager;
import cat.udl.tidic.a_favour.MainPageClasses.DataModel;
import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.models.UploadFavourModel;
import cat.udl.tidic.a_favour.preferences.PreferencesProvider;
import cat.udl.tidic.a_favour.view.FavoursListActivity;

public class UploadFavour extends AppCompatActivity
{
    public static int TITLE_LENGTH = 50;
    public static int DESCRIPTION_LENGTH = 600;
    public static int AMOUNT_LENGTH = 10;
    public static int INPUTS = 3;
    boolean upload_bool;

    public int total_categories=5;

    Button upload;
    ImageView[] imageArrays;
    TextView uploadtTitle;
    TextView[] wordCounter;
    TextInputEditText[] inputEditTexts;
    TextInputLayout amount_parent;
    DataModel.Favour currentFavour;
    UploadFavourModel uploadFavourModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anunci);
        getComponents();
        getBundle();
        setListeners();
        uploadFavourModel = new UploadFavourModel(this);
    }

    private void getBundle()
    {
        Bundle b = getIntent().getExtras();
        if(b != null)
        {
            upload_bool = b.getBoolean("upload");
            currentFavour = (DataModel.Favour) getIntent().getSerializableExtra("favour");
        }
        Log.d("Open upload page", String.valueOf(upload_bool));
        prepareUpload(upload_bool);
        //if (!upload_bool)
        //{
            //DataModel d = (DataModel) b.getParcelable("data");
        //}

    }

    @SuppressLint("ResourceType")
    private void prepareUpload(boolean upload_)
    {
        if(!upload_)
        {
            uploadtTitle.setText(getResources().getString(R.string.editFavour));
            upload.setText(getResources().getString(R.string.edit_));
            setAllData();
        }
        else
        {
            uploadtTitle.setText(getResources().getString(R.string.uploadFavour));
            upload.setText(getResources().getString(R.string.upload));

        }
    }

    @SuppressLint("SetTextI18n")
    private void setAllData()
    {


        for (ImageView i : imageArrays)
        {
            if (i.getTag().equals(currentFavour.getCategoria()))
            {
                selectCategory(i);
            }
        }
        inputEditTexts[0].setText(currentFavour.getName());
        inputEditTexts[1].setText(currentFavour.getDescription());
        if (!currentFavour.getCategoria().equals(CategoryManager.CATEGORIES.favourxfavour.name())) {
            inputEditTexts[2].setText("" + currentFavour.amount);
        }
    }

    @Override
    public void onBackPressed()
    {
        if (upload_bool) {
            super.onBackPressed();
            finish();
        }
        else
        {
            Intent i = new Intent(this, AnunciView.class);
            Bundle b= new Bundle();
            b.putBoolean("myfavour", true);
            i.putExtras(b);
            i.putExtra("favour", currentFavour);
            startActivityForResult(i, 1);
            finish();
        }
    }

    public void onSucces()
    {
        Intent i = new Intent(this, AnunciView.class);
        Bundle b= new Bundle();
        b.putBoolean("myfavour", true);
        i.putExtras(b);
        i.putExtra("favour", getCurrentFavourData());
        startActivityForResult(i, 1);
        finish();
    }
    private void getComponents()
    {
        inputEditTexts = new TextInputEditText[INPUTS];
        inputEditTexts[0] = findViewById(R.id.title);
        inputEditTexts[1] = findViewById(R.id.description);
        inputEditTexts[2] = findViewById(R.id.amount);
        uploadtTitle = findViewById(R.id.uploadtTitle);

        imageArrays = new ImageView[total_categories];

        imageArrays[0] = findViewById(R.id.daytoday);
        imageArrays[0].setTag(CategoryManager.CATEGORIES.daytodaythings.name());

        imageArrays[1] = findViewById(R.id.computing);
        imageArrays[1].setTag(CategoryManager.CATEGORIES.computing.name());

        imageArrays[2] = findViewById(R.id.reparation);
        imageArrays[2].setTag(CategoryManager.CATEGORIES.reparation.name());

        imageArrays[3] = findViewById(R.id.others);
        imageArrays[3].setTag(CategoryManager.CATEGORIES.others.name());

        imageArrays[4] = findViewById(R.id.favourxfavour);
        imageArrays[4].setTag(CategoryManager.CATEGORIES.favourxfavour.name());

        upload = findViewById(R.id.upload);

        wordCounter = new TextView[INPUTS];
        wordCounter[0] = findViewById(R.id.titleCounter);
        wordCounter[1] = findViewById(R.id.descCounter);
        wordCounter[2] = findViewById(R.id.amountCounter);

        amount_parent = findViewById(R.id.amount_parent);

    }

    private void setListeners()
    {
        for (ImageView i : imageArrays)
        {
            i.setOnClickListener(v -> selectCategory(i));
        }

        for (int i=0; i < inputEditTexts.length; i++)
        {
            int finalI = i;
            inputEditTexts[i].setOnFocusChangeListener((v, hasFocus) -> enableTextCount(hasFocus, finalI));

            inputEditTexts[i].addTextChangedListener(new TextWatcher()
            {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after)
                {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count)
                {
                    setCount(finalI);
                }
                @Override
                public void afterTextChanged(Editable s)
                {

                }
            });
        }

        UploadFavour u = this;
        upload.setOnClickListener(v -> {
            currentFavour = getCurrentFavourData();
            if (currentFavour != null) {
                if (!upload_bool) {
                    uploadFavourModel.editFavour(getCurrentFavourData(),u);
                } else {
                    uploadFavourModel.postFavour(getCurrentFavourData(),u);
                }
            }
        });
    }

    @SuppressWarnings("deprecation")
    private String getSelectedCategory()
    {
        for (ImageView iv: imageArrays)
        {
            if (Objects.requireNonNull(iv.getBackgroundTintList()).equals(ColorStateList.valueOf(getResources().getColor(R.color.AfavourColor))))
                return iv.getTag().toString();
        }
        return null;
    }

    public DataModel.Favour getCurrentFavourData()
    {
        try
        {
            if (currentFavour == null)
            {
                SharedPreferences mPreferences = PreferencesProvider.providePreferences();
                currentFavour = new DataModel.Favour("", "", 0, "", mPreferences.getInt("id", 0), "",-1,0,0);
            }
            currentFavour.setName(Objects.requireNonNull(inputEditTexts[0].getText()).toString());
            currentFavour.setDescription(Objects.requireNonNull(inputEditTexts[1].getText()).toString());
            currentFavour.setCategory(getSelectedCategory());

            if (!currentFavour.category.equals(CategoryManager.CATEGORIES.favourxfavour.name()))
            {
                currentFavour.setAmount(Float.parseFloat(Objects.requireNonNull(inputEditTexts[2].getText()).toString()));
            }
            else
                {
                currentFavour.setAmount(0);
            }

            return currentFavour;
        }
        catch (Exception e)
        {
            LoadingPanel.sendMessage(getResources().getString(R.string.errorfilled));
            return null;
        }
    }

    @SuppressLint("SetTextI18n")
    private void setCount(int i)
    {
        int words = getMaxLength(wordCounter[i]) - Objects.requireNonNull(inputEditTexts[i].getText()).length();
        wordCounter[i].setText(""+words);
    }
    private void enableTextCount(boolean hasFocus, int i)
    {
        if (hasFocus)
        {
            wordCounter[i].setVisibility(View.VISIBLE);
            setCount(i);
        }
        else
        {
            wordCounter[i].setVisibility(View.GONE);
        }
    }

    private int getMaxLength(TextView tv)
    {
        if (tv.getId() == R.id.titleCounter){return TITLE_LENGTH;}
        else if (tv.getId() == R.id.descCounter){return DESCRIPTION_LENGTH;}
        else{return AMOUNT_LENGTH;}
    }


    @SuppressWarnings("deprecation")
    private void selectCategory(ImageView imageView)
    {
        if (imageView.getTag() != null)
        {
            if (imageView.getTag() == CategoryManager.CATEGORIES.favourxfavour.name())
            {
                Log.d("Category selected is ", "favour x favour");
                amount_parent.setVisibility(View.GONE);
            }
            else {amount_parent.setVisibility(View.VISIBLE);}
        }
        else {amount_parent.setVisibility(View.VISIBLE);}

        unselecttAll(imageArrays);
        imageView.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.AfavourColor)));
    }

    @SuppressWarnings("deprecation")
    private void unselecttAll(ImageView[] imageViews)
    {
        for (ImageView i : imageViews)
        {
            i.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.Unselected)));
        }
    }

    //private void setCategory()
    //{ }

    //private void setText(TextView tv, String text)
    //{
    //    tv.setText(text);
    //}
}
