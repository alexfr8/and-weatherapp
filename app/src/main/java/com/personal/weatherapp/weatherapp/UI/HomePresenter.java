package com.personal.weatherapp.weatherapp.UI;

import android.content.Context;

import com.android.volley.VolleyError;
import com.personal.weatherapp.weatherapp.Networking.Model.SevenDaysForecast;
import com.personal.weatherapp.weatherapp.Networking.OpenWeatherManager;

public class HomePresenter {



    private Context context;
    private HomeView view;

    public HomePresenter(HomeView view, Context context) {

        this.view = view;
        this.context = context;
    }

    public void setupUI() {
        view.setupUI();
    }


    public void getForecast() {
        view.showLoading();
        OpenWeatherManager.getInstance(context).getNextDaysForecast("Malaga", new OpenWeatherManager.GetForecastCallback() {
            @Override
            public void onSuccess(SevenDaysForecast nextDaysForecast) {
                view.showSuccess(nextDaysForecast);
                view.hidLoading();
            }

            @Override
            public void onFailed(VolleyError error) {
                view.showError(error.getMessage());
                view.hidLoading();
            }
        });
    }

}
