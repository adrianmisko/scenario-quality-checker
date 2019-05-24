package pl.put.poznan.scenarioqualitychecker.scenario;

import pl.put.poznan.scenarioqualitychecker.scenario.model.Scenario;

public interface ResponseGenerationStrategy {

    public Object getResponse(Scenario scenario);

}
