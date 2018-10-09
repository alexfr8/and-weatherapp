package com.personal.weatherapp.weatherapp.UI;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.android.volley.VolleyError;
import com.personal.weatherapp.weatherapp.Networking.Model.SevenDaysForecast;
import com.personal.weatherapp.weatherapp.Networking.OpenWeatherManager;
import com.personal.weatherapp.weatherapp.R;
import com.personal.weatherapp.weatherapp.UI.Adapter.ForecastListAdapter;

public class HomeActivity extends AppCompatActivity implements  HomeView{

    HomePresenter presenter ;


    private RecyclerView mRecyclerview;
    private ProgressBar mProgressBar;
    private ForecastListAdapter mAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.GONE);
        presenter = new  HomePresenter(this, this);


    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.setupUI();

        presenter.getForecast();

    }

    @Override
    public void setupUI() {

        mRecyclerview = (RecyclerView) findViewById(R.id.forecastRecyclerView);
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
        mRecyclerview.setVisibility(View.GONE);
    }

    @Override
    public void hidLoading() {
        mProgressBar.setVisibility(View.GONE);
        mRecyclerview.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(String msg) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle(R.string.alert_dialog_title)
                .setMessage(msg)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO: IMPLEMENT A RETRY.
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public void showSuccess(SevenDaysForecast forecast) {

        mAdapter = new ForecastListAdapter(this, forecast.getFilterdList());
        mRecyclerview.setAdapter(mAdapter);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(HomeActivity.this);
        mRecyclerview.setLayoutManager(layoutManager);
        mRecyclerview.setHasFixedSize(true);
    }
}
