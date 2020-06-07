package com.zerobank.stepDefinitions;

import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import com.zerobank.utilities.ExecutionLogger;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    @Before
    public void setup(Scenario scenario) {
        System.out.println(":::: Starting test automation ::::");
        System.out.println("Browser type :: " + ConfigurationReader.getProperty("browser"));
        System.out.println("Environment :: " + ConfigurationReader.getProperty("url"));
        System.out.println("Test scenario :: " + scenario.getName());
        ExecutionLogger.getLogger().info(":::: Starting test automation ::::");
        ExecutionLogger.getLogger().info("Browser type :: " + ConfigurationReader.getProperty("browser"));
        ExecutionLogger.getLogger().info("Environment :: " + ConfigurationReader.getProperty("url"));
        ExecutionLogger.getLogger().info("Test scenario :: " + scenario.getName());

        Driver.getDriver().manage().window().maximize();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            ExecutionLogger.getLogger().info("Test scenario :: " + scenario.getName()+ " is failed !!!");
            TakesScreenshot takesScreenshot = (TakesScreenshot) Driver.getDriver();
            byte[] image = takesScreenshot.getScreenshotAs(OutputType.BYTES);
            scenario.embed(image, "image/png", scenario.getName());
        }
        ExecutionLogger.getLogger().info("Test scenario :: " + scenario.getName()+" is completed");
        Driver.closeDriver();
    }
}

