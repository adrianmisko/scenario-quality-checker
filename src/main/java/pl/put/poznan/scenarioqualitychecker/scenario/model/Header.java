package pl.put.poznan.scenarioqualitychecker.scenario.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * This class create a header for a scenario.
 * A header contains unique title, list of actors and system actors.
 *
 * @author Adrian Miśko
 */

@Entity
public class Header {

    /**
     * Automatically generated value for identification.
     */

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * An unique string of a title.
     */

    @Getter
    @Setter
    @NotEmpty
    @Column(unique = true)
    private String title;

    /**
     * A list of strings of actors.
     */

    @Getter
    @Setter
    @ElementCollection(targetClass=String.class)
    @NotEmpty
    private List<String> actors;

    /**
     * A list of strings of system actors.
     */

    @Getter
    @Setter
    @ElementCollection(targetClass=String.class)
    @NotEmpty
    @JsonProperty("system actors")
    private List<String> systemActors;

    /**
     * A constructor for class.
     */

    public Header() {
        super();
    }
}
