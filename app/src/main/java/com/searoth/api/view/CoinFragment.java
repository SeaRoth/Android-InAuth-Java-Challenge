package com.searoth.api.view;

import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.searoth.api.R;
import com.searoth.api.databinding.CoinFragmentBinding;
import com.searoth.api.viewModel.MainViewModel;

public class CoinFragment extends Fragment implements CompletedListener, SwipeRefreshLayout.OnRefreshListener{

    private static String TAG = CoinFragment.class.getSimpleName();
    private MainViewModel viewModel;
    private CoinFragmentBinding coinFragmentBinding;
    private CoinAdapter coinAdapter;
    LocationManager locationManager;
    private static Integer REQUEST_PERMISSIONS_LOCATION = 121;

    public static CoinFragment getInstance() {
        return new CoinFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.coin_fragment, container, false);
        coinFragmentBinding = CoinFragmentBinding.bind(contentView);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        initData();
        return contentView;
    }

    private void initData() {
        coinAdapter = new CoinAdapter();
        coinFragmentBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        coinFragmentBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        coinFragmentBinding.recyclerView.setAdapter(coinAdapter);
        coinFragmentBinding.swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        coinFragmentBinding.swipeRefreshLayout.setOnRefreshListener(this);
        viewModel = new MainViewModel(coinAdapter,this, getActivity());
        coinFragmentBinding.setViewModel(viewModel);
    }

    private void requestLocationUpdates(){
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_PERMISSIONS_LOCATION
            );
        } else {
            Location loc  = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            viewModel.setSanFranciscoText(loc);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSIONS_LOCATION) {
            if (permissions[0].equals(Manifest.permission.ACCESS_FINE_LOCATION) && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                requestLocationUpdates();
            }
        }
    }

    @Override
    public void onRefresh() {
        coinAdapter.clearItems();
        viewModel.refreshData();
    }

    @Override
    public void onAPIComplete() {
        if (coinFragmentBinding.swipeRefreshLayout.isRefreshing()) {
            coinFragmentBinding.swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void makeGPSRequest() {
        requestLocationUpdates();
    }
}





