package pl.put.poznan.qualitychecker.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Header {

    public String title;
    public List<String> actors;
    @JsonProperty("system actors")
    public List<String> systemActors;

}
