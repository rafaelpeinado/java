package org.example.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = "pretty",
        // monochrome = true,
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        // dryRun = false,
        // strict = true,
        features = "src/test/java/resources/features",
        glue = "org.example.steps"
)
public class Runner {

}
