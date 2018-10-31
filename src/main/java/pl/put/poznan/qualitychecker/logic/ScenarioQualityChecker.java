package pl.put.poznan.qualitychecker.logic;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.put.poznan.qualitychecker.models.Header;
import pl.put.poznan.qualitychecker.models.Scenario;
import pl.put.poznan.qualitychecker.models.Step;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScenarioQualityChecker {

    private ObjectMapper mapper;

    public ScenarioQualityChecker() {
        mapper = new ObjectMapper();
    }

    public Scenario processNode(String scenario, ArrayList<Scenario> scenarios) {
        Scenario s = new Scenario();
        try {
            JsonNode node = mapper.readTree(scenario);
            s.header = mapper.readValue(node.get("header").toString(), Header.class);
            node.at("/steps").forEach(elem -> {
                Step step = new Step();
                step.keyword = elem.get("keyword").asText();
                step.actor = elem.get("actor").asText();
                step.systemActor = elem.get("system actor").asText();
                step.text = elem.get("text").asText();
                String subScenarioJSON = elem.get("scenario").toString();
                if (subScenarioJSON.equals("\"\""))
                    step.subScenario = null;
                else
                    step.subScenario = processNode(subScenarioJSON, scenarios);
                s.steps.add(step);
            });
            scenarios.add(s);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return s;
    }

}
