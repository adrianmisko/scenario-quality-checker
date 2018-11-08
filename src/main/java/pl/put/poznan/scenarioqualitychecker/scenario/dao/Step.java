package pl.put.poznan.scenarioqualitychecker.scenario.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
    @NotNull
    private String actor;

    @Getter
    @Setter
    @NotNull
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