package pl.put.poznan.scenarioqualitychecker.scenario.strategies;

import pl.put.poznan.scenarioqualitychecker.scenario.ResponseGenerationStrategy;
import pl.put.poznan.scenarioqualitychecker.scenario.model.Scenario;
import pl.put.poznan.scenarioqualitychecker.scenario.visitors.SubscenarioCounter;

public class GetNumberOfSubscenarios implements ResponseGenerationStrategy {

    @Override
    public Object getResponse(Scenario scenario) {
        SubscenarioCounter subc = new SubscenarioCounter();
        scenario.accept(subc);
        return subc.getNumOfSubscenario();
    }

}
