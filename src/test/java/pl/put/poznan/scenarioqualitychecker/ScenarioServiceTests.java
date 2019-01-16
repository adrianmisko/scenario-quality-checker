package pl.put.poznan.scenarioqualitychecker;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import pl.put.poznan.scenarioqualitychecker.scenario.ScenarioRepository;
import pl.put.poznan.scenarioqualitychecker.scenario.ScenarioService;
import pl.put.poznan.scenarioqualitychecker.scenario.model.Scenario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;


public class ScenarioServiceTests {

    @Autowired
    @InjectMocks
    private ScenarioService service;

    @Mock
    private ScenarioRepository repositoryMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAdd() {
        Scenario s = new Scenario();
        when(repositoryMock.save(any(Scenario.class))).thenReturn(s);
        service.createScenario(s);
        verify(repositoryMock).save(any(Scenario.class));
    }

    @Test
    public void testGetScenario() {
        Scenario s = new Scenario();
        doReturn(Optional.of(s)).when(repositoryMock).findById(anyLong());
        service.getScenario(1l);
        verify(repositoryMock).findById(anyLong());
    }

    @Test(expected = Exception.class)
    public void testGetNullScenario() {
        doReturn(new Exception()).when(repositoryMock).findById(anyLong());
        service.getScenario(1l);
        verify(repositoryMock).findById(anyLong());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGetScenarioException() {
        when(repositoryMock.findById(anyLong())).thenThrow(new ResourceNotFoundException());
        service.getScenario(1l);
    }

    @Test
    public void testGetAllScenarios() {
        Scenario s = new Scenario();
        Scenario s2 = new Scenario();
        List<Scenario> listScenarios = new ArrayList<>();
        listScenarios.add(s);
        listScenarios.add(s2);
        when(repositoryMock.findAll()).thenReturn(listScenarios);
        Assert.assertEquals(service.getAllScenarios().size(), listScenarios.size());
    }

    @Test
    public void testGetAllEmptyScenarios() {
        when(repositoryMock.findAll()).thenReturn(Lists.newArrayList());
        service.getAllScenarios();
        verify(repositoryMock).findAll();
    }

}
