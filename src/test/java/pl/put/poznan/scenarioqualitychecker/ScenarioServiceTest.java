package pl.put.poznan.scenarioqualitychecker;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.put.poznan.scenarioqualitychecker.scenario.ScenarioRepository;
import pl.put.poznan.scenarioqualitychecker.scenario.ScenarioService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScenarioServiceTest {

    @Autowired
    private ScenarioService service;

    @Mock
    private ScenarioRepository repository;


    @BeforeClass
    public static void setUp() {
        MockitoAnnotations.initMocks(ScenarioServiceTest.class);
    }

    @Test
    public void test() {
        Assert.assertEquals(1, 1);
    }

}
