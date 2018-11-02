package pl.put.poznan.scenarioqualitychecker.scenario;

import org.springframework.data.repository.CrudRepository;
import pl.put.poznan.scenarioqualitychecker.scenario.scenarioModel.Scenario;

public interface ScenarioRepository extends CrudRepository<Scenario, Long> {}
