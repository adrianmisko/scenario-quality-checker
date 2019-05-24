package pl.put.poznan.scenarioqualitychecker.scenario.strategies;

import pl.put.poznan.scenarioqualitychecker.scenario.ResponseGenerationStrategy;
import pl.put.poznan.scenarioqualitychecker.scenario.model.Scenario;
import pl.put.poznan.scenarioqualitychecker.scenario.visitors.StepCounter;

public class GetNumberOfSteps implements ResponseGenerationStrategy {

    @Override
    public Object getResponse(Scenario scenario) {
        StepCounter sc = new StepCounter();
        scenario.accept(sc);
        return sc.getNumOfSteps();
    }

}
