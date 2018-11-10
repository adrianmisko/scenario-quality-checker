package pl.put.poznan.scenarioqualitychecker.scenario.dao.constraints;

import pl.put.poznan.scenarioqualitychecker.scenario.dao.Scenario;
import pl.put.poznan.scenarioqualitychecker.scenario.dao.Step;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidSystemActorValidator implements ConstraintValidator<ValidSystemActor, Scenario> {

    @Override
    public boolean isValid(Scenario scenario, ConstraintValidatorContext constraintValidatorContext) {

        for (Step step : scenario.getSteps()
        ) {
            if ((!step.getSystemActor().equals("")) && (!scenario.getHeader().getSystemActors().contains(step.getSystemActor()))) {
                return false;
            }
        }
        return true;
    }
}
