package pl.put.poznan.scenarioqualitychecker;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import pl.put.poznan.scenarioqualitychecker.scenario.model.Scenario;
import java.io.IOException;


public class ScenarioTests {

    private String correctScenario = "{\"header\":{\"title\":\"additem1\",\"actors\":[\"actor1\",\"actor2\",\"\"],\"system actors\":[\"sysactor1\",\"sysactor2\"]},\"steps\":[{\"keyword\":\"\",\"actor\":\"actor1\",\"system actor\":\"sysactor1\",\"text\":\"lorem40\",\"scenario\":null},{\"keyword\":\"IF\",\"actor\":\"actor1\",\"system actor\":\"sysactor2\",\"text\":\"lorem40\",\"scenario\":{\"header\":{\"title\":\"title2\",\"actors\":[\"actor222\"],\"system actors\":[\"sysactor222\"]},\"steps\":[{\"keyword\":\"FOREACH\",\"actor\":\"actor222\",\"system actor\":\"sysactor222\",\"text\":\"loremipsum\",\"scenario\":null},{\"keyword\":\"\",\"actor\":\"actor222\",\"system actor\":\"sysactor222\",\"text\":\"loremipsum2\",\"scenario\":null}]}},{\"keyword\":\"\",\"actor\":\"actor2\",\"system actor\":\"sysactor2\",\"text\":\"lorem40\",\"scenario\":null}]}";
    private String scenarioWithNullHeader = "{\"h\":{\"title\":\"additem1\",\"actors\":[\"actor1\",\"actor2\",\"\"],\"system actors\":[\"sysactor1\",\"sysactor2\"]},\"steps\":[{\"keyword\":\"\",\"actor\":\"actor1\",\"system actor\":\"sysactor1\",\"text\":\"lorem40\",\"scenario\":null},{\"keyword\":\"IF\",\"actor\":\"actor1\",\"system actor\":\"sysactor2\",\"text\":\"lorem40\",\"scenario\":{\"header\":{\"title\":\"title2\",\"actors\":[\"actor222\"],\"system actors\":[\"sysactor222\"]},\"steps\":[{\"keyword\":\"FOREACH\",\"actor\":\"actor222\",\"system actor\":\"sysactor222\",\"text\":\"loremipsum\",\"scenario\":null},{\"keyword\":\"\",\"actor\":\"actor222\",\"system actor\":\"sysactor222\",\"text\":\"loremipsum2\",\"scenario\":null}]}},{\"keyword\":\"\",\"actor\":\"actor2\",\"system actor\":\"sysactor2\",\"text\":\"lorem40\",\"scenario\":null}]}";
    private String scenarioWithNullActors = "{\"header\":{\"title\":\"additem1\",\"acrs\":[\"actor1\",\"actor2\",\"\"],\"system actors\":[\"sysactor1\",\"sysactor2\"]},\"steps\":[{\"keyword\":\"\",\"actor\":\"actor1\",\"system actor\":\"sysactor1\",\"text\":\"lorem40\",\"scenario\":null},{\"keyword\":\"IF\",\"actor\":\"actor1\",\"system actor\":\"sysactor2\",\"text\":\"lorem40\",\"scenario\":{\"header\":{\"title\":\"title2\",\"actors\":[\"actor222\"],\"system actors\":[\"sysactor222\"]},\"steps\":[{\"keyword\":\"FOREACH\",\"actor\":\"actor222\",\"system actor\":\"sysactor222\",\"text\":\"loremipsum\",\"scenario\":null},{\"keyword\":\"\",\"actor\":\"actor222\",\"system actor\":\"sysactor222\",\"text\":\"loremipsum2\",\"scenario\":null}]}},{\"keyword\":\"\",\"actor\":\"actor2\",\"system actor\":\"sysactor2\",\"text\":\"lorem40\",\"scenario\":null}]}";
    private String scenarioWithNullSystemActors = "{\"header\":{\"title\":\"additem1\",\"actors\":[\"actor1\",\"actor2\",\"\"],\"systectors\":[\"sysactor1\",\"sysactor2\"]},\"steps\":[{\"keyword\":\"\",\"actor\":\"actor1\",\"system actor\":\"sysactor1\",\"text\":\"lorem40\",\"scenario\":null},{\"keyword\":\"IF\",\"actor\":\"actor1\",\"system actor\":\"sysactor2\",\"text\":\"lorem40\",\"scenario\":{\"header\":{\"title\":\"title2\",\"actors\":[\"actor222\"],\"system actors\":[\"sysactor222\"]},\"steps\":[{\"keyword\":\"FOREACH\",\"actor\":\"actor222\",\"system actor\":\"sysactor222\",\"text\":\"loremipsum\",\"scenario\":null},{\"keyword\":\"\",\"actor\":\"actor222\",\"system actor\":\"sysactor222\",\"text\":\"loremipsum2\",\"scenario\":null}]}},{\"keyword\":\"\",\"actor\":\"actor2\",\"system actor\":\"sysactor2\",\"text\":\"lorem40\",\"scenario\":null}]}";
    private String scenarioWithoutSteps = "{\"header\":{\"title\":\"additem1\",\"actors\":[\"actor1\",\"actor2\",\"\"],\"system actors\":[\"sysactor1\",\"sysactor2\"]},\"steps\":[]}";
    private String malformedScenario = "{\"header\":{\"title\":\"additem1\",\"actors\":[\"actor1\",\"actor2\",\"\"],\"system actors\":[\"sysactor1\",\"sysactor2\"]},\"steps\":[\"keyword\":\"\",\"actor\":\"actor1\",\"system actor\":\"sysactor1\",\"text\":\"lorem40\",\"scenario\":null},{\"keyword\":\"IF\",\"actor\":\"actor1\",\"system actor\":\"sysactor2\",\"text\":\"lorem40\",\"scenario\":{\"header\":{\"title\":\"title2\",\"actors\":[\"actor222\"],\"system actors\":[\"sysactor222\"]},\"steps\":[{\"keyword\":\"FOREACH\",\"actor\":\"actor222\",\"system actor\":\"sysactor222\",\"text\":\"loremipsum\",\"scenario\":null},{\"keyword\":\"\",\"actor\":\"actor222\",\"system actor\":\"sysactor222\",\"text\":\"loremipsum2\",\"scenario\":null}]}},{\"keyword\":\"\",\"actor\":\"actor2\",\"system actor\":\"sysactor2\",\"text\":\"lorem40\",\"scenario\":null}]}";


    //doesn't throw
    @Test
    public void testParsingCorrectJsonIntoObject() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.readValue(correctScenario, Scenario.class);
    }

    @Test
    public void testHeader() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Scenario scenario =  objectMapper.readValue(correctScenario, Scenario.class);
        Assert.assertEquals("additem1", scenario.getHeader().getTitle());
        Assert.assertEquals("actor1", scenario.getHeader().getActors().get(0));
        Assert.assertEquals("sysactor1", scenario.getHeader().getSystemActors().get(0));
    }

    @Test
    public void testSteps() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Scenario scenario =  objectMapper.readValue(correctScenario, Scenario.class);
        Assert.assertEquals(3, scenario.getSteps().size());
        Assert.assertEquals("IF", scenario.getSteps().get(1).getKeyword());
        Assert.assertNull(scenario.getSteps().get(0).getScenario());
        Assert.assertNotNull(scenario.getSteps().get(1).getScenario());
    }


    @Test
    public void testParsingJsonWithoutStepsIntoObject() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Scenario scenario = objectMapper.readValue(scenarioWithoutSteps, Scenario.class);
        Assert.assertEquals(0, scenario.getSteps().size());
    }

    @Test(expected = IOException.class)
    public void testParsingMalfromedJsonIntoObject() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.readValue(malformedScenario, Scenario.class);
    }

    @Test(expected = IOException.class)
    public void testParsingJsonWithoutHeaderFieldIntoObject() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.readValue(scenarioWithNullHeader, Scenario.class);
    }

    @Test(expected = IOException.class)
    public void testParsingJsonWithoutSystemActorsFieldIntoObject() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.readValue(scenarioWithNullSystemActors, Scenario.class);
    }

    @Test(expected = IOException.class)
    public void testParsingJsonWithoutActorsFieldIntoObject() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.readValue(scenarioWithNullActors, Scenario.class);
    }

}