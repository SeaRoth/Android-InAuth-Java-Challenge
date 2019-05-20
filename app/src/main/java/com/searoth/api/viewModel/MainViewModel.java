package com.searoth.api.viewModel;


import android.content.Context;
import android.location.Location;
import android.view.View;
import androidx.databinding.ObservableField;
import com.searoth.api.model.data.RetrofitHelper;
import com.searoth.api.model.entity.Coin;
import com.searoth.api.model.entity.StorageInformation;
import com.searoth.api.view.CoinAdapter;
import com.searoth.api.view.CompletedListener;
import rx.Subscriber;

public class MainViewModel {
    private Context ctx;
    public ObservableField<Integer> contentViewVisibility;
    public ObservableField<Integer> progressBarVisibility;
    public ObservableField<Integer> errorInfoLayoutVisibility;
    public ObservableField<String> exception;
    private Subscriber<Coin> subscriberCoin;
    private CoinAdapter coinAdapter;
    private CompletedListener completedListener;

    public MainViewModel(CoinAdapter coinAdapter, CompletedListener completedListener, Context context) {
        this.coinAdapter = coinAdapter;
        this.completedListener = completedListener;
        this.ctx = context;
        initData();
    }

    public void setSanFranciscoText(Location loc){
        double sfLat = 37.773972;
        double sfLon = -122.431297;
        float[] distanceResults = new float[1];
        Location.distanceBetween(loc.getLatitude(), loc.getLongitude(), sfLat, sfLon, distanceResults);

        Number miles = distanceResults[0] * .00062137;
        exception.set("You are approximately " + miles.intValue() + " miles from the center of San Francisco (" + sfLat + ", " + sfLon + ")");
    }

    public void clickedAPI() {
        getCoins();
    }

    public void clickedGPS() {
        contentViewVisibility.set(View.GONE);
        completedListener.makeGPSRequest();
    }

    public void clickedApps() {
        contentViewVisibility.set(View.GONE);
        progressBarVisibility.set(View.VISIBLE);
        new Thread(() -> {
            StorageInformation storageInformation = new StorageInformation(ctx);
            String s = storageInformation.getpackageSize();
            exception.set(s);
            progressBarVisibility.set(View.GONE);
        }).start();
    }

    private void getCoins() {
        progressBarVisibility.set(View.VISIBLE);
        subscriberCoin = new Subscriber<Coin>() {
            @Override
            public void onCompleted() {
                hideAll();
                contentViewVisibility.set(View.VISIBLE);
                completedListener.onAPIComplete();
            }

            @Override
            public void onError(Throwable e) {
                hideAll();
                errorInfoLayoutVisibility.set(View.VISIBLE);
                exception.set(e.getMessage());
            }

            @Override
            public void onNext(Coin movie) {
                coinAdapter.addItem(movie);
            }
        };
        RetrofitHelper.getInstance().getCoins(subscriberCoin);
    }

    public void refreshData() {
        getCoins();
    }

    private void initData() {
        contentViewVisibility = new ObservableField<>();
        progressBarVisibility = new ObservableField<>();
        errorInfoLayoutVisibility = new ObservableField<>();
        exception = new ObservableField<>();
        contentViewVisibility.set(View.GONE);
        errorInfoLayoutVisibility.set(View.GONE);
        progressBarVisibility.set(View.GONE);
    }

    private void hideAll(){
        contentViewVisibility.set(View.GONE);
        errorInfoLayoutVisibility.set(View.GONE);
        progressBarVisibility.set(View.GONE);
    }
}
