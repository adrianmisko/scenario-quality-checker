package pl.put.poznan.scenarioqualitychecker.scenario.scenarioModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Step {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Getter
    @Setter
    private String keyword;

    @Getter
    @Setter
    @NotEmpty
    private String actor;

    @Getter
    @Setter
    @NotEmpty
    @JsonProperty("system actor")
    private String systemActor;

    @Getter
    @Setter
    @NotEmpty
    private String text;

    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    private Scenario scenario;

}
