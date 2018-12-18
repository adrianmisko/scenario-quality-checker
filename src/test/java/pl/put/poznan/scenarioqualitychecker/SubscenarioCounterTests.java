package pl.put.poznan.scenarioqualitychecker;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import pl.put.poznan.scenarioqualitychecker.scenario.model.Scenario;
import pl.put.poznan.scenarioqualitychecker.scenario.visitors.SubscenarioCounter;

import java.io.IOException;


public class SubscenarioCounterTests {

    private String scenariowithoutsubscenario = "{\"header\":{\"title\":\"add item\",\"actors\":[\"actor1\", \"actor2\"],\"system actors\":[\"sysactor1\", \"sysactor2\"]},\"steps\":[{\"keyword\":\"\",\"actor\":\"actor2\",\"system actor\":\"sysactor2\",\"text\":\"lorem40\",\"scenario\":null}]}";
    private String scenariowithsubscenario = "{\"header\":{\"title\":\"add item\",\"actors\":[\"actor1\", \"actor2\"],\"system actors\":[\"sysactor1\", \"sysactor2\"]},\"steps\":[{\"keyword\":\"\",\"actor\":\"actor1\",\"system actor\":\"sysactor1\",\"text\":\"lorem40\",\"scenario\":null},{\"keyword\":\"IF\",\"actor\":\"actor1\",\"system actor\":\"sysactor2\",\"text\":\"lorem40\",\"scenario\":{\"header\":{\"title\":\"title2\",\"actors\":[\"actor222\"],\"system actors\":[\"sysactor222\"]},\"steps\":[{\"keyword\":\"FOR EACH\",\"actor\":\"actor222\",\"system actor\":\"sysactor222\",\"text\":\"lorem ipsum\",\"scenario\":null},{\"keyword\":\"\",\"actor\":\"actor222\",\"system actor\":\"sysactor222\",\"text\":\"lorem ipsum2\",\"scenario\":{\"header\":{\"title\":\"title3\",\"actors\":[\"actor222\"],\"system actors\":[\"sysactor222\"]},\"steps\":[{\"keyword\":\"FOR EACH\",\"actor\":\"actor222\",\"system actor\":\"sysactor222\",\"text\":\"lorem ipsum\",\"scenario\":null}]}}]}},{\"keyword\":\"\",\"actor\":\"actor2\",\"system actor\":\"sysactor2\",\"text\":\"lorem40\",\"scenario\":null}]}";

    @Test
    public void testScenarioWithoutSteps() throws IOException{
        ObjectMapper om = new ObjectMapper();
        Scenario s = om.readValue(scenariowithoutsubscenario, Scenario.class);
        SubscenarioCounter subc = new SubscenarioCounter();
        s.accept(subc);
        Assert.assertNotNull(subc.getNumOfSubscenario());
        Assert.assertEquals(0, subc.getNumOfSubscenario());
    }

    @Test
    public void testScenarioWithSteps() throws IOException{
        ObjectMapper om = new ObjectMapper();
        Scenario s = om.readValue(scenariowithsubscenario, Scenario.class);
        SubscenarioCounter subc = new SubscenarioCounter();
        s.accept(subc);
        Assert.assertNotNull(subc.getNumOfSubscenario());
        Assert.assertEquals(2, subc.getNumOfSubscenario());
    }
}
