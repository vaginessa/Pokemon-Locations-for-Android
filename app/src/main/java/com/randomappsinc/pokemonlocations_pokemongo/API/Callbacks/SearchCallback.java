package com.randomappsinc.pokemonlocations_pokemongo.API.Callbacks;

import com.randomappsinc.pokemonlocations_pokemongo.API.ApiConstants;
import com.randomappsinc.pokemonlocations_pokemongo.API.Models.SearchResults;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alexanderchiou on 7/19/16.
 */
public class SearchCallback implements Callback<SearchResults> {
    public static final String SEARCH_FAIL = "searchFail";

    @Override
    public void onResponse(Call<SearchResults> call, Response<SearchResults> response) {
        if (response.code() == ApiConstants.HTTP_STATUS_OK) {
            EventBus.getDefault().post(response.body().getLocations());
        } else {
            EventBus.getDefault().post(SEARCH_FAIL);
        }
    }

    @Override
    public void onFailure(Call<SearchResults> call, Throwable t) {
        EventBus.getDefault().post(SEARCH_FAIL);
    }
}
