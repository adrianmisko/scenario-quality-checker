package pl.put.poznan.scenarioqualitychecker.scenario.strategies;

import pl.put.poznan.scenarioqualitychecker.scenario.ResponseGenerationStrategy;
import pl.put.poznan.scenarioqualitychecker.scenario.model.Scenario;

public class OperationNotFound implements ResponseGenerationStrategy {

    @Override
    public Object getResponse(Scenario scenario) {
        return "No such option";
    }

}
