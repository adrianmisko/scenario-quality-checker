package pl.put.poznan.qualitychecker.models;

import java.util.ArrayList;
import java.util.List;

public class Scenario {

    public Header header;
    public List<Step> steps;

    public Scenario() {
        steps = new ArrayList<>();
    }

}
