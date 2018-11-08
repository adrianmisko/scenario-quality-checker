package pl.put.poznan.scenarioqualitychecker.scenario;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.put.poznan.scenarioqualitychecker.scenario.dao.Scenario;
import pl.put.poznan.scenarioqualitychecker.scenario.dao.Step;

import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;




@Service
public class ScenarioService {

    @Autowired ScenarioRepository repository;


    public void createScenario(Scenario scenario) {
        repository.save(scenario);
    }


    public void traverse(Scenario scenario, List<Map<Long, List<Step>>> result) {
        List<Scenario> toVisit = new ArrayList<>();
        result.add(new HashMap<>());
        result.get(result.size()-1).put(scenario.getId(), new ArrayList<>());
        for (Step step : scenario.getSteps()) {
            if (step.getActor().equals(""))
                result.get(result.size()-1).get(scenario.getId()).add(step);
            if (step.getScenario() != null)
                toVisit.add(step.getScenario());
        }
        for (Scenario s : toVisit)
            traverse(s, result);
    }


    public List<Map<Long, List<Step>>> getNoActors(Scenario scenario) {
        List<Map<Long, List<Step>>> result = new ArrayList<>();
        traverse(scenario, result);
        return result;
    }

    public int getStepNumber(Scenario scenario, int NumberOfSteps) {
        for (Step step : scenario.getSteps()) {
            NumberOfSteps = NumberOfSteps + 1;
            if (step.getScenario() != null)
                NumberOfSteps = getStepNumber(step.getScenario(), NumberOfSteps);
        }
        return NumberOfSteps;
    }


    public int countSteps(Scenario scenario) {
        int NumberOfSteps = 0;
        NumberOfSteps = getStepNumber(scenario, NumberOfSteps);
        return NumberOfSteps;
    }

    public Map<String, Object> apiCall(long id, String[] params) {
        Scenario scenario = getScenario(id);
        Map<String, Object> response = new LinkedHashMap<>();
        for (String param : params) {
            switch (param) {
                case "noactors": {
                    response.put(param, getNoActors(scenario));
                    break;
                }
                case "NumberOfSteps": {
                    response.put(param, countSteps(scenario));
                    break;
                }
            }
        }
        return response;
    }


    public List<Scenario> getAllScenarios() {
        return Lists.newArrayList(repository.findAll());
    }

    public Scenario getScenario(long id) {
        return repository.findById(id).get();
    }

}
