package com.personal.weatherapp.weatherapp.Networking;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.personal.weatherapp.weatherapp.Networking.Model.SevenDaysForecast;
import com.personal.weatherapp.weatherapp.Networking.Request.GsonRequest;
import com.personal.weatherapp.weatherapp.Utils.Constants;


import org.json.JSONException;
import org.json.JSONObject;


import java.util.HashMap;

public class OpenWeatherManager {

    private static OpenWeatherManager mInstance;
    private RequestQueue mRequestQueue;
    private static Gson gson = new Gson();

    public interface GetForecastCallback
    {
        void onSuccess(SevenDaysForecast nextDaysForecast);
        void onFailed(VolleyError error);
    }

    private OpenWeatherManager(Context context) {
        mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    private String getBase() {
        return "https://api.openweathermap.org";
    }

    public static synchronized OpenWeatherManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new OpenWeatherManager(context);
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setDateFormat("YYYY/MM/DD");
            gson = gsonBuilder.create();
        }

        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }


    public void getNextDaysForecast(String city , final GetForecastCallback callback) {

        String url = this.getBase() + "/data/2.5/forecast";
        url = url + "?";
        url = url + "q=" + city;
        url = url + "&mode=json";
        url = url + "&units=metric";
        url = url + "&cnt=50";
        url = url + "&appid=" + Constants.getOpenWeatherKey();

        GsonRequest<SevenDaysForecast> request = new GsonRequest<SevenDaysForecast>(url, SevenDaysForecast.class, new HashMap<String, String>(), new Response.Listener<SevenDaysForecast>() {
            @Override
            public void onResponse(SevenDaysForecast response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailed(error);
            }
        });


        mRequestQueue.add(request);
    }

    private Response.Listener<SevenDaysForecast> createMyReqSuccessListener() {
        return new Response.Listener<SevenDaysForecast>() {
            @Override
            public void onResponse(SevenDaysForecast response) {
                // Do whatever you want to do with response;
                // Like response.tags.getListing_count(); etc. etc.
            }
        };
    }

    private Response.ErrorListener createMyReqErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Do whatever you want to do with error.getMessage();
            }
        };
    }
}
