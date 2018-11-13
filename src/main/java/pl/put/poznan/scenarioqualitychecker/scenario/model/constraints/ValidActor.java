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
@Constraint(validatedBy = ValidActorValidator.class)
@Documented
public @interface ValidActor {

    String message() default "Actor has to be included in actors list in scenario header.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
