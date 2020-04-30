package com.zerobank.stepDefinitions;

import com.zerobank.pages.accountsummary.AccountSummaryPage;
import com.zerobank.pages.login.LoginPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginPageStepDefinitions {
    LoginPage loginPage=new LoginPage();
    AccountSummaryPage accountSummaryPage=new AccountSummaryPage();

    @Then("user navigates to the login page")
    public void userNavigatesToTheLoginPage() {
        System.out.println("User clicks sign in button and navigates to login page");
        loginPage.clickSignin();
    }

    @When("user logs in with {string} and {string}")
    public void user_logs_in_with_and(String username, String password) {
        System.out.println("User is on the login page and enters "+username+" and "+password);
        loginPage.login(username,password);
    }


    @Then("verifies that {string} page is displayed")
    public void verifiesThatPageIsDisplayed(String pageTitle) {
        System.out.println("User verifies that is on the "+pageTitle + " page.");
        Assert.assertEquals("user is not at the "+pageTitle,pageTitle,accountSummaryPage.getActivePageTitle());
    }


    @Then("verifies that warning message is displayed")
    public void verifiesThatWarningMessageIsDisplayed() {
        System.out.println("User verifies that warning message is displayed");
        Assert.assertEquals("Warning message is not displayed","Login and/or password are wrong.",loginPage.getWarningMessage());

    }

    @Then("user logs in with default credentials")
    public void userLogsInWithDefaultCredentials() {
        System.out.println("User logs in to the application by default credentials");
        loginPage.defaultLogin();
    }
}
