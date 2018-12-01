package pl.put.poznan.scenarioqualitychecker.scenario.visitors;

import lombok.Getter;
import lombok.Setter;
import pl.put.poznan.scenarioqualitychecker.scenario.Visitor;
import pl.put.poznan.scenarioqualitychecker.scenario.model.Scenario;
import pl.put.poznan.scenarioqualitychecker.scenario.model.Step;

import java.util.ArrayList;
import java.util.List;

public class KeywordsCounter implements Visitor {

    @Setter
    @Getter
    private int numStepsWithKeywords;

    public KeywordsCounter() {
        numStepsWithKeywords = 0;
    }

    @Override
    public void visit(Scenario scenario) {
        ;
    }

    @Override
    public void visit(Step step) {
        if (! step.getKeyword().equals("")) {
            numStepsWithKeywords++;
        }
    }

}
