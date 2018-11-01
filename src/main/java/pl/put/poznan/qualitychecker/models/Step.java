package pl.put.poznan.qualitychecker.models;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Step {

    public String keyword;
    public String actor;
    public String systemActor;
    public String text;
    public Scenario scenario;

    public Step() {}

}
