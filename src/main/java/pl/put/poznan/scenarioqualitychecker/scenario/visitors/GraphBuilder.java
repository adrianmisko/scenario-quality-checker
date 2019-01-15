package pl.put.poznan.scenarioqualitychecker.scenario.visitors;

import lombok.Getter;
import lombok.Setter;
import pl.put.poznan.scenarioqualitychecker.scenario.Visitor;
import pl.put.poznan.scenarioqualitychecker.scenario.model.Scenario;
import pl.put.poznan.scenarioqualitychecker.scenario.model.Step;

import java.util.ArrayList;
import java.util.List;

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

    public GraphBuilder() {
        sb.append("strict digraph {\n");
    }

    @Override
    public void visit(Step step) {
        if (currentStep % 2 == 0) {
            sb.append("\t\"");
            sb.append(step.getText());
            sb.append("\"->");
        } else {
            sb.append(step.getText());
            sb.append("[rank=\"");
            sb.append(currentDepth);
            sb.append("\"]\n");
        }
    }

    @Override
    public void visit(Scenario scenario) {
        currentDepth++;
    }

    @Override
    public void afterVisit(Step step) {
        currentStep++;
    }

    @Override
    public void afterVisit(Scenario scenario) {
        currentDepth--;
    }

    public void onFinish() {
        sb.append("\n}");
    }

    public String getScenarioGraphInTextForm() {
        return sb.toString();
    }
}
