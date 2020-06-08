package cat.udl.tidic.a_favour.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import cat.udl.tidic.a_favour.models.Favour;

public class FavourDiffCallback extends DiffUtil.ItemCallback<Favour> {

    @Override
    public boolean areItemsTheSame(@NonNull Favour oldItem, @NonNull Favour newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Favour oldItem, @NonNull Favour newItem) {
        return oldItem.equals(newItem);
    }
}


