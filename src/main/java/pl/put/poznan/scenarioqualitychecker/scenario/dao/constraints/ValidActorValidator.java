package pl.put.poznan.scenarioqualitychecker.scenario.dao.constraints;

import lombok.extern.slf4j.Slf4j;
import pl.put.poznan.scenarioqualitychecker.scenario.dao.Scenario;
import pl.put.poznan.scenarioqualitychecker.scenario.dao.Step;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class ValidActorValidator implements ConstraintValidator<ValidActor, Scenario> {

    @Override
    public boolean isValid(Scenario scenario, ConstraintValidatorContext constraintValidatorContext) {

        for (Step step : scenario.getSteps()
        ) {
            if ((!step.getActor().equals("")) && (!scenario.getHeader().getActors().contains(step.getActor()))) {
                return false;
            }
        }
        return true;
    }
}