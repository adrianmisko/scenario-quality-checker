package pl.put.poznan.scenarioqualitychecker.scenario;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.scenarioqualitychecker.scenario.dao.Scenario;

import javax.validation.Valid;

@RestController
public class ScenarioController {

    @Autowired
    private ScenarioService service;

    @RequestMapping(value = "/scenario/{id}", method = RequestMethod.GET,
            consumes = "application/json", produces = "application/json")
    public String getScenario(@PathVariable long id, @RequestParam(value = "api") String[] params) {

        return Long.toString(id);

    }

    @RequestMapping(value = "/scenario", method = RequestMethod.POST,
            consumes = "application/json", produces = "application/json")
    public String addScenario(@RequestBody @Valid Scenario scenario) {

        service.createScenario(scenario);

        for (Scenario scenario1 : service.getAllScenarios()) {
            System.out.println(scenario1.getId());
        }
        return Long.toString(scenario.getId());

    }

}
