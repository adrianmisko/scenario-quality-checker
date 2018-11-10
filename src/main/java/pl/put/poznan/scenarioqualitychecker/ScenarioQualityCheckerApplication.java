package pl.put.poznan.scenarioqualitychecker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Class to run spring application.
 */

@SpringBootApplication

public class ScenarioQualityCheckerApplication {

    /**
     * Method to run Spring application.
     * @param args Command-line arguments as an array of string objects.
     */

    public static void main(String[] args) {
        SpringApplication.run(ScenarioQualityCheckerApplication.class, args);
    }

    /**
     * A constructor for class.
     */

    public ScenarioQualityCheckerApplication() {
        super();
    }
}
