package pl.put.poznan.scenarioqualitychecker.scenario;

import pl.put.poznan.scenarioqualitychecker.scenario.model.Scenario;
import pl.put.poznan.scenarioqualitychecker.scenario.model.Step;

public interface Visitor {

    void visit(Step step);
    void visit(Scenario scenario);
    void afterVisit(Step step);
    void afterVisit(Scenario scenario);

}
