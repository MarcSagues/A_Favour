package cat.udl.tidic.a_favour.adapters;

import android.location.Location;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.models.Favour;
import cat.udl.tidic.a_favour.models.Message;


public class MessageCommonHolder
{

    private final String TAG = "MessageCommonHolder";
    private TextView messageText;


    public MessageCommonHolder(@NonNull View itemView)
    {
        messageText = itemView.findViewById(R.id.tv_text);
    }


    public void bindHolder(Message m)
    {
       messageText.setText(m.getText());
    }

}
