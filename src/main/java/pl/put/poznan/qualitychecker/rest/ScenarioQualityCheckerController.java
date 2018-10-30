package pl.put.poznan.qualitychecker.rest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.qualitychecker.logic.ScenarioQualityChecker;
import pl.put.poznan.qualitychecker.models.Scenario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
public class ScenarioQualityCheckerController {

    private static final Logger logger = LoggerFactory.getLogger(ScenarioQualityCheckerController.class);
    private static final List<Scenario> scenarios = new ArrayList<>();

    @RequestMapping(value="/scenarios/{id}", method=RequestMethod.GET, produces="application/json")
    public String get(@PathVariable int id, @RequestParam(value="extract", defaultValue="") String[] transforms) {

        logger.debug("scenario " + id);


        return "{ \"id\" : \"" + "423" + "\" }";
    }

    @RequestMapping(value="/scenarios/add", method=RequestMethod.POST, produces="application/json", consumes="application/json")
    public String post(@RequestBody String scenarioJSON) {

        ScenarioQualityChecker sqc = new ScenarioQualityChecker();

        return scenarioJSON;
    }


}


