package cat.udl.tidic.a_favour.Views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class CustomActivty extends AppCompatActivity {

    protected String TAG = this.getClass().getSimpleName();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void initView(){

    }


    public void goTo(Class _class){
        Log.d(TAG, "goTo() -> Navigate to " + _class.getSimpleName());
        Intent intent = new Intent(this, _class);
        startActivity(intent);
    }


}
