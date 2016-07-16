package com.randomappsinc.pokemonlocations_pokemongo.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.randomappsinc.pokemonlocations_pokemongo.Activities.MainActivity;
import com.randomappsinc.pokemonlocations_pokemongo.Adapters.PokemonACAdapter;
import com.randomappsinc.pokemonlocations_pokemongo.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.nlopez.smartlocation.SmartLocation;

/**
 * Created by alexanderchiou on 7/14/16.
 */
public class SearchFragment extends Fragment {
    public static final int LOCATION_REQUEST_CODE = 1;

    @Bind(R.id.search_term) AutoCompleteTextView searchInput;

    private MaterialDialog progressDialog;
    private boolean locationFetched;
    private Handler locationChecker;
    private Runnable locationCheckTask;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.search, container, false);
        ButterKnife.bind(this, rootView);

        searchInput.setAdapter(new PokemonACAdapter(getActivity(), R.layout.pokemon_ac_item, new ArrayList<String>()));

        locationChecker = new Handler();
        locationCheckTask = new Runnable() {
            @Override
            public void run() {
                SmartLocation.with(getActivity()).location().stop();
                if (!locationFetched) {
                    progressDialog.dismiss();
                    showSnackbar(getString(R.string.auto_location_fail));
                }
            }
        };
        progressDialog = new MaterialDialog.Builder(getActivity())
                .progress(true, 0)
                .cancelable(false)
                .build();

        return rootView;
    }

    @OnClick(R.id.clear_search)
    public void clearSearch() {
        searchInput.setText("");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case LOCATION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void showLocationServicesDialog() {
        new MaterialDialog.Builder(getActivity())
                .content(R.string.location_services_needed)
                .negativeText(android.R.string.cancel)
                .positiveText(android.R.string.yes)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .show();
    }

    private void showSnackbar(String message) {
        ((MainActivity) getActivity()).showSnackbar(message);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}