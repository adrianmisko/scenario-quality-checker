package pl.put.poznan.qualitychecker.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class Scenario {

    @JsonIgnore
    public int id;
    public Header header;
    public List<Step> steps;

    public Scenario() {
        steps = new ArrayList<>();
    }

}
