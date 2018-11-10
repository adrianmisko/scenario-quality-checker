package pl.put.poznan.scenarioqualitychecker.scenario.dao;

import lombok.Getter;
import lombok.Setter;
import pl.put.poznan.scenarioqualitychecker.scenario.dao.constraints.ValidActor;
import pl.put.poznan.scenarioqualitychecker.scenario.dao.constraints.ValidSystemActor;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.List;

/**
 * This class creates a new scenario.
 * It contains: an automatically generated id, a header and list of steps in that scenario.
 *
 * @author Adrian Miśko
 */

@Entity
@ValidActor
@ValidSystemActor
public class Scenario {

    /**
     * Automatically generated value for identification.
     */

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * It contains class header in an one-to-one relationship with scenario class.
     */


    @Setter
    @Getter
    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    private Header header;

    /**
     * It contains list of step for a scenario.
     */


    @Setter
    @Getter
    @Valid
    @OneToMany(cascade = CascadeType.ALL)
    private List<Step> steps;

    /**
     * A constructor for class.
     */

    public Scenario() {
        super();
    }

}
