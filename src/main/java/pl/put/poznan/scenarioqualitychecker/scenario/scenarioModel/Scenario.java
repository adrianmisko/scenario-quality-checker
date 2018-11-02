package pl.put.poznan.scenarioqualitychecker.scenario.scenarioModel;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.List;

@Entity
public class Scenario {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Setter
    @Getter
    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    private Header header;

    @Setter
    @Getter
    @Valid
    @OneToMany(cascade = CascadeType.ALL)
    private List<Step> steps;

    public Scenario() {
        super();
    }

}
