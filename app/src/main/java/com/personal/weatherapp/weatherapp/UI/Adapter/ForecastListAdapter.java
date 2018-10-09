package com.personal.weatherapp.weatherapp.UI.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.personal.weatherapp.weatherapp.Networking.Model.Forecast;
import com.personal.weatherapp.weatherapp.R;
import com.personal.weatherapp.weatherapp.Utils.Constants;
import com.personal.weatherapp.weatherapp.Utils.DateUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class ForecastListAdapter extends RecyclerView.Adapter<ForecastListAdapter.ForecastViewHolder> {
    private ArrayList<Forecast> mForecast = new ArrayList<>();
    private Context mContext;

    public ForecastListAdapter(Context context, List<Forecast> forecasts) {
        mContext = context;
        mForecast.addAll(forecasts);
    }

    @Override
    public ForecastListAdapter.ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_weather, parent, false);
        ForecastViewHolder viewHolder = new ForecastViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ForecastListAdapter.ForecastViewHolder holder, int position) {
        holder.bindRestaurant(mForecast.get(position));
    }

    @Override
    public int getItemCount() {
        return mForecast.size();
    }

    public class ForecastViewHolder extends RecyclerView.ViewHolder {

        ImageView mRestaurantImageView;

        TextView txtDate;
        TextView txtDesc;
        TextView txtTemp;
        ImageView imgWeather;


        private Context mContext;

        public ForecastViewHolder(View itemView) {
            super(itemView);

            mContext = itemView.getContext();

            txtDate = (TextView) itemView.findViewById(R.id.txtDate);
            txtDesc = (TextView) itemView.findViewById(R.id.txtDesc);
            txtTemp = (TextView) itemView.findViewById(R.id.txtTemp);
            imgWeather = (ImageView) itemView.findViewById(R.id.imgWeather);
        }

        public void bindRestaurant(Forecast forecast) {


            txtDate.setText(DateUtils.stringBadFormedDateToGoodFormat(forecast.getDtTxt()));
            txtDesc.setText(forecast.getWeather().get(0).getDescription());
            txtTemp.setText("Min: "+forecast.getMain().getTempMin() + "º \nMax: " + forecast.getMain().getTempMax() + "º");
            //GlideApp.with(this).load(Constants.getOpenWeatherIconUrl() + forecast.getWeather().get(0).getIcon()+Constants.getIconExt()).into(imgWeather);

            Glide.with(mContext)
                    .load(Constants.getOpenWeatherIconUrl() + forecast.getWeather().get(0).getIcon() + Constants.getIconExt())

                    .into(imgWeather);


        }
    }
}