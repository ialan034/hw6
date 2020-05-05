package com.zerobank.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com/zerobank/stepDefinitions",
        tags = "@statements_documents",
        dryRun = false,
        strict = true,
        plugin = {
                "html:target/default-report",
                "json:target/cucumber.json",
                "rerun:target/rerun.txt"
        }
)
public class CucumberRunner {
}
