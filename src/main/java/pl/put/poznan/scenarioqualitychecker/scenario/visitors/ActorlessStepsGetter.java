package pl.put.poznan.scenarioqualitychecker.scenario.visitors;

import lombok.Getter;
import lombok.Setter;
import pl.put.poznan.scenarioqualitychecker.scenario.Visitor;
import pl.put.poznan.scenarioqualitychecker.scenario.model.Scenario;
import pl.put.poznan.scenarioqualitychecker.scenario.model.Step;

import java.util.ArrayList;
import java.util.List;

/**
 * This class gets steps that don't have an actor from the scenario and stores them in actrolessSteps list;
 */

public class ActorlessStepsGetter implements Visitor {

    @Getter
    @Setter
    private List<Step> actorlessSteps;

    public ActorlessStepsGetter() {
        actorlessSteps = new ArrayList<>();
    }

    /**
     * Method to check if step is without an actor
     * @param step Step of scenario.
     */
    @Override
    public void visit(Step step) {
        if (step.getActor().equals("")) {
            actorlessSteps.add(step);
        }
    }

    @Override
    public void visit(Scenario scenario) {}

    @Override
    public void afterVisit(Step step) {}

    @Override
    public void afterVisit(Scenario scenario) {}

}
