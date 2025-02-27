package com.flauschcode.broccoli.shopping;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.flauschcode.broccoli.databinding.ShoppingListItemBinding;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ShoppingListAdapter extends ListAdapter<ShoppingListItem, ShoppingListAdapter.ShoppingListViewHolder> {

    private final OnCheckedChangeListener onCheckedChangeListener;

    public interface OnCheckedChangeListener {
        void onCheckedChanged(ShoppingListItem item, boolean isChecked);
    }

    public ShoppingListAdapter(OnCheckedChangeListener onCheckedChangeListener) {
        super(new ShoppingListItemDiffCallback());
        this.onCheckedChangeListener = onCheckedChangeListener;
    }

    @NonNull
    @Override
    public ShoppingListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ShoppingListItemBinding binding = ShoppingListItemBinding.inflate(layoutInflater, parent, false);
        return new ShoppingListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingListViewHolder holder, int position) {
        ShoppingListItem currentItem = getItem(position);
        holder.bind(currentItem, onCheckedChangeListener);
    }

    @Override
    public void submitList(List<ShoppingListItem> list) {
        if (list != null) {
            List<ShoppingListItem> sortedList = sortList(list);
            super.submitList(sortedList);
        } else {
            super.submitList(null);
        }
    }

    private List<ShoppingListItem> sortList(List<ShoppingListItem> list) {
        List<ShoppingListItem> newList = new java.util.ArrayList<>(list);
        Collections.sort(newList, new Comparator<ShoppingListItem>() {
            @Override
            public int compare(ShoppingListItem item1, ShoppingListItem item2) {
                // Unchecked items come before checked items
                if (item1.isChecked() && !item2.isChecked()) {
                    return 1;
                } else if (!item1.isChecked() && item2.isChecked()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        return newList;
    }

    public static class ShoppingListViewHolder extends RecyclerView.ViewHolder {
        private final ShoppingListItemBinding binding;

        public ShoppingListViewHolder(ShoppingListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ShoppingListItem item, OnCheckedChangeListener onCheckedChangeListener) {
            binding.setItem(item);
            binding.setListener(onCheckedChangeListener);
            binding.executePendingBindings();

            // Apply strike-through if checked
            if (item.isChecked()) {
                binding.textViewItemName.setPaintFlags(binding.textViewItemName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                binding.textViewItemName.setPaintFlags(binding.textViewItemName.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            }
        }
    }

    static class ShoppingListItemDiffCallback extends DiffUtil.ItemCallback<ShoppingListItem> {
        @Override
        public boolean areItemsTheSame(@NonNull ShoppingListItem oldItem, @NonNull ShoppingListItem newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull ShoppingListItem oldItem, @NonNull ShoppingListItem newItem) {
            return oldItem.equals(newItem);
        }
    }
}