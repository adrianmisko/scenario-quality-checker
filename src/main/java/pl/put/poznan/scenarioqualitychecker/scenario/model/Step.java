package pl.put.poznan.scenarioqualitychecker.scenario.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * This class create a step for a scenario.
 * Each step contains keyword, actor, system actor, text and scenario.
 *
 * @author Adrian Miśko
 */

@Entity
public class Step {

    /**
     * Automatically generated value for identification.
     */

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * A string that can be one of three keywords: "IF", "ELSE" OR "FOR EACH".
     */

    @Getter
    @Setter
    private String keyword;

    /**
     * A string with information about actors.
     */

    @Getter
    @Setter
    @NotNull
    private String actor;

    /**
     * A string with information about system actors.
     */

    @Getter
    @Setter
    @NotNull
    @JsonProperty("system actor")
    private String systemActor;

    /**
     * A string that describes step.
     */

    @Getter
    @Setter
    @NotEmpty
    private String text;

    /**
     * A field that can contain subscenario for a step or null value.
     */

    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    private Scenario scenario;

    /**
     * A constructor for class.
     */
    public Step() {
        super();
    }
}
