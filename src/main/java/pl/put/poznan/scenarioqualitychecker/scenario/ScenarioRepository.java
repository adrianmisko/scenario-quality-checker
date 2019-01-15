package pl.put.poznan.scenarioqualitychecker.scenario;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.put.poznan.scenarioqualitychecker.scenario.model.Scenario;

/**
 *  This is a repository for application.
 */

@Repository
public interface ScenarioRepository extends CrudRepository<Scenario, Long> {}
