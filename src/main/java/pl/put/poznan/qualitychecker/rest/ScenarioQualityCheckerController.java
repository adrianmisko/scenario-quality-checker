package pl.put.poznan.qualitychecker.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.qualitychecker.logic.ScenarioQualityChecker;
import pl.put.poznan.qualitychecker.models.Scenario;

import java.util.Arrays;


@RestController
public class ScenarioQualityCheckerController {

    private static final Logger logger = LoggerFactory.getLogger(ScenarioQualityCheckerController.class);

    @RequestMapping(value="/scenarios/{id}", method=RequestMethod.GET, produces="application/json")
    public String get(@PathVariable int id, @RequestParam(value="extract", defaultValue="") String[] transforms) {

        logger.debug("scenario " + id);

        // logic
        String stringid = Integer.toString(id);

        return "{ \"id\" : \"" + stringid + "\" }";  //return jsonified scenarios[id] or func(scenarios[id]) depending on params
    }

    @RequestMapping(value="scenarios/add", method=RequestMethod.POST, produces="application/json", consumes="application/json")
    public String post(@RequestBody String scenarioJSON) {

        //add scenario object constructed from json (gson or other lib), return OK response and resource ID
        //parse it here or add model and convert it with @RequestBody <T> scenarioJSON - uses Jackson by default

        return scenarioJSON; //object id for future reference
    }


}


