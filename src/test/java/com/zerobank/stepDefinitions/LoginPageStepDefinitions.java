package com.zerobank.stepDefinitions;

import com.zerobank.pages.accountsummary.AccountSummaryPage;
import com.zerobank.pages.login.LoginPage;
import com.zerobank.utilities.ExecutionLogger;
import com.zerobank.utilities.StepData;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginPageStepDefinitions {
    LoginPage loginPage=new LoginPage();
    AccountSummaryPage accountSummaryPage=new AccountSummaryPage();
    private StepData stepData;

    public LoginPageStepDefinitions(StepData stepData) {
        this.stepData = stepData;
    }

    @Then("user navigates to the login page")
    public void userNavigatesToTheLoginPage() {
        System.out.println("User clicks sign in button and navigates to login page");
        ExecutionLogger.getLogger().info("User clicks sign in button and navigates to login page");
        stepData.data="Login info from login page step definitions";
        loginPage.clickSignin();
    }

    @When("user logs in with {string} and {string}")
    public void user_logs_in_with_and(String username, String password) {
        System.out.println("User is on the login page and enters "+username+" and "+password);
        ExecutionLogger.getLogger().info("User is on the login page and enters "+username+" and "+password);
        loginPage.login(username,password);
    }


    @Then("verifies that {string} page is displayed")
    public void verifiesThatPageIsDisplayed(String pageTitle) {
        System.out.println("User verifies that is on the "+pageTitle + " page.");
        ExecutionLogger.getLogger().info("User verifies that is on the "+pageTitle + " page.");

        Assert.assertEquals("user is not at the "+pageTitle,pageTitle,accountSummaryPage.getActivePageTitle());
    }


    @Then("verifies that warning message is displayed")
    public void verifiesThatWarningMessageIsDisplayed() {
        System.out.println("User verifies that warning message is displayed");
        ExecutionLogger.getLogger().info("User verifies that warning message is displayed");
        Assert.assertEquals("Warning message is not displayed","Login and/or password are wrong.",loginPage.getWarningMessage());

    }

    @Then("user logs in with default credentials")
    public void userLogsInWithDefaultCredentials() {
        loginPage.defaultLogin();
        System.out.println("User logs in to the application by default credentials");
        ExecutionLogger.getLogger().info("User logs in to the application by default credentials");
    }
}
