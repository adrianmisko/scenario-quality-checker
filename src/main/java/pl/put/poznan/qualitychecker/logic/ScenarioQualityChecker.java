package pl.put.poznan.qualitychecker.logic;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.put.poznan.qualitychecker.models.Header;
import pl.put.poznan.qualitychecker.models.Scenario;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;

public class ScenarioQualityChecker {

    private ObjectMapper mapper;

    public ScenarioQualityChecker() {
        mapper = new ObjectMapper();
    }

    public Scenario processNode(String scenario, ArrayList<Scenario> scenarios) {
        Scenario s = new Scenario();
        try {
            JsonNode node = mapper.readTree(scenario);
            s.header = mapper.readValue(node.get("header").asText(), Header.class);
            node.at("/steps").forEach(elem -> {
                // new step
                // parse JSON
                // step.subscenario = processNode(scenario-field, scenarios)
                // s.steps.add(step)
            });
            scenarios.add(s);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return s;
    }

}
