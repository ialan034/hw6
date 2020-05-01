package com.zerobank.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "@target/rerun.txt",
        glue = "com/zerobank/stepDefinitions",
        plugin= {
                "html:target/failed-default-reports",
                "json:target/cucumber_failed_report.json"
        }
)
public class FailedRunner {
}
