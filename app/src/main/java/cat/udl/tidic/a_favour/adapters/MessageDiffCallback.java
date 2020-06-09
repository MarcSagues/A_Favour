package cat.udl.tidic.a_favour.adapters;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import cat.udl.tidic.a_favour.models.Favour;
import cat.udl.tidic.a_favour.models.Message;

public class MessageDiffCallback extends DiffUtil.ItemCallback<Message> {

    @Override
    public boolean areItemsTheSame(@NonNull Message oldItem, @NonNull Message newItem) {
        return oldItem.getUser().getId() == newItem.getUser().getId();
    }

    @SuppressLint("DiffUtilEquals")
    @Override
    public boolean areContentsTheSame(@NonNull Message oldItem, @NonNull Message newItem) {
        return oldItem.equals(newItem);
    }
}


