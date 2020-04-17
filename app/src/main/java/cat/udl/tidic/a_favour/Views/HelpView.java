package cat.udl.tidic.a_favour.Views;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import cat.udl.tidic.a_favour.R;

public class HelpView extends AppCompatActivity
{
    Button faqsButton;
    Button privacityButton;
    Button securtyButton;

    LinearLayout faqsContainer;
    LinearLayout privacityContainer;
    LinearLayout securtyContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        getAllData();
    }

    private void getAllData()
    {
        faqsButton = findViewById(R.id.faqsButton);
        faqsContainer = findViewById(R.id.faqsContainer);
        privacityButton = findViewById(R.id.privacityButton);
        privacityContainer = findViewById(R.id.privacityContainer);
        securtyButton = findViewById(R.id.securityButton);
        securtyContainer = findViewById(R.id.securityContainer);
    }

    public void enableFAQS(View v) { changeVisibility(faqsContainer); }
    public void enablePrivacity(View v) { changeVisibility(privacityContainer); }
    public void enableSecurity(View v) { changeVisibility(securtyContainer); }
    public void goBack(View v){super.onBackPressed();}

    void changeVisibility(LinearLayout layout)
    {
        if (layout.getVisibility() == View.VISIBLE) { layout.setVisibility(View.GONE); }
        else {layout.setVisibility(View.VISIBLE);}
    }
}
