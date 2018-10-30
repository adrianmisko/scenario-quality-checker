package pl.put.poznan.qualitychecker.logic;

import pl.put.poznan.qualitychecker.models.Scenario;

public class ScenarioQualityChecker {

    //String array for storing params

    //map that *maps* parameters (get methods parameters) to functions
    //private final map...;


    //copy of Scenario object

    Scenario s;
    String[] params = {"steps", "actors"};

    public ScenarioQualityChecker(String[] params){
    //    this.params = params;
    }

    public String apply (String function) {
        String json = "";
        // foreach param in params:
            // apply function to Scenario object
            // add result to json
        return "{\"id\" : 1 }";
    }

}
