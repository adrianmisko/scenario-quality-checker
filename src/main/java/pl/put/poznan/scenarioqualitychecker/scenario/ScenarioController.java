package pl.put.poznan.scenarioqualitychecker.scenario;


import com.sun.jdi.event.StepEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.scenarioqualitychecker.scenario.dao.Scenario;
import pl.put.poznan.scenarioqualitychecker.scenario.dao.Step;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * This class describes control for application.
 */

@RestController
public class ScenarioController {

    /**
     * Creation of service instance.
     */

    @Autowired
    private ScenarioService service;

    /**
     * Method for getting response from service.
     * @param id Id scenario.
     * @param params The string that determines what api functions will be called/applied.
     * @return A JSON response containing results of called api functions.
     */

    @RequestMapping(value = "/scenarios/{id}", method = RequestMethod.GET,
            consumes = "application/json", produces = "application/json")

    public Map<String, Object> getScenario(@PathVariable long id, @RequestParam(value = "api", required = false) String[] params) {

        Map<String, Object> response = service.apiCall(id, params);

        return response;
    }

    /**
     * Method for adding scenario to service.
     * @param scenario JSON representing serialized scenario object which you want to add.
     * @return A response that informs about success.
     */

    @RequestMapping(value = "/scenarios", method = RequestMethod.POST,
            consumes = "application/json", produces = "application/json")

    public ResponseEntity addScenario(@RequestBody @Valid Scenario scenario) {

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
