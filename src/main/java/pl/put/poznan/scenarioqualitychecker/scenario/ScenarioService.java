package pl.put.poznan.scenarioqualitychecker.scenario;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.put.poznan.scenarioqualitychecker.scenario.dao.Scenario;
import pl.put.poznan.scenarioqualitychecker.scenario.dao.Step;

import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;

/**
 * This class describes service for application.
 */

@Service
public class ScenarioService {

    /**
     * Creation of repository instance.
     */

    @Autowired
    ScenarioRepository repository;


    /**
     * Method that saves scenario in repository.
     * @param scenario A scenario which you want to save.
     */

    public void createScenario(Scenario scenario) {
        repository.save(scenario);
    }

    /**
     * Method that return a list of steps with empty actor list.
     * @param scenario A scenario from which you want to get steps with empty actor list.
     * @param result A list of steps which is your result.
     */

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

    /**
     * Method of getting list of steps with empty actor list.
     * @param scenario A scenario from which you want to get steps with empty actor list.
     * @return A list of steps with empty actor list.
     */

    public List<Map<Long, List<Step>>> getNoActors(Scenario scenario) {
        List<Map<Long, List<Step>>> result = new ArrayList<>();
        traverse(scenario, result);
        return result;
    }

    /**
     * Method of getting number of all steps in scenario.
     * @param scenario A scenario from which you want to get number of all steps in scenario.
     * @param NumberOfSteps Integer of number of steps.
     * @return Number of all steps in scenario.
     */

    public int getStepNumber(Scenario scenario, int NumberOfSteps) {
        for (Step step : scenario.getSteps()) {
            NumberOfSteps = NumberOfSteps + 1;
            if (step.getScenario() != null)
                NumberOfSteps = getStepNumber(step.getScenario(), NumberOfSteps);
        }
        return NumberOfSteps;
    }

    /**
     * Method of getting number of steps in scenario with empty keywords string.
     * @param scenario A scenario from which you want to number of steps in scenario with empty keywords string.
     * @param NumberOfKeywords Integer of number of steps.
     * @return Number of steps in scenario with empty keywords string.
     */

    public int getKeywordNumber(Scenario scenario, int NumberOfKeywords) {
        for (Step step : scenario.getSteps()) {
            if(!step.getKeyword().equals("")) NumberOfKeywords++;
            if (step.getScenario() != null) NumberOfKeywords = getKeywordNumber(step.getScenario(), NumberOfKeywords);
        }
        return NumberOfKeywords;
    }

    public int countSteps(Scenario scenario) {
        int NumberOfSteps = 0;
        NumberOfSteps = getStepNumber(scenario, NumberOfSteps);
        return NumberOfSteps;
    }

    /**
     * Method that call application service.
     * @param id Id scenario.
     * @param params The string that determines what api functions will be called/applied.
     * @return A JSON response containing results of called api functions.
     */

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
                case "NumberOfKeywords": {
                    response.put(param, getKeywordNumber(scenario, 0));
                    break;
                }
            }
        }
        return response;
    }


    /**
     * Method to get list of all scenarios.
     * @return List of scenarios.
     */

    public List<Scenario> getAllScenarios() {
        return Lists.newArrayList(repository.findAll());
    }

    /**
     * Method to get a scenario using its id.
     * @param id Id of scenario.
     * @return A scenario from repository.
     */

    public Scenario getScenario(long id) {
        return repository.findById(id).get();
    }

    /**
     * A constructor for class.
     */

    public ScenarioService() {
        super();
    }
}
