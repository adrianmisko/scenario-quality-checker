package pl.put.poznan.scenarioqualitychecker.scenario.visitors;

import lombok.Getter;
import lombok.Setter;
import pl.put.poznan.scenarioqualitychecker.scenario.Visitor;
import pl.put.poznan.scenarioqualitychecker.scenario.model.Scenario;
import pl.put.poznan.scenarioqualitychecker.scenario.model.Step;

public class SubscenarioCounter implements Visitor {

    @Getter
    @Setter
    private int NumOfSubscenario;

    public SubscenarioCounter() {
        NumOfSubscenario = -1;
    }

    @Override
    public void visit(Step s) {}

    @Override
    public void visit(Scenario scenario) {NumOfSubscenario++;}

    @Override
    public void afterVisit(Step step) {}

    @Override
    public void afterVisit(Scenario scenario) {}
}
