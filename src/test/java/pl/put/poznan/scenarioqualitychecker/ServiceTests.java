package pl.put.poznan.scenarioqualitychecker;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import pl.put.poznan.scenarioqualitychecker.scenario.ScenarioService;
import pl.put.poznan.scenarioqualitychecker.scenario.model.Scenario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTests {

    @Autowired
    private ScenarioService scenarioService;

    @Test
    public void testGettingAllScenarios() throws IOException {
        String s1 = "{\"header\":{\"title\":\"additem1\",\"actors\":[\"actor1\",\"actor2\",\"\"],\"system actors\":[\"sysactor1\",\"sysactor2\"]},\"steps\":[{\"keyword\":\"\",\"actor\":\"actor1\",\"system actor\":\"sysactor1\",\"text\":\"lorem40\",\"scenario\":null},{\"keyword\":\"IF\",\"actor\":\"actor1\",\"system actor\":\"sysactor2\",\"text\":\"lorem40\",\"scenario\":{\"header\":{\"title\":\"title2\",\"actors\":[\"actor222\"],\"system actors\":[\"sysactor222\"]},\"steps\":[{\"keyword\":\"FOREACH\",\"actor\":\"actor222\",\"system actor\":\"sysactor222\",\"text\":\"loremipsum\",\"scenario\":null},{\"keyword\":\"\",\"actor\":\"actor222\",\"system actor\":\"sysactor222\",\"text\":\"loremipsum2\",\"scenario\":null}]}},{\"keyword\":\"\",\"actor\":\"actor2\",\"system actor\":\"sysactor2\",\"text\":\"lorem40\",\"scenario\":null}]}";
        String s2 = "{\"header\":{\"title\":\"additem2\",\"actors\":[\"actor1\",\"actor2\",\"\"],\"system actors\":[\"sysactor1\",\"sysactor2\"]},\"steps\":[{\"keyword\":\"\",\"actor\":\"actor1\",\"system actor\":\"sysactor1\",\"text\":\"lorem40\",\"scenario\":null},{\"keyword\":\"IF\",\"actor\":\"actor1\",\"system actor\":\"sysactor2\",\"text\":\"lorem40\",\"scenario\":{\"header\":{\"title\":\"title3\",\"actors\":[\"actor222\"],\"system actors\":[\"sysactor222\"]},\"steps\":[{\"keyword\":\"FOREACH\",\"actor\":\"actor222\",\"system actor\":\"sysactor222\",\"text\":\"loremipsum\",\"scenario\":null},{\"keyword\":\"\",\"actor\":\"actor222\",\"system actor\":\"sysactor222\",\"text\":\"loremipsum2\",\"scenario\":null}]}},{\"keyword\":\"\",\"actor\":\"actor2\",\"system actor\":\"sysactor2\",\"text\":\"lorem40\",\"scenario\":null}]}";
        ObjectMapper om = new ObjectMapper();
        scenarioService.createScenario(om.readValue(s1, Scenario.class));
        scenarioService.createScenario(om.readValue(s2, Scenario.class));
        Assert.assertEquals(6, scenarioService.getAllScenarios().size());
    }

    @Test
    public void getSingleExistingScenario() throws IOException {
        String s1 = "{\"header\":{\"title\":\"additem1\",\"actors\":[\"actor1\",\"actor2\",\"\"],\"system actors\":[\"sysactor1\",\"sysactor2\"]},\"steps\":[{\"keyword\":\"\",\"actor\":\"actor1\",\"system actor\":\"sysactor1\",\"text\":\"lorem40\",\"scenario\":null},{\"keyword\":\"IF\",\"actor\":\"actor1\",\"system actor\":\"sysactor2\",\"text\":\"lorem40\",\"scenario\":{\"header\":{\"title\":\"title2\",\"actors\":[\"actor222\"],\"system actors\":[\"sysactor222\"]},\"steps\":[{\"keyword\":\"FOREACH\",\"actor\":\"actor222\",\"system actor\":\"sysactor222\",\"text\":\"loremipsum\",\"scenario\":null},{\"keyword\":\"\",\"actor\":\"actor222\",\"system actor\":\"sysactor222\",\"text\":\"loremipsum2\",\"scenario\":null}]}},{\"keyword\":\"\",\"actor\":\"actor2\",\"system actor\":\"sysactor2\",\"text\":\"lorem40\",\"scenario\":null}]}";
        ObjectMapper om = new ObjectMapper();
        scenarioService.createScenario(om.readValue(s1, Scenario.class));
        Assert.assertNotNull(scenarioService.getScenario(1));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGettingScenarioThatDoesntExist() {
        scenarioService.getScenario(324234324);
    }

}
