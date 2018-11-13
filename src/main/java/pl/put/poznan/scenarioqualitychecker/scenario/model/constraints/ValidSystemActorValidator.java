package pl.put.poznan.scenarioqualitychecker.scenario.model.constraints;

import pl.put.poznan.scenarioqualitychecker.scenario.model.Scenario;
import pl.put.poznan.scenarioqualitychecker.scenario.model.Step;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Class for checking system actors validation.
 * @author Robert Dudek
 */

public class ValidSystemActorValidator implements ConstraintValidator<ValidSystemActor, Scenario> {

    /**
     * Method for checking valid system actors.
     * @param scenario A scenario which you want to check for valid system actors.
     * @param constraintValidatorContext To fill up.
     * @return True for valid system actor or false.
     */

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

    /**
     * A constructor for class.
     */

    public ValidSystemActorValidator() {
        super();
    }
}
