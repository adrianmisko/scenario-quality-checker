package pl.put.poznan.scenarioqualitychecker.scenario.scenarioModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
public class Header {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Getter
    @Setter
    @NotEmpty
    @Column(unique = true)
    private String title;

    @Getter
    @Setter
    @ElementCollection(targetClass=String.class)
    @NotEmpty
    private List<String> actors;

    @Getter
    @Setter
    @ElementCollection(targetClass=String.class)
    @NotEmpty
    @JsonProperty("system actors")
    private List<String> systemActors;

}
