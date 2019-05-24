package pl.put.poznan.scenarioqualitychecker.scenario.strategies;

import pl.put.poznan.scenarioqualitychecker.scenario.ResponseGenerationStrategy;
import pl.put.poznan.scenarioqualitychecker.scenario.model.Scenario;
import pl.put.poznan.scenarioqualitychecker.scenario.visitors.ActorlessStepsGetter;

public class GetNumberOfStepsWithoutActors implements ResponseGenerationStrategy {

    @Override
    public Object getResponse(Scenario scenario) {
        ActorlessStepsGetter asg = new ActorlessStepsGetter();
        scenario.accept(asg);
        return  asg.getActorlessSteps();
    }

}
