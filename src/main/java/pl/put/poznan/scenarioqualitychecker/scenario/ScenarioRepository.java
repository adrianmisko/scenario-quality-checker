package pl.put.poznan.scenarioqualitychecker.scenario;

import org.springframework.data.repository.CrudRepository;
import pl.put.poznan.scenarioqualitychecker.scenario.dao.Scenario;

/**
 *  This is a repository for application.
 */

public interface ScenarioRepository extends CrudRepository<Scenario, Long> {}
