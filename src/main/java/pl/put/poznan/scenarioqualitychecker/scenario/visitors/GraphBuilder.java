package pl.put.poznan.scenarioqualitychecker.scenario.visitors;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.google.common.base.Strings;
import lombok.Getter;
import lombok.Setter;
import pl.put.poznan.scenarioqualitychecker.scenario.Visitor;
import pl.put.poznan.scenarioqualitychecker.scenario.model.Scenario;
import pl.put.poznan.scenarioqualitychecker.scenario.model.Step;

import java.util.*;

/**
 * This class builds description of graph in a simple text language, to be consumed by graphviz software,
 * more precisely by the 'dot' tool
 * https://www.graphviz.org/
 * https://graphviz.gitlab.io/_pages/doc/info/lang.html
 */

class Node {
    int id;
    @Getter
    String label;
    int rank;
    private Map<String, String> properties = new HashMap<>();

    Node (int id, String label, int rank){
        this.id = id;
        this.label = label;
        this.rank = rank;
        properties.put("id", Integer.toString(id));
        properties.put("label", label);
        properties.put("rank", Integer.toString(rank));
    }

    @JsonAnyGetter
    public Map<String, String> getProperties() {
        return properties;
    }

    @Override
    public String toString() {
        return "\t\"" + label + "\" [rank=" + Integer.toString(rank) + "]\n";
    }
}

class Edge {
    int id;
    String source;
    String target;
    private Map<String, String> properties = new HashMap<>();

    Edge (int id, String source, String target){
        this.id = id;
        this.source = source;
        this.target = target;
        properties.put("id", Integer.toString(id));
        properties.put("source", source);
        properties.put("target", target);
    }

    @JsonAnyGetter
    public Map<String, String> getProperties() {
        return properties;
    }

    @Override
    public String toString() {
        return "\t\"" + source + "\" -> " + "\"" + target + "\"\n";
    }
}

public class GraphBuilder implements Visitor {

    private int numOfScenarios  = 0;
    private int numOfSteps = 0;
    private int depth = 0;
    private Stack<Integer> stackOfSteps = new Stack<>();
    private List<Node> nodes = new ArrayList<>();
    private List<Edge> edges = new ArrayList<>();

    public GraphBuilder() {}

    @Override
    public void visit(Step s) {
        numOfSteps++;
        nodes.add(new Node(nodes.size(), s.getText(), numOfScenarios));
    }

    @Override
    public void visit(Scenario scenario) {
        numOfScenarios++;
        depth++;
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

    public String getScenarioGraphInTextForm() {

        StringBuilder sb = new StringBuilder();
        sb.append("strict digraph {\n");
        for (int i = 0; i < nodes.size() - 1; i++)
            edges.add(new Edge(edges.size(), nodes.get(i).getLabel(), nodes.get(i+1).getLabel()));
        for (Node node : nodes)
            sb.append(node.toString());
        for (Edge edge : edges)
            sb.append(edge.toString());
        for (int i = 1; i <= depth; i++) {
            sb.append("\t{ rank=same; ");
            for (Node node : nodes)
                if (node.rank == i)
                    sb.append("\"" + node.getLabel() + "\" ");
            sb.append("}\n");
        }
        sb.append("}");
        return sb.toString();
    }

}
