package pl.put.poznan.scenarioqualitychecker;


import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.put.poznan.scenarioqualitychecker.scenario.model.Scenario;
import pl.put.poznan.scenarioqualitychecker.scenario.model.Step;
import pl.put.poznan.scenarioqualitychecker.scenario.visitors.ActorlessStepsGetter;

import java.util.ArrayList;


public class ActorlessStepsGetterTests {

    private static Scenario scenario;
    private static Scenario scenarioWithSteps;
    private static Scenario scenarioWithActors;
    private static Scenario scenarioWithActors2;
    private static Scenario subscenarioWithActors;

    @BeforeClass
    public static void setUp() {
        scenario = new Scenario();
        scenario.setSteps(new ArrayList<>());

        scenarioWithSteps = new Scenario();
        scenarioWithSteps.setSteps(new ArrayList<>());
        Step step = new Step();
        step.setActor("");
        scenarioWithSteps.getSteps().add(step);
        step = new Step();
        step.setActor("actor");
        scenarioWithSteps.getSteps().add(step);


        scenarioWithActors = new Scenario();
        scenarioWithActors.setSteps(new ArrayList<>());
        Step step2 = new Step();
        step2.setActor("actor1");
        scenarioWithActors.getSteps().add(step2);
        Step step3 = new Step();
        step3.setActor("actor2");
        scenarioWithActors.getSteps().add(step3);


        scenarioWithActors2 = new Scenario();
        scenarioWithActors2.setSteps(new ArrayList<>());
        Step step4 = new Step();
        step4.setActor("actor3");
        scenarioWithActors2.getSteps().add(step4);
        subscenarioWithActors = new Scenario();
        subscenarioWithActors.setSteps(new ArrayList<>());
        step4.setScenario(subscenarioWithActors);
        Step step5 = new Step();
        step5.setActor("");
        subscenarioWithActors.getSteps().add(step5);


    }


    @Test
    public void testEmptyList() {
        ActorlessStepsGetter asg = new ActorlessStepsGetter();
        scenario.accept(asg);
        Assert.assertNotNull(asg.getActorlessSteps());
        Assert.assertEquals(0, asg.getActorlessSteps().size());
    }

    @Test
    public void testResult() {

        ActorlessStepsGetter asg = new ActorlessStepsGetter();
        scenarioWithSteps.accept(asg);
        Assert.assertNotNull(asg.getActorlessSteps());
        Assert.assertEquals(1, asg.getActorlessSteps().size());
    }

    @Test
    public void testActorsInSteps() {

        ActorlessStepsGetter asg = new ActorlessStepsGetter();
        scenarioWithActors.accept(asg);
        Assert.assertNotNull(asg.getActorlessSteps());
        Assert.assertEquals(0, asg.getActorlessSteps().size());
    }

    @Test
    public void testActorsInSubscenario() {

        ActorlessStepsGetter asg = new ActorlessStepsGetter();
        scenarioWithActors2.accept(asg);
        Assert.assertNotNull(asg.getActorlessSteps());
        Assert.assertEquals(1, asg.getActorlessSteps().size());
    }

}
