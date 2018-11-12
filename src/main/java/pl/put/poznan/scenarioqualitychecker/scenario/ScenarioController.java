package pl.put.poznan.scenarioqualitychecker.scenario;


import com.sun.jdi.event.StepEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.scenarioqualitychecker.scenario.dao.Scenario;
import pl.put.poznan.scenarioqualitychecker.scenario.dao.Step;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * This class describes control for application.
 */

@Slf4j
@RestController
public class ScenarioController {

    /**
     * Creation of service instance.
     */

    @Autowired
    private ScenarioService service;


    /**
     * Endpoint for getting all scenarios
     * @return List of all scenarios
     */

    @RequestMapping(value = "/scenarios", method = RequestMethod.GET, produces = "application/json")
    public Map<String, List<Scenario>> getScenarios() {
        log.info("GET /scenarios");
        Map<String, List<Scenario>> result = new LinkedHashMap<>();
        result.put("scenarios", service.getAllScenarios());
        return result;
    }

    /**
     * Method for getting response from service.
     * @param id Id scenario.
     * @param params The string that determines what api functions will be called/applied.
     * @return A JSON response containing results of called api functions.
     */

    @RequestMapping(value = "/scenarios/{id}", method = RequestMethod.GET, produces = "application/json")
    public Map<String, Object> getScenario(@PathVariable long id, @RequestParam(value = "api", required = false) String[] params) {

        if (params == null) {
            log.info("GET /scenarios/" + Long.toString(id));
            Map<String, Object>response = new LinkedHashMap<>();
            response.put("scenario", service.getScenario(id));
            return response;
        }

        log.info("GET /scenarios/" + Long.toString(id) + "?api=" + params);
        return service.apiCall(id, params);
    }

    /**
     * Method for adding scenario to service.
     * @param scenario JSON representing serialized scenario object which you want to add.
     * @return A response that informs about success.
     */

    @RequestMapping(value = "/scenarios", method = RequestMethod.POST,
            consumes = "application/json", produces = "application/json")
    public ResponseEntity addScenario(@RequestBody @Valid Scenario scenario) {

        log.info("POST /scenarios");
        service.createScenario(scenario);

        return new ResponseEntity("{ \"message\" : \"success\", \"id\" : \"" + Long.toString(scenario.getId()) +"\" }", HttpStatus.CREATED);

    }

    /**
     * A constructor for class.
     */

    public ScenarioController() {
        super();
    }
}
