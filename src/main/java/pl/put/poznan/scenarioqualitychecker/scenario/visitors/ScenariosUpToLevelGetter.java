package pl.put.poznan.scenarioqualitychecker.scenario.visitors;

import lombok.Getter;
import lombok.Setter;
import pl.put.poznan.scenarioqualitychecker.scenario.Visitor;
import pl.put.poznan.scenarioqualitychecker.scenario.model.Scenario;
import pl.put.poznan.scenarioqualitychecker.scenario.model.Step;

import java.util.ArrayList;
import java.util.List;

public class ScenariosUpToLevelGetter implements Visitor {


    @Getter
    @Setter
    private Scenario scenariosUpToLevel;
    private int level;
    private int maxLevel;
    private int numOfSteps;
    private List<Integer> stackOfSteps;

    public ScenariosUpToLevelGetter(int l) {
        level = 0;
        maxLevel = l;
        numOfSteps = 0;
        stackOfSteps = new ArrayList<>();
        scenariosUpToLevel = new Scenario();
    }

    @Override
    public void visit(Step s) {
        numOfSteps++;
        if(level == maxLevel) {
            List<Step> steps = scenariosUpToLevel.getSteps();
            for(int i=1;i<stackOfSteps.size();i++) {
                steps = steps.get(stackOfSteps.get(i)-1).getScenario().getSteps();
            }
            for(Step localStep : steps) {
                localStep.setScenario(null);
            }
        }
    }

    @Override
    public void visit(Scenario scenario) {
        if(level == 0 && maxLevel!=0) {
            scenariosUpToLevel.setId(scenario.getId());
            scenariosUpToLevel.setSteps(scenario.getSteps());
            scenariosUpToLevel.setHeader(scenario.getHeader());
        }

        stackOfSteps.add(numOfSteps);
        numOfSteps = 0;
        level++;
    }

    @Override
    public void afterVisit(Step step) {}

    @Override
    public void afterVisit(Scenario scenario) {
        numOfSteps = stackOfSteps.remove(stackOfSteps.size()-1);
        level--;
    }


}