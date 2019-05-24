package pl.put.poznan.scenarioqualitychecker.scenario;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import pl.put.poznan.scenarioqualitychecker.scenario.model.Scenario;
import pl.put.poznan.scenarioqualitychecker.scenario.strategies.*;
import pl.put.poznan.scenarioqualitychecker.scenario.visitors.*;

import java.util.*;

/**
 * This class describes service for application.
 */

@Service
public class ScenarioService {

    /**
     * Creation of repository instance.
     */

    @Autowired
    private ScenarioRepository repository;

    private ResponseGenerationStrategy responseGenerationStrategy;

    private Map<String, ResponseGenerationStrategy> strategies = Map.of(
            "NoActors",             new GetNumberOfStepsWithoutActors(),
            "NumberOfSteps",        new GetNumberOfSteps(),
            "NumberOfKeywords",     new GetNumberOfKeywords(),
            "NumberedListOfSteps",  new GetNumberedListOfSteps(),
            "NumberOfSubscenarios", new GetNumberOfSubscenarios(),
            "ScenariosUpToLevel",   new GetScenariosUpToLevel()
    );

    /**
     * Method that saves scenario in repository.
     * @param scenario A scenario which you want to save.
     */

    public void createScenario(Scenario scenario) {
        repository.save(scenario);
    }

    /**
     * This method executes functions based on request parameters and concatenates the results into JSON response.
     * @param id ID of scenario that functions will be called on.
     * @param params The string that determines what api functions will be called/applied.
     * @return A JSON response containing results of called api functions.
     */

    public Map<String, Object> makeResponseFromRequestParameters(long id, String[] params) {
        Scenario scenario = getScenario(id);
        Map<String, Object> response = new LinkedHashMap<>();
        for (String param : params) {
            response.put(param, makeResponseFromParameter(param, scenario));
        }
        return response;
    }

    private Object makeResponseFromParameter(String param, Scenario scenario) {

        this.responseGenerationStrategy = strategies.getOrDefault(param, new OperationNotFound());
        return responseGenerationStrategy.getResponse(scenario);

    }

    /**
     * Get scenario in graph form. More info on visitors/GraphBuilder
     * Requires separate endpoint because return type is text instead of application/json
     * @return dot-use ready String
     */

    public String getScenarioInGraphForm(long id) {
        Scenario scenario = getScenario(id);
        GraphBuilder gb = new GraphBuilder();
        scenario.accept(gb);
        return gb.getScenarioGraphInTextForm();
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
        return repository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    /**
     * A constructor for class.
     */

    public Map<String, String> getScenariosWithTitles() {
        Map<String, String> response = new HashMap<>();

        List<Scenario> scenarios = this.getAllScenarios();
        for (Scenario s : scenarios)
            response.put(s.getHeader().getTitle(), Long.toString(s.getId()));

        return response;
    }

    public ScenarioService() {
        super();
    }

}
