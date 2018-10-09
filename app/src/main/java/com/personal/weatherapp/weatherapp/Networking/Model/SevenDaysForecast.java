package com.personal.weatherapp.weatherapp.Networking.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.personal.weatherapp.weatherapp.Utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

public class SevenDaysForecast {

@SerializedName("cod")
@Expose
private String cod;
@SerializedName("message")
@Expose
private Double message;
@SerializedName("cnt")
@Expose
private Integer cnt;
@SerializedName("list")
@Expose
private java.util.List<Forecast> list = null;
@SerializedName("city")
@Expose
private City city;

public String getCod() {
return cod;
}

public void setCod(String cod) {
this.cod = cod;
}

public Double getMessage() {
return message;
}

public void setMessage(Double message) {
this.message = message;
}

public Integer getCnt() {
return cnt;
}

public void setCnt(Integer cnt) {
this.cnt = cnt;
}

public java.util.List<Forecast> getList() {
return list;
}

public void setList(java.util.List<Forecast> list) {
this.list = list;
}

public City getCity() {
return city;
}

public void setCity(City city) {
this.city = city;
}

public List<Forecast> getFilterdList() {
    List<Forecast> cleanList= new ArrayList<Forecast>();
    Boolean found = false;

    for (Forecast forecast : getList()) {

        for (Forecast cleanForecast : cleanList) {
            if (DateUtils.stringBadFormedDateToGoodFormat(cleanForecast.getDtTxt()).equals(DateUtils.stringBadFormedDateToGoodFormat(forecast.getDtTxt()))){
                found = true;
            }
        }
        if (!found) {
            cleanList.add(forecast);
        }
        found = false;
    }

    return cleanList;
}

}