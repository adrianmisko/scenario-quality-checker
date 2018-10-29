package pl.put.poznan.qualitychecker.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.qualitychecker.logic.ScenarioQualityChecker;

import java.util.Arrays;


@RestController
public class ScenarioQualityCheckerController {

    private static final Logger logger = LoggerFactory.getLogger(ScenarioQualityCheckerController.class);

    @RequestMapping(value="/scenarios/{id}", method=RequestMethod.GET, produces="application/json")
    public String get(@PathVariable int id) {

        logger.debug("scenario " + id);

        // logic
        String sid = Integer.toString(id);
        return "{ \"id\" : \"" + sid + "\" }";  //return jsonified scenarios[id] or func(scenarios[id]) depending on params
    }

    @RequestMapping(value="/add", method=RequestMethod.POST, produces="application/json", consumes="application/json")
    public String post(@RequestBody String scenarioJSON) {

        //add scenario object constructed from json (gson or other lib), return OK response and resource ID
        //parse it here or add model and convert it with @RequestBody <T> scenarioJSON - uses Jackson by default

        return "{\"id\" : \"1\" }" ; //object id for future reference
    }


}


