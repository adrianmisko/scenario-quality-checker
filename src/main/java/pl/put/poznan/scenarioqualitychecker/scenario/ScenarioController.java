package pl.put.poznan.scenarioqualitychecker.scenario;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.scenarioqualitychecker.scenario.dao.Scenario;

import javax.validation.Valid;

@RestController
public class ScenarioController {

    @Autowired
    private ScenarioService service;

    @RequestMapping(value = "/scenario/{id}", method = RequestMethod.GET,
            consumes = "application/json", produces = "application/json")
    public ResponseEntity getScenario(@PathVariable long id, @RequestParam(value = "api", required = false) String[] params) {

        return new ResponseEntity("{ \"error\" : \"not implemented yet\" }", HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(value = "/scenario", method = RequestMethod.POST,
            consumes = "application/json", produces = "application/json")
    public ResponseEntity addScenario(@RequestBody @Valid Scenario scenario) {

        service.createScenario(scenario);
        return new ResponseEntity("{ \"message\" : \"success\", \"id\" : \"" + Long.toString(scenario.getId()) +"\" }", HttpStatus.CREATED);

    }

}
