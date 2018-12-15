package pl.put.poznan.scenarioqualitychecker;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import pl.put.poznan.scenarioqualitychecker.scenario.model.Scenario;
import pl.put.poznan.scenarioqualitychecker.scenario.visitors.StepCounter;

import java.io.IOException;


public class StepCounterTests {

    private String scenariowithoutstep = "{\"header\":{\"title\":\"add item\",\"actors\":[\"actor1\",\"actor2\"],\"system actors\":[\"sysactor1\", \"sysactor2\"]},\"steps\":[]}";
    private String scenariowithsteps = "{\"header\":{\"title\":\"add item\",\"actors\":[\"actor1\", \"actor2\"],\"system actors\":[\"sysactor1\", \"sysactor2\"]},\"steps\":[{\"keyword\":\"\",\"actor\":\"actor1\",\"system actor\":\"sysactor1\",\"text\":\"lorem40\",\"scenario\":null},{\"keyword\":\"IF\",\"actor\":\"actor1\",\"system actor\":\"sysactor2\",\"text\":\"lorem40\",\"scenario\":{\"header\":{\"title\":\"title2\",\"actors\":[\"actor222\"],\"system actors\":[\"sysactor222\"]},\"steps\":[{\"keyword\":\"FOR EACH\",\"actor\":\"actor222\",\"system actor\":\"sysactor222\",\"text\":\"lorem ipsum\",\"scenario\":null},{\"keyword\":\"\",\"actor\":\"actor222\",\"system actor\":\"sysactor222\",\"text\":\"lorem ipsum2\",\"scenario\":null}]}},{\"keyword\":\"\",\"actor\":\"actor2\",\"system actor\":\"sysactor2\",\"text\":\"lorem40\",\"scenario\":null}]}";

    @Test
    public void testscenariowithoutsteps() throws IOException{
        ObjectMapper om = new ObjectMapper();
        Scenario s = om.readValue(scenariowithoutstep, Scenario.class);
        StepCounter sc = new StepCounter();
        s.accept(sc);
        Assert.assertNotNull(sc.getNumOfSteps());
        Assert.assertEquals(0, sc.getNumOfSteps());
    }

    @Test
    public void testscenariowithsteps() throws IOException{
        ObjectMapper om = new ObjectMapper();
        Scenario s = om.readValue(scenariowithsteps, Scenario.class);
        StepCounter sc = new StepCounter();
        s.accept(sc);
        Assert.assertNotNull(sc.getNumOfSteps());
        Assert.assertEquals(5, sc.getNumOfSteps());
    }
}
