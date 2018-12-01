package pl.put.poznan.scenarioqualitychecker.scenario.model.constraints;

import lombok.extern.slf4j.Slf4j;
import pl.put.poznan.scenarioqualitychecker.scenario.model.Scenario;
import pl.put.poznan.scenarioqualitychecker.scenario.model.Step;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Class for checking actors validation.
 * @author Robert Dudek
 */

@Slf4j
public class ValidActorValidator implements ConstraintValidator<ValidActor, Scenario> {

    /**
     * Method for checking valid system actors.
     * @param scenario A scenario which you want to check for valid actors.
     * @param constraintValidatorContext To fill up.
     * @return True for valid actor or false.
     */

    @Override
    public boolean isValid(Scenario scenario, ConstraintValidatorContext constraintValidatorContext) {

        for (Step step : scenario.getSteps()
        ) {
            if ((!step.getActor().equals("")) && (!scenario.getHeader().getActors().contains(step.getActor()))) {
                log.error("Error on validating actor");
                return false;
            }
        }
        return true;
    }


    /**
     * A constructor for class.
     */

    public ValidActorValidator() {
        super();
    }
}