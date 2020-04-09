package cat.udl.tidic.a_favour.Views;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import cat.udl.tidic.a_favour.R;

public class UploadFavour extends AppCompatActivity
{
    public static int TITLE_LENGTH = 50;
    public static int DESCRIPTION_LENGTH = 600;
    public static int AMOUNT_LENGTH = 10;
    public static int INPUTS = 3;


    public int total_categories=4;

    Button upload;
    ImageView[] imageArrays;

    TextView[] wordCounter;
    TextInputEditText[] inputEditTexts;


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
        inputEditTexts = new TextInputEditText[INPUTS];
        inputEditTexts[0] = findViewById(R.id.title);
        inputEditTexts[1] = findViewById(R.id.description);
        inputEditTexts[2] = findViewById(R.id.amount);

        imageArrays = new ImageView[total_categories];
        imageArrays[0] = findViewById(R.id.daytoday);
        imageArrays[1] = findViewById(R.id.computing);
        imageArrays[2] = findViewById(R.id.reparation);
        imageArrays[3] = findViewById(R.id.others);

        upload = findViewById(R.id.upload);

        wordCounter = new TextView[INPUTS];
        wordCounter[0] = findViewById(R.id.titleCounter);
        wordCounter[1] = findViewById(R.id.descCounter);
        wordCounter[2] = findViewById(R.id.amountCounter);

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
            inputEditTexts[i].setOnFocusChangeListener(new View.OnFocusChangeListener()
            {
                @Override
                public void onFocusChange(View v, boolean hasFocus)
                {
                    enableTextCount(hasFocus, finalI);
                }
            });

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
    }

    private void setCount(int i)
    {
        int words = getMaxLength(wordCounter[i]) - inputEditTexts[i].getText().length();
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
