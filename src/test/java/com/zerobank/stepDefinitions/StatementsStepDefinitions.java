package com.zerobank.stepDefinitions;

import com.zerobank.pages.onlinestatements.StatementsPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.io.File;

public class StatementsStepDefinitions {
    StatementsPage statementsPage = new StatementsPage();


    @When("the user selects the Recent Statements Year {int}")
    public void the_user_selects_the_Recent_Statements_Year(int year) {
        System.out.println("User select " + year + " to see statements");
        statementsPage.selectStatementYear(year);
    }

    @Then("{int} statements should be displayed for that year")
    public void statements_should_be_displayed_for_that_year(int expected) {
        Assert.assertEquals("Statement count is not correct", statementsPage.getNumberOfStatements(), expected);
        System.out.println("User verifies statement count is as expected");
    }

    @When("the user clicks on statement {string}")
    public void theUserClicksOnStatement(String statement) {
        System.out.println("User clicks statement: " + statement);
        statementsPage.clickStatement(statement);
    }

    @Then("the downloaded file name should contain {string} and the file type should be pdf")
    public void theDownloadedFileNameShouldContainAndTheFileTypeShouldBePdf(String name) {
        System.out.println("User at verify pdf step");
        String expectedFileName = statementsPage.getFullFileName(name);
        String filePath = "";
        if (System.getProperty("os.name").toLowerCase().contains("mac"))
            filePath = System.getProperty("user.home") + "/Downloads/" + expectedFileName;
        else
            filePath = System.getProperty("user.home") + "\\Downloads\\" + expectedFileName;

        System.out.println("filePath = " + filePath);
        File temp = new File(filePath);
        Assert.assertTrue("File is not downloaded", temp.exists());
        Assert.assertTrue("File doesn't contain " + name, temp.getName().contains(name));
        System.out.println("User verifies the statement containing " + name + " is downloaded");
        Assert.assertTrue("File type is not pdf", temp.getName().endsWith("pdf"));
        System.out.println("User verifies the statement file type is pdf ");

    }
}
