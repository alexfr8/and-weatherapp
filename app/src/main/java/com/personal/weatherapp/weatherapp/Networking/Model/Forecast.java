package com.personal.weatherapp.weatherapp.Networking.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Forecast {

@SerializedName("dt")
@Expose
private Integer dt;
@SerializedName("main")
@Expose
private Main main;
@SerializedName("weather")
@Expose
private List<Weather> weather = null;
@SerializedName("clouds")
@Expose
private Clouds clouds;
@SerializedName("wind")
@Expose
private Wind wind;
@SerializedName("dt_txt")
@Expose
private String dtTxt;

public Integer getDt() {
return dt;
}

public void setDt(Integer dt) {
this.dt = dt;
}

public Main getMain() {
return main;
}

public void setMain(Main main) {
this.main = main;
}

public List<Weather> getWeather() {
return weather;
}

public void setWeather(List<Weather> weather) {
this.weather = weather;
}

public Clouds getClouds() {
return clouds;
}

public void setClouds(Clouds clouds) {
this.clouds = clouds;
}

public Wind getWind() {
return wind;
}

public void setWind(Wind wind) {
this.wind = wind;
}

public String getDtTxt() {
return dtTxt;
}

public void setDtTxt(String dtTxt) {
this.dtTxt = dtTxt;
}

}