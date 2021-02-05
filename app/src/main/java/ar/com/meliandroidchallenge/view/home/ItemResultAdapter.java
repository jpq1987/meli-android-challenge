package ar.com.meliandroidchallenge.view.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import ar.com.meliandroidchallenge.R;
import ar.com.meliandroidchallenge.service.model.Item;
import ar.com.meliandroidchallenge.utils.StringUtils;

import static ar.com.meliandroidchallenge.utils.Constants.LOCALE_AR;

public class ItemResultAdapter extends RecyclerView.Adapter<ItemResultAdapter.ItemResultHolder> {
    private List<Item> items = new ArrayList<>();
    OnItemResultRecyclerListener onItemResultRecyclerListener;

    public ItemResultAdapter(OnItemResultRecyclerListener onItemResultRecyclerListener){
        this.onItemResultRecyclerListener = onItemResultRecyclerListener;
    }

    @NonNull
    @Override
    public ItemResultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);
        return new ItemResultHolder(itemView, onItemResultRecyclerListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemResultHolder holder, int position) {
        // Invokes callback when the end of RecyclerView is reached
        if (position == items.size() - 1){
            onItemResultRecyclerListener.onBottomReached(items.size());
        }

        Item item = items.get(position);
        if (item.getThumbnail() != null) {
            String imageUrl = item.getThumbnail().replace("http://", "https://");
            Glide.with(holder.itemView)
                    .load(imageUrl)
                    .fitCenter()
                    .into(holder.thumbnailImageView);
        }
        holder.titleTextView.setText(item.getTitle());
        holder.priceTextView.setText(StringUtils.formatPriceWithLocalizedCurrency(item.getPrice(), item.getCurrencyID(), LOCALE_AR));
        holder.discountPercentTextView.setText(StringUtils.getDiscountLabelBetweenPrices(item.getOriginalPrice(), item.getPrice()));
        holder.conditionTextView.setText(StringUtils.getLabelForCondition(item.getCondition()));
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public Item getResult(int position){
        return this.items.get(position);
    }

    public void setResults(List<Item> results) {
        this.items = results;
        notifyDataSetChanged();
    }

    public void setOnRecyclerViewListener(OnItemResultRecyclerListener onItemResultRecyclerListener){
        this.onItemResultRecyclerListener = onItemResultRecyclerListener;
    }

    class ItemResultHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView thumbnailImageView;
        private TextView titleTextView;
        private TextView priceTextView;
        private TextView discountPercentTextView;
        private TextView conditionTextView;
        private OnItemResultRecyclerListener onItemResultRecyclerListener;

        public ItemResultHolder(@NonNull View itemView, OnItemResultRecyclerListener onItemResultRecyclerListener) {
            super(itemView);
            this.onItemResultRecyclerListener = onItemResultRecyclerListener;

            thumbnailImageView = itemView.findViewById(R.id.imageview_card_item_thumbnail);
            titleTextView = itemView.findViewById(R.id.textview_card_item_title);
            priceTextView = itemView.findViewById(R.id.textview_card_item_price);
            discountPercentTextView = itemView.findViewById(R.id.textview_card_item_discountpercent);
            conditionTextView = itemView.findViewById(R.id.textview_card_item_condition);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemResultRecyclerListener.onItemClicked(getAdapterPosition());
        }
    }

    public interface OnItemResultRecyclerListener {
        void onBottomReached(int position);
        void onItemClicked(int position);
    }
}
