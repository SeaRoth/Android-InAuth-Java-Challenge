package com.searoth.api.viewModel;

import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.searoth.api.model.entity.Coin;

public class CoinViewModel extends BaseObservable {
    private Coin coin;

    public CoinViewModel(Coin coin) {
        this.coin = coin;
    }

    public String getCoverUrl() {
        return coin.getCoinInfo().getImageUrl();
    }

    public String getTitle() {
        return coin.getCoinInfo().getName();
    }

    public String getImageUrl() {
        return "https://www.cryptocompare.com"+coin.getCoinInfo().getImageUrl();
    }

    @BindingAdapter({"app:imageUrl"})
    public static void loadImage(ImageView imageView,String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .into(imageView);

    }
}
