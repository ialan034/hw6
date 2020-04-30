package com.zerobank.stepDefinitions;

import com.zerobank.pages.onlinestatements.StatementsPage;
import com.zerobank.utilities.BrowserUtilities;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class StatementsStepDefinitions {
    StatementsPage statementsPage=new StatementsPage();


    @When("the user selects the Recent Statements Year {int}")
    public void the_user_selects_the_Recent_Statements_Year(int year) {
        System.out.println("User select "+year+" to see statements");
        statementsPage.selectStatementYear(year);
    }

    @Then("{int} statements should be displayed for that year")
    public void statements_should_be_displayed_for_that_year(int expected) {
        Assert.assertEquals("Statement count is not correct",statementsPage.getNumberOfStatements(),expected);
        System.out.println("User verifies statement count is as expected");
    }

    @When("the user clicks on statement {string}")
    public void theUserClicksOnStatement(String statement) {
        System.out.println("User clicks statement: "+statement);
        statementsPage.clickStatement(statement);
        BrowserUtilities.wait(15);
    }
}
