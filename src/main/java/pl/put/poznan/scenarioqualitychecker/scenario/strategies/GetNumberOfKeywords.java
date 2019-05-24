package pl.put.poznan.scenarioqualitychecker.scenario.strategies;

import pl.put.poznan.scenarioqualitychecker.scenario.ResponseGenerationStrategy;
import pl.put.poznan.scenarioqualitychecker.scenario.model.Scenario;
import pl.put.poznan.scenarioqualitychecker.scenario.visitors.KeywordsCounter;

public class GetNumberOfKeywords implements ResponseGenerationStrategy {

    @Override
    public Object getResponse(Scenario scenario) {
        KeywordsCounter kc = new KeywordsCounter();
        scenario.accept(kc);
        return kc.getNumStepsWithKeywords();
    }

}
