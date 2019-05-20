package com.searoth.api.view;


import android.view.LayoutInflater;

import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.searoth.api.R;
import com.searoth.api.databinding.CoinItemBinding;
import com.searoth.api.model.entity.Coin;
import com.searoth.api.viewModel.CoinViewModel;

import java.util.ArrayList;
import java.util.List;

public class CoinAdapter extends RecyclerView.Adapter<CoinAdapter.BindingHolder> {
    private List<Coin> coins;

    public CoinAdapter() {
        coins = new ArrayList<>();
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CoinItemBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.coin_item, parent, false);
        return new BindingHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        CoinViewModel movieViewModel = new CoinViewModel(coins.get(position));
        holder.itemBinding.setViewModel(movieViewModel);
    }

    @Override
    public int getItemCount() {
        return coins.size();
    }

    public void addItem(Coin movie) {
        coins.add(movie);
        notifyItemInserted(coins.size() - 1);
    }

    public void clearItems() {
        coins.clear();
        notifyDataSetChanged();
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {
        private CoinItemBinding itemBinding;

        public BindingHolder(CoinItemBinding itemBinding) {
            super(itemBinding.cardView);
            this.itemBinding = itemBinding;
        }
    }
}
