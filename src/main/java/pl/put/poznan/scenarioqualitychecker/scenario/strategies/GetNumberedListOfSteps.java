package pl.put.poznan.scenarioqualitychecker.scenario.strategies;

import pl.put.poznan.scenarioqualitychecker.scenario.ResponseGenerationStrategy;
import pl.put.poznan.scenarioqualitychecker.scenario.model.Scenario;
import pl.put.poznan.scenarioqualitychecker.scenario.visitors.NumberedListOfSteps;

public class GetNumberedListOfSteps implements ResponseGenerationStrategy {

    @Override
    public Object getResponse(Scenario scenario) {
        NumberedListOfSteps nls = new NumberedListOfSteps();
        scenario.accept(nls);
        return  nls.getNumberedList();
    }

}
