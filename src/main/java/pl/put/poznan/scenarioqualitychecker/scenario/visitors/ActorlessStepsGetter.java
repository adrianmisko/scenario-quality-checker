package pl.put.poznan.scenarioqualitychecker.scenario.visitors;

import lombok.Getter;
import lombok.Setter;
import pl.put.poznan.scenarioqualitychecker.scenario.Visitor;
import pl.put.poznan.scenarioqualitychecker.scenario.model.Scenario;
import pl.put.poznan.scenarioqualitychecker.scenario.model.Step;

import java.util.ArrayList;
import java.util.List;

public class ActorlessStepsGetter implements Visitor {

    @Getter
    @Setter
    private List<Step> actorlessSteps;

    public ActorlessStepsGetter() {
        actorlessSteps = new ArrayList<>();
    }

    @Override
    public void visit(Step step) {
        if (step.getActor().equals("")) {
            actorlessSteps.add(step);
        }
    }

    @Override
    public void visit(Scenario scenario) {
        ;
    }

}
