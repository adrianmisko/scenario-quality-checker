package pl.put.poznan.scenarioqualitychecker.scenario;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import pl.put.poznan.scenarioqualitychecker.scenario.model.Scenario;
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
    ScenarioRepository repository;


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

    public Map<String, Object> apiCall(long id, String[] params) {
        Scenario scenario = getScenario(id);
        Map<String, Object> response = new LinkedHashMap<>();
        Boolean scenariosUpToLevel = Boolean.FALSE;
        for (String param : params) {

            if(scenariosUpToLevel) {
                if (param.matches("[0-9]+")) {
                    ScenariosUpToLevelGetter scenariosUpToLevelGetter = new ScenariosUpToLevelGetter(Integer.parseInt(param));
                    scenario.accept(scenariosUpToLevelGetter);
                    response.put("ScenariosUpToLevel(" + param + ")", scenariosUpToLevelGetter.getScenariosUpToLevel());
                    scenariosUpToLevel = Boolean.FALSE;
                    continue;
                } else {
                    response.put(param, "Error: please put a positive Integer parameter after ScenariosUpToLevel parameter.");
                    break;
                }
            }
            switch (param) {
                case "NoActors": {
                    ActorlessStepsGetter asg = new ActorlessStepsGetter();
                    scenario.accept(asg);
                    response.put(param, asg.getActorlessSteps());
                    break;
                }
                case "NumberOfSteps": {
                    StepCounter sc = new StepCounter();
                    scenario.accept(sc);
                    response.put(param, sc.getNumOfSteps());
                    break;
                }
                case "NumberOfKeywords": {
                    KeywordsCounter kc = new KeywordsCounter();
                    scenario.accept(kc);
                    response.put(param, kc.getNumStepsWithKeywords());
                    break;
                }
                case "NumberedListOfSteps": {
                    NumberedListOfSteps nls = new NumberedListOfSteps();
                    scenario.accept(nls);
                    response.put(param, nls.getNumberedList());
                    break;
                }
                case "NumberOfSubscenarios": {
                    SubscenarioCounter subc = new SubscenarioCounter();
                    scenario.accept(subc);
                    response.put(param, subc.getNumOfSubscenario());
                    break;
                }
                case "ScenariosUpToLevel": {
                    scenariosUpToLevel = Boolean.TRUE;          //this is not perfect
                    break;
                }

                default: {
                    response.put(param, "wrong parameter");
                    break;
                }
            }
        }
        return response;
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

    public ScenarioService() {
        super();
    }
}
