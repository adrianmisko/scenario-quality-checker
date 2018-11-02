package pl.put.poznan.scenarioqualitychecker.scenario;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.put.poznan.scenarioqualitychecker.scenario.dao.Scenario;

import java.util.List;

@Service
public class ScenarioService {

    @Autowired ScenarioRepository repository;

    public void createScenario(Scenario scenario) {
        repository.save(scenario);
    }

    public List<Scenario> getAllScenarios() {
        return Lists.newArrayList(repository.findAll());
    }

}
