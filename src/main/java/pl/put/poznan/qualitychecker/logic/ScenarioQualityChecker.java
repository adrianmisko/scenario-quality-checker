package pl.put.poznan.qualitychecker.logic;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.put.poznan.qualitychecker.models.Header;
import pl.put.poznan.qualitychecker.models.Scenario;
import pl.put.poznan.qualitychecker.models.Step;

import java.io.IOException;
import java.util.ArrayList;

public class ScenarioQualityChecker {

    private ObjectMapper mapper;

    public ScenarioQualityChecker() {
        mapper = new ObjectMapper();
    }

    public Scenario processNode(String scenarioJSON, ArrayList<Scenario> scenarios) {
        Scenario scenario = new Scenario();
        try {
            JsonNode node = mapper.readTree(scenarioJSON);
            scenario.header = mapper.readValue(node.get("header").toString(), Header.class);
            node.at("/steps").forEach(elem -> {
                Step step = new Step();
                step.keyword = elem.get("keyword").asText();
                step.actor = elem.get("actor").asText();
                step.systemActor = elem.get("system actor").asText();
                step.text = elem.get("text").asText();
                String subScenarioJSON = elem.get("scenario").toString();
                if (subScenarioJSON.equals("\"\""))
                    step.scenario = null;
                else
                    step.scenario = processNode(subScenarioJSON, scenarios);
                scenario.steps.add(step);
            });
            scenario.id = scenarios.size();
            scenarios.add(scenario);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return scenario;
    }

}
