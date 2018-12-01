package pl.put.poznan.scenarioqualitychecker.scenario.visitors;

import lombok.Getter;
import lombok.Setter;
import pl.put.poznan.scenarioqualitychecker.scenario.Visitor;
import pl.put.poznan.scenarioqualitychecker.scenario.model.Scenario;
import pl.put.poznan.scenarioqualitychecker.scenario.model.Step;

public class StepCounter implements Visitor {

    @Getter
    @Setter
    private int numOfSteps;

    public StepCounter() {
        numOfSteps = 0;
    }

    @Override
    public void visit(Step s) {
        numOfSteps++;
    }

    @Override
    public void visit(Scenario scenario) {}

    @Override
    public void afterVisit(Step step) {}

    @Override
    public void afterVisit(Scenario scenario) {}

}
