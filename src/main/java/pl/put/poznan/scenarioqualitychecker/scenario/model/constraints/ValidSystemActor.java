package pl.put.poznan.scenarioqualitychecker.scenario.model.constraints;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Target( { TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = ValidSystemActorValidator.class)
@Documented
public @interface ValidSystemActor {


    String message() default "System actor has to be included in system actors list in scenario header.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
