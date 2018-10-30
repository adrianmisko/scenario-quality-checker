package pl.put.poznan.qualitychecker.rest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.qualitychecker.logic.ScenarioQualityChecker;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;


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

    @RequestMapping(value="/scenarios/add", method=RequestMethod.POST, produces="application/json", consumes="application/json")
    public String post(@RequestBody String scenarioJSON) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode node = mapper.readTree(scenarioJSON);
            if (node.isObject())
                System.out.println(node.get("steps").get(1).get("scenario"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return scenarioJSON;
    }


}


