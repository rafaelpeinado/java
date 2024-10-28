package org.example.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/report.html", "json:target/report.json"},
        // monochrome = true,
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        // dryRun = false,
        // strict = true,
        features = "src/test/java/resources/features/alugar_filmes.feature",
        glue = "org.example.steps"
        // tags = "@esse"
)
public class Runner {

}
