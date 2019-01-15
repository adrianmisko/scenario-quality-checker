package pl.put.poznan.scenarioqualitychecker.scenario.visitors;

import com.google.common.base.Strings;
import lombok.Getter;
import lombok.Setter;
import pl.put.poznan.scenarioqualitychecker.scenario.Visitor;
import pl.put.poznan.scenarioqualitychecker.scenario.model.Scenario;
import pl.put.poznan.scenarioqualitychecker.scenario.model.Step;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NumberedListOfSteps implements Visitor {

    @Getter
    @Setter
    private List<String> numberedList;
    private int numOfScenarios;
    private int numOfSteps;
    private Stack<Integer> stackOfSteps;

    public NumberedListOfSteps() {
        numOfScenarios = 0;
        numOfSteps = 0;
        numberedList = new ArrayList<>();
        stackOfSteps = new Stack<>();
    }

    @Override
    public void visit(Step s) {
        numOfSteps++;
        numberedList.add(Strings.repeat("  ", numOfScenarios - 1) + Integer.toString(numOfScenarios) + '.' + Integer.toString(numOfSteps) +  ". " + s.getText());
    }

    @Override
    public void visit(Scenario scenario) {
        numOfScenarios++;
        stackOfSteps.push(numOfSteps);
        numOfSteps = 0;
    }

    @Override
    public void afterVisit(Step step) {

    }

    @Override
    public void afterVisit(Scenario scenario) {
        numOfScenarios--;
        numOfSteps = stackOfSteps.pop();
    }
}
