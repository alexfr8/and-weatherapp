package com.personal.weatherapp.weatherapp.UI;

import com.personal.weatherapp.weatherapp.Networking.Model.SevenDaysForecast;

public interface HomeView {

    void setupUI();
    void showLoading();
    void hidLoading();
    void showError(String msg);
    void showSuccess(SevenDaysForecast forecast);

}
