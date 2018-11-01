package pl.put.poznan.qualitychecker.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.qualitychecker.logic.ScenarioQualityChecker;
import pl.put.poznan.qualitychecker.models.Scenario;
import java.util.ArrayList;
import javax.validation.Valid;

@RestController
public class ScenarioQualityCheckerController {

    private static final Logger logger = LoggerFactory.getLogger(ScenarioQualityCheckerController.class);
    private static final ArrayList<Scenario> scenarios = new ArrayList<>();

    @RequestMapping(value="/scenarios/{id}", method=RequestMethod.GET, produces="application/json")
    public String get(@PathVariable int id, @RequestParam(value="extract", defaultValue="") String[] transforms) {

        logger.debug("GET /scenario/" + id);

        return  "{}";
    }

    @RequestMapping(value="/scenarios/add", method=RequestMethod.POST, produces="application/json", consumes="application/json")
    public String post(@RequestBody Scenario scenario) {

        scenario.id = scenarios.size();
        scenarios.add(scenario);

        return "{ \"id\" : " + Integer.toString(scenario.id) + "}";
    }

}


