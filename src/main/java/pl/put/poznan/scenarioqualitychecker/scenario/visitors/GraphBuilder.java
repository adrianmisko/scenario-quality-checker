package pl.put.poznan.scenarioqualitychecker.scenario.visitors;


import pl.put.poznan.scenarioqualitychecker.scenario.Visitor;
import pl.put.poznan.scenarioqualitychecker.scenario.model.Scenario;
import pl.put.poznan.scenarioqualitychecker.scenario.model.Step;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


/**
 * This class builds description of graph in a simple text language, to be consumed by graphviz software,
 * more precisely by the 'dot' tool
 * https://www.graphviz.org/
 * https://graphviz.gitlab.io/_pages/doc/info/lang.html
 */

public class GraphBuilder implements Visitor {

    private StringBuilder sb = new StringBuilder();
    private int currentDepth = -1;
    private int currentStep = 0;
    private Step lastStep;
    private List<Integer> stepsLength = new ArrayList<>();
    private Stack<Integer> stepNumbers = new Stack<>();

    public GraphBuilder() {
        sb.append("strict digraph {\n");
    }

    @Override
    public void visit(Step step) {
            if (currentStep == 0) {
                sb.append("\t\"");
                sb.append(step.getText());
                sb.append("\" -> ");
            } else {
                sb.append("\"");
                sb.append(step.getText());
                sb.append("\" [rank=");
                sb.append(currentDepth);
                sb.append("]\n\t");
                if (stepsLength.get(currentDepth) != currentStep + 1) {
                    sb.append("\"");
                    sb.append(step.getText());
                    sb.append("\" -> ");
                }
            }
        currentStep++;
        lastStep = step;
    }

    @Override
    public void visit(Scenario scenario) {
        currentDepth++;
        if (currentDepth > 0) {
            sb.append("\"");
            sb.append(lastStep.getText());
            sb.append("\" -> ");
        }
        stepsLength.add(scenario.getSteps().size());
        stepNumbers.push(currentStep);
        currentStep = 0;
    }

    @Override
    public void afterVisit(Step step) {
    }

    @Override
    public void afterVisit(Scenario scenario) {
        sb.append("\"");
        sb.append(lastStep.getText());
        sb.append("\" -> ");
        currentDepth--;
        currentStep = stepNumbers.pop();
    }

    public void onFinish() {
        sb.append("}");
    }

    public String getScenarioGraphInTextForm() {
        return sb.toString();
    }
}
