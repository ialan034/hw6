package com.zerobank.stepDefinitions;

import com.zerobank.pages.accountsummary.AccountSummaryPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.sql.SQLOutput;
import java.util.List;

public class AccountSummaryPageStepDefinitions {
    AccountSummaryPage accountSummaryPage=new AccountSummaryPage();
    @Then("user verifies that page title is {string}")
    public void userVerifiesThatPageTitleIs(String expectedTitle) {
        System.out.println("User verifies that page title is "+expectedTitle);
        Assert.assertEquals("Title is not correct",expectedTitle,accountSummaryPage.getPageTitle());
    }

    @Then("Verifies account types in the page")
    public void verifies_account_types_in_the_page(List<String> expected) {
        System.out.println("User verifies account types in Account Summary Page");
        Assert.assertEquals("Account types are not correct",expected,accountSummaryPage.getAccountTypes());
    }

    @Then("Verifies Credit Accounts Table headers")
    public void verifiesCreditAccountsTableHeaders(List<String> expected) {
        System.out.println("User verifies credit accounts table");
        Assert.assertEquals("Credit accounts table headers are not correct",expected,accountSummaryPage.getCreditAccountsHeaders());
    }


    @When("the user clicks {string} link on the Account Summary Page")
    public void theUserClicksLinkOnTheAccountSummaryPage(String accountType) {
        System.out.println("User navigates to the "+accountType+" account");
        accountSummaryPage.navigateToAccount(accountType);
    }
}
