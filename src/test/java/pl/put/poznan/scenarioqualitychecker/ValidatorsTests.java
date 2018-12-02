package pl.put.poznan.scenarioqualitychecker;


import java.io.IOException;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.put.poznan.scenarioqualitychecker.scenario.model.Scenario;

public class ValidatorsTests {


    private String correctScenario = "{\"header\":{\"title\":\"additem1\",\"actors\":[\"actor1\",\"actor2\",\"\"],\"system actors\":[\"sysactor1\",\"sysactor2\"]},\"steps\":[{\"keyword\":\"\",\"actor\":\"actor1\",\"system actor\":\"sysactor1\",\"text\":\"lorem40\",\"scenario\":null},{\"keyword\":\"IF\",\"actor\":\"actor1\",\"system actor\":\"sysactor2\",\"text\":\"lorem40\",\"scenario\":{\"header\":{\"title\":\"title2\",\"actors\":[\"actor222\"],\"system actors\":[\"sysactor222\"]},\"steps\":[{\"keyword\":\"FOREACH\",\"actor\":\"actor222\",\"system actor\":\"sysactor222\",\"text\":\"loremipsum\",\"scenario\":null},{\"keyword\":\"\",\"actor\":\"actor222\",\"system actor\":\"sysactor222\",\"text\":\"loremipsum2\",\"scenario\":null}]}},{\"keyword\":\"\",\"actor\":\"actor2\",\"system actor\":\"sysactor2\",\"text\":\"lorem40\",\"scenario\":null}]}";
    private String scenarioWhereActorIsntListedInHeader = "{\"header\":{\"title\":\"additem1\",\"actors\":[\"actor1\",\"actor2\",\"\"],\"system actors\":[\"sysactor1\",\"sysactor2\"]},\"steps\":[{\"keyword\":\"\",\"actor\":\"a\",\"system actor\":\"sysactor1\",\"text\":\"lorem40\",\"scenario\":null},{\"keyword\":\"IF\",\"actor\":\"actor1\",\"system actor\":\"sysactor2\",\"text\":\"lorem40\",\"scenario\":{\"header\":{\"title\":\"title2\",\"actors\":[\"actor222\"],\"system actors\":[\"sysactor222\"]},\"steps\":[{\"keyword\":\"FOREACH\",\"actor\":\"actor222\",\"system actor\":\"sysactor222\",\"text\":\"loremipsum\",\"scenario\":null},{\"keyword\":\"\",\"actor\":\"actor222\",\"system actor\":\"sysactor222\",\"text\":\"loremipsum2\",\"scenario\":null}]}},{\"keyword\":\"\",\"actor\":\"actor2\",\"system actor\":\"sysactor2\",\"text\":\"lorem40\",\"scenario\":null}]}";
    private String scenarioWhereSystemActorIsntListedInHeader = "{\"header\":{\"title\":\"additem1\",\"actors\":[\"actor1\",\"actor2\",\"\"],\"system actors\":[\"sysactor1\",\"sysactor2\"]},\"steps\":[{\"keyword\":\"\",\"actor\":\"actor1\",\"system actor\":\"g\",\"text\":\"lorem40\",\"scenario\":null},{\"keyword\":\"IF\",\"actor\":\"actor1\",\"system actor\":\"sysactor2\",\"text\":\"lorem40\",\"scenario\":{\"header\":{\"title\":\"title2\",\"actors\":[\"actor222\"],\"system actors\":[\"sysactor222\"]},\"steps\":[{\"keyword\":\"FOREACH\",\"actor\":\"actor222\",\"system actor\":\"sysactor222\",\"text\":\"loremipsum\",\"scenario\":null},{\"keyword\":\"\",\"actor\":\"actor222\",\"system actor\":\"sysactor222\",\"text\":\"loremipsum2\",\"scenario\":null}]}},{\"keyword\":\"\",\"actor\":\"actor2\",\"system actor\":\"sysactor2\",\"text\":\"lorem40\",\"scenario\":null}]}";
    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testScenarioValidatorsWithCorrectData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Scenario scenario =  objectMapper.readValue(correctScenario, Scenario.class);
        Set<ConstraintViolation<Scenario>> violations = validator.validate(scenario);
        Assert.assertTrue(violations.isEmpty());
    }

    @Test
    public void testScenarioValidatorsWithWrongActor() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Scenario scenario =  objectMapper.readValue(scenarioWhereSystemActorIsntListedInHeader, Scenario.class);
        Set<ConstraintViolation<Scenario>> violations = validator.validate(scenario);
        Assert.assertFalse(violations.isEmpty());
    }

    @Test
    public void testScenarioValidatorsWithWrongSystemActor() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Scenario scenario =  objectMapper.readValue(scenarioWhereActorIsntListedInHeader, Scenario.class);
        Set<ConstraintViolation<Scenario>> violations = validator.validate(scenario);
        Assert.assertFalse(violations.isEmpty());
    }

}