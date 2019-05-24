package pl.put.poznan.scenarioqualitychecker.scenario.strategies;

import pl.put.poznan.scenarioqualitychecker.scenario.ResponseGenerationStrategy;
import pl.put.poznan.scenarioqualitychecker.scenario.model.Scenario;
import pl.put.poznan.scenarioqualitychecker.scenario.visitors.ScenariosUpToLevelGetter;

public class GetScenariosUpToLevel implements ResponseGenerationStrategy {
    @Override
    public Object getResponse(Scenario scenario) {
        ScenariosUpToLevelGetter scenariosUpToLevelGetter = new ScenariosUpToLevelGetter(2);
        scenario.accept(scenariosUpToLevelGetter);
        return scenariosUpToLevelGetter.getScenariosUpToLevel();
    }
}
