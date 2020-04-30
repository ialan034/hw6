package com.zerobank.stepDefinitions;

import com.zerobank.pages.accountactivity.AccountActivityPage;
import com.zerobank.utilities.BrowserUtilities;
import com.zerobank.utilities.DateTimeUtilities;
import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class AccountActivityPageStepDefinitions {
    AccountActivityPage accountActivityPage = new AccountActivityPage();
    private List<String> allDates ;
    private List<LocalDate> localDateList;
    private List<String> ONLINE_descriptions ;
    private List<String> online_descriptions ;

    @Then("user navigates to the {string} page")
    public void userNavigatesToThePage(String page) {
        System.out.println("User navigates to the " + page + " page");
        accountActivityPage.navigateTo(page);
    }

    @Then("Verify that default option for account type is {string}")
    public void verifyThatDefaultOptionForAccountTypeIs(String expected) {
        System.out.println("User verifies that default account option is " + expected);
        Assert.assertEquals("Default option for account type is wrong", expected, accountActivityPage.getDefaultOption());
    }

    @Then("Verify that dropdown has following options")
    public void verifyThatDropdownHasFollowingOptions(List<String> expected) {
        System.out.println("User verifies that account dropdown has correct options");
        Assert.assertEquals("Account options are not correct", expected, accountActivityPage.getAllOptions());
    }

    @Then("Verify that Transactions table has following headers")
    public void verifyThatTransactionsTableHasFollowingHeaders(List<String> expected) {
        System.out.println("User verifies that transactions table has correct headers");
        Assert.assertEquals("Transactions table headers are not correct", expected, accountActivityPage.getTableHeaders());
    }

    @When("the user enters date range from {string} to {string}")
    public void theUserEntersDateRangeFromTo(String dateFrom, String dateTo) {
        System.out.println("User enters date range from " + dateFrom + " to " + dateTo);
        accountActivityPage.enterFromDate(dateFrom);
        accountActivityPage.enterToDate(dateTo);

    }

    @And("clicks search")
    public void clicksSearch() {
//        BrowserUtilities.wait(10);
        System.out.println("User clicks to search button");
        accountActivityPage.clickSearch();
        //BrowserUtilities.wait(3);
    }

    @Then("results table should only show transactions dates between {string} to {string}")
    public void resultsTableShouldOnlyShowTransactionsDatesBetweenTo(String dateFrom, String dateTo) {

        allDates = accountActivityPage.getAllDates();
      //  System.out.println("allDates = " + allDates);
        localDateList = DateTimeUtilities.convertToLocalDateObjects(allDates);
        LocalDate from = LocalDate.parse(dateFrom);
        LocalDate to = LocalDate.parse(dateTo);
        localDateList.sort(Comparator.naturalOrder());
//        System.out.println(localDateList);
        if (localDateList.size() > 0) {
            Assert.assertTrue("Actual dates are not in the range", localDateList.get(0).isEqual(from) || localDateList.get(0).isAfter(from));
            Assert.assertTrue("Actual dates are not in the range", localDateList.get(localDateList.size() - 1).isEqual(to) || localDateList.get(localDateList.size() - 1).isBefore(to));
        }
        System.out.println("User verifies dates are in the range");
    }

    @And("the results should be sorted by most recent date")
    public void theResultsShouldBeSortedByMostRecentDate(List<String> expected) {
       // System.out.println("expected = " + expected);
        localDateList.sort(Comparator.reverseOrder());
        allDates = DateTimeUtilities.convertToStringObjects(localDateList);
        Assert.assertEquals("Dates are not sorted as expected", expected, allDates);
        System.out.println("User verifies dates are sorted by most recent date");
    }

    @And("the results table should only not contain transactions dated {string}")
    public void theResultsTableShouldOnlyNotContainTransactionsDated(String date) {
        Assert.assertFalse("Out of range date is listed",allDates.contains(date));
        System.out.println("User verifies out of range date "+date+" is not listed");
    }

    @When("the user enters description {string}")
    public void theUserEntersDescription(String description) {
        System.out.println("User enters description: "+description);
        accountActivityPage.enterDescription(description);
    }

    @Then("results table should only show descriptions containing {string}")
    public void resultsTableShouldOnlyShowDescriptionsContaining(String description) {
        List<String> descriptions=accountActivityPage.getAllDescriptions();

        if(description.equals("ONLINE") && ONLINE_descriptions==null)
            ONLINE_descriptions=descriptions;

        for(String each:descriptions)
            Assert.assertTrue("description is not related with expected",each.contains(description));
        System.out.println("User verifies all descriptions are related with "+description);
    }

    @But("results table should not show descriptions containing {string}")
    public void resultsTableShouldNotShowDescriptionsContaining(String description) {
        List<String> descriptions=accountActivityPage.getAllDescriptions();
        for(String each:descriptions)
            Assert.assertFalse("description is related with unrelated search",each.contains(description));
        System.out.println("User verifies all descriptions are not related with "+description);
    }

    @Then("results table should show same descriptions with {string} search")
    public void resultsTableShouldShowSameDescriptionsWithSearch(String description) {
        List<String> descriptions=accountActivityPage.getAllDescriptions();
        if(description.equalsIgnoreCase("online") && online_descriptions==null)
            online_descriptions=descriptions;
        if(online_descriptions!=null && ONLINE_descriptions!=null){
            Assert.assertEquals("Search description is case sensitive",ONLINE_descriptions,online_descriptions);
        }
    }

    @Then("results table should show at least one result under Deposit")
    public void resultsTableShouldShowAtLeastOneResultUnderDeposit() {

        Assert.assertTrue("There isnt any deposit",accountActivityPage.getAllDeposits().size()>0);
        System.out.println("User verifies that at least one deposit is displayed");
    }

    @Then("results table should show at least one result under Withdrawal")
    public void resultsTableShouldShowAtLeastOneResultUnderWithdrawal() {
        Assert.assertTrue("There isnt any withdrawal",accountActivityPage.getAllWithdrawals().size()>0);
        System.out.println("User verifies that at least one withdrawal is displayed");
    }

    @When("user selects type {string}")
    public void userSelectsType(String type) {
        accountActivityPage.selectTransactionType(type);
        accountActivityPage.clickSearch();
        System.out.println("User selects "+type+" as transaction type");
    }

    @But("results table should show no result under {string}")
    public void resultsTableShouldShowNoResultUnder(String type) {
        if(type.equalsIgnoreCase("withdrawal")){
            Assert.assertFalse("There is a withdrawal",accountActivityPage.getAllWithdrawals().size()>0);
            System.out.println("User verifies that there isn't any withdrawal displayed");
        }
        if(type.equalsIgnoreCase("deposit")){
            Assert.assertFalse("There is a deposit",accountActivityPage.getAllDeposits().size()>0);
            System.out.println("User verifies that there isn't any deposit displayed");
        }
    }

    @And("user navigates to the {string} tab")
    public void userNavigatesToTheTab(String tabName) {
        System.out.println("user navigates to the " + tabName + " tab");
        accountActivityPage.navigateToTab(tabName);
    }
}
