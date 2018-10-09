package com.personal.weatherapp.weatherapp.Networking.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class City {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("name")
@Expose
private String name;
@SerializedName("coord")
@Expose
private Coord coord;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public Coord getCoord() {
return coord;
}

public void setCoord(Coord coord) {
this.coord = coord;
}

}