/*
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
import cat.udl.tidic.a_favour.R;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.bouncycastle.jcajce.provider.symmetric.DES;

import java.util.Objects;

import cat.udl.tidic.a_favour.MainPageClasses.DataModel;
import cat.udl.tidic.a_favour.models.UploadOpinionModel;
import cat.udl.tidic.a_favour.preferences.PreferencesProvider;

import static cat.udl.tidic.a_favour.Views.UploadFavour.INPUTS;

public class UploadOpinion extends AppCompatActivity {

    public static int DESCRIPTION_LENGTH = 600;
    public static int MARK= 10;
    boolean upload_bool;

    Button upload;
    TextView uploadtDescription;
    TextView[] wordCounter;
    TextInputEditText[] inputEditTexts;


    DataModel.Opinion currentOpinion;
    UploadOpinionModel uploadOpinionModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anunci);
        getComponents();
        getBundle();
        setListeners();
        uploadOpinionModel = new UploadOpinionModel(this);
    }

    private void Model(UploadOpinion uploadOpinion) {
    }

    private void getBundle() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            upload_bool = b.getBoolean("upload");
            currentOpinion = (DataModel.Opinion) getIntent().getSerializableExtra("opinion");
        }
        Log.d("Open upload page", String.valueOf(upload_bool));
        prepareUpload(upload_bool);
        //if (!upload_bool)
        //{
        //DataModel d = (DataModel) b.getParcelable("data");
        //}

    }

    @SuppressLint("ResourceType")
    private void prepareUpload(boolean upload_) {
        if (!upload_) {
            uploadtDescription.setText(getResources().getString(R.string.editOpinion));
            upload.setText(getResources().getString(R.string.edit_));
            setAllData();
        } else {
            uploadtDescription.setText(getResources().getString(R.string.uploadOpinion));
            upload.setText(getResources().getString(R.string.upload));

        }
    }

    @SuppressLint("SetTextI18n")
    private void setAllData() {

       inputEditTexts[0].setText(currentOpinion.getDescription());
       inputEditTexts[1].setText(currentOpinion.getMark());

    }

    @Override
    public void onBackPressed() {
        if (upload_bool) {
            Intent i = new Intent(this, MainPage.class);
            startActivityForResult(i, 1);
            finish();
        } else {
            Intent i = new Intent(this, AnunciView.class);
            Bundle b = new Bundle();
            b.putBoolean("myopinion", true);
            i.putExtras(b);
            i.putExtra("opinion", currentOpinion);
            startActivityForResult(i, 1);
            finish();
        }
    }

    public void onSucces() {
        Intent i = new Intent(this, AnunciView.class);
        Bundle b = new Bundle();
        b.putBoolean("myopinion", true);
        i.putExtras(b);
        i.putExtra("opinion", getCurrentOpinionData());
        startActivityForResult(i, 1);
        finish();
    }

    private void getComponents() {
        inputEditTexts = new TextInputEditText[INPUTS];
        inputEditTexts[1] = findViewById(R.id.description);
        inputEditTexts[2] = findViewById(R.id.mark);



        upload = findViewById(R.id.upload);

        wordCounter = new TextView[INPUTS];
        wordCounter[1] = findViewById(R.id.descCounter);
        wordCounter[2] = findViewById(R.id.markCounter);


    }

    private void setListeners() {
        for (ImageView i : imageArrays) {
            i.setOnClickListener(v -> selectCategory(i));
        }

        for (int i = 0; i < inputEditTexts.length; i++) {
            int finalI = i;
            inputEditTexts[i].setOnFocusChangeListener((v, hasFocus) -> enableTextCount(hasFocus, finalI));

            inputEditTexts[i].addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    setCount(finalI);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }

        UploadOpinion u = this;
        upload.setOnClickListener(v -> {
            currentOpinion = getCurrentOpinionData();
            if (currentOpinion != null) {
                if (!upload_bool) {
                    uploadOpinionModel.editOpinion(getCurrentOpinionData(), u);
                } else {
                    uploadOpinionModel.postOpinion(getCurrentOpinionData(), u);
                }
            }
        });
    }

    private void Data() {
    }


    public DataModel.Opinion getCurrentOpinionData() {
        try {
            if (currentOpinion == null) {
                SharedPreferences mPreferences = PreferencesProvider.providePreferences();
                currentOpinion = new DataModel.Opinion("", "", 0, "", mPreferences.getInt("id", 0), "", -1);
            }
            currentOpinion.setTaskDescription(Objects.requireNonNull(inputEditTexts[1].getText()).toString());
            currentOpinion.setMark(Objects.requireNonNull(inputEditTexts[1].getText()).toString());


            return currentOpinion;
        } catch (Exception e) {
            LoadingPanel.sendMessage(getResources().getString(R.string.errorfilled));
            return null;
        }
    }

    @SuppressLint("SetTextI18n")
    private void setCount(int i) {
        int words = getMaxLength(wordCounter[i]) - Objects.requireNonNull(inputEditTexts[i].getText()).length();
        wordCounter[i].setText("" + words);
    }

    private void enableTextCount(boolean hasFocus, int i) {
        if (hasFocus) {
            wordCounter[i].setVisibility(View.VISIBLE);
            setCount(i);
        } else {
            wordCounter[i].setVisibility(View.GONE);
        }
    }

    private int getMaxLength(TextView tv) {
        if (tv.getId() == R.id.titleCounter) {
            return DESCRIPTION_LENGTH;
        }
    }




    @SuppressWarnings("deprecation")
    private void unselecttAll(ImageView[] imageViews) {
        for (ImageView i : imageViews) {
            i.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.Unselected)));
        }
    }
}
 */