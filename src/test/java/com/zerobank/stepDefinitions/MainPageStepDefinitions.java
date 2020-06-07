package com.zerobank.stepDefinitions;

import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import com.zerobank.utilities.ExecutionLogger;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MainPageStepDefinitions {
    @Given("user is on the main page")
    public void userIsOnTheMainPage() {
        System.out.println("User is on the main page ");
        ExecutionLogger.getLogger().info("User is on the main page ");
        String URL= ConfigurationReader.getProperty("url");
        Driver.getDriver().get(URL);
    }



}
