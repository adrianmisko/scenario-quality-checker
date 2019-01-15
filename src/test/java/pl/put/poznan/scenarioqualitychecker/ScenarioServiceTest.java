package pl.put.poznan.scenarioqualitychecker;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import pl.put.poznan.scenarioqualitychecker.scenario.ScenarioRepository;
import pl.put.poznan.scenarioqualitychecker.scenario.ScenarioService;
import pl.put.poznan.scenarioqualitychecker.scenario.model.Scenario;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class ScenarioServiceTest {

    @Autowired
    @InjectMocks
    private ScenarioService service;

    @Mock
    private ScenarioRepository repository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAdd() {
        Scenario s = new Scenario();
        when(repository.save(any(Scenario.class))).thenReturn(s);
        service.createScenario(s);
        verify(repository).save(any(Scenario.class));
    }

}
