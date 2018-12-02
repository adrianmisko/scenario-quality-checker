package pl.put.poznan.scenarioqualitychecker;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import pl.put.poznan.scenarioqualitychecker.scenario.model.Scenario;
import pl.put.poznan.scenarioqualitychecker.scenario.visitors.ActorlessStepsGetter;

import java.io.IOException;

public class ActorlessStepsGetterTests {


    private String scenarioWithoutActorlessSteps = "{\"header\":{\"title\":\"additem1\",\"actors\":[\"actor1\",\"actor2\",\"\"],\"system actors\":[\"sysactor1\",\"sysactor2\"]},\"steps\":[{\"keyword\":\"\",\"actor\":\"actor1\",\"system actor\":\"sysactor1\",\"text\":\"lorem40\",\"scenario\":null},{\"keyword\":\"IF\",\"actor\":\"actor1\",\"system actor\":\"sysactor2\",\"text\":\"lorem40\",\"scenario\":{\"header\":{\"title\":\"title2\",\"actors\":[\"actor222\"],\"system actors\":[\"sysactor222\"]},\"steps\":[{\"keyword\":\"FOREACH\",\"actor\":\"actor222\",\"system actor\":\"sysactor222\",\"text\":\"loremipsum\",\"scenario\":null},{\"keyword\":\"\",\"actor\":\"actor222\",\"system actor\":\"sysactor222\",\"text\":\"loremipsum2\",\"scenario\":null}]}},{\"keyword\":\"\",\"actor\":\"actor2\",\"system actor\":\"sysactor2\",\"text\":\"lorem40\",\"scenario\":null}]}";
    private String scenarioWithOneActorlessStep = "{\"header\":{\"title\":\"additem1\",\"actors\":[\"actor1\",\"actor2\",\"\",\"\"],\"system actors\":[\"sysactor1\",\"sysactor2\"]},\"steps\":[{\"keyword\":\"\",\"actor\":\"actor1\",\"system actor\":\"sysactor1\",\"text\":\"lorem40\",\"scenario\":null},{\"keyword\":\"IF\",\"actor\":\"actor1\",\"system actor\":\"sysactor2\",\"text\":\"lorem40\",\"scenario\":{\"header\":{\"title\":\"title2\",\"actors\":[\"actor222\"],\"system actors\":[\"sysactor222\"]},\"steps\":[{\"keyword\":\"FOREACH\",\"actor\":\"actor222\",\"system actor\":\"sysactor222\",\"text\":\"loremipsum\",\"scenario\":null},{\"keyword\":\"\",\"actor\":\"actor222\",\"system actor\":\"sysactor222\",\"text\":\"loremipsum2\",\"scenario\":null}]}},{\"keyword\":\"\",\"actor\":\"\",\"system actor\":\"sysactor2\",\"text\":\"lorem40\",\"scenario\":null}]}";


    @Test
    public void testEmptyList() throws IOException {
        ObjectMapper om = new ObjectMapper();
        Scenario s = om.readValue(scenarioWithoutActorlessSteps, Scenario.class);
        ActorlessStepsGetter asg = new ActorlessStepsGetter();
        s.accept(asg);
        Assert.assertNotNull(asg.getActorlessSteps());
        Assert.assertEquals(0, asg.getActorlessSteps().size());
    }

    @Test
    public void testResult() throws IOException {
        ObjectMapper om = new ObjectMapper();
        Scenario s = om.readValue(scenarioWithOneActorlessStep, Scenario.class);
        ActorlessStepsGetter asg = new ActorlessStepsGetter();
        s.accept(asg);
        Assert.assertEquals(1, asg.getActorlessSteps().size());
    }


}
