package com.zerobank.stepDefinitions;

import com.zerobank.pages.paybills.PayBillsPage;
import com.zerobank.utilities.BrowserUtilities;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class PayBillsPageStepDefinitions {
    PayBillsPage payBillsPage=new PayBillsPage();
    @Then("user selects payee: {string}")
    public void user_selects_payee(String payee) {
        System.out.println("User selects payee as: "+payee);
        payBillsPage.selectPayee(payee);
    }

    @Then("user selects account type: {string}")
    public void user_selects_account_type(String account) {
        System.out.println("User selects account type as: "+account);
       payBillsPage.selectAccountType(account);
    }

    @Then("user enters payment amount: {string}")
    public void user_enters_payment_amount(String amount) {
        System.out.println("User enters payment amount: "+amount);
        payBillsPage.setPaymentAmount(amount);
    }

    @Then("user enters payment date: {string}")
    public void user_enters_payment_date(String date) {
        System.out.println("User enters payment date: "+date);
      payBillsPage.setPaymentDate(date);
    }

    @Then("user enters payment description: {string}")
    public void user_enters_payment_description(String description) {
        System.out.println("User enters payment description: "+description);
        payBillsPage.setDescription(description);
    }

    @Then("user submit payment")
    public void user_submit_payment() {
        payBillsPage.submitPayment();
    }

    @Then("verifies successful submission message {string}")
    public void verifies_successful_submission_message(String expected) {
        Assert.assertEquals("Payment is not successfully submitted",expected,payBillsPage.getAlertMessage());
        System.out.println("User verifies successful submission message");
    }

    @Then("Verify you will get {string} message on amount")
    public void verifyYouWillGetMessageOnAmount(String expected) {
        Assert.assertEquals("Amount shouldn't be empty message is not displayed",expected,payBillsPage.getEmptyWarningForAmount());
    }

    @Then("Verify you will get {string} message on date field")
    public void verifyYouWillGetMessageOnDateField(String expected) {
        Assert.assertEquals("Date shouldn't be empty message is not displayed",expected,payBillsPage.getEmptyWarningForDate());

    }

    @And("verifies successful submission message {string} is not displayed")
    public void verifiesSuccessfulSubmissionMessageIsNotDisplayed(String unExpected) {
        Assert.assertNotEquals("Payment shouldn't be submitted by invalid values",unExpected,payBillsPage.getAlertMessage());

    }

    @Then("user creates new payee using following information")
    public void user_creates_new_payee_using_following_information(Map<String,String> map) {
        System.out.println("User enters new payee information :\n"+map);
        payBillsPage.entersNewPayeeName(map.get("Payee Name"));
        payBillsPage.entersNewPayeeAddress(map.get("Payee Address"));
        payBillsPage.entersNewPayeeAccount(map.get("Account"));
        payBillsPage.entersNewPayeeDetails(map.get("Payee details"));
        payBillsPage.clickAddNewPayeeButton();

    }

    @Then("following currencies should be available")
    public void followingCurrenciesShouldBeAvailable(List<String> expected){
        Assert.assertEquals("Currency list is not expected",expected,payBillsPage.getAllCurrencies());
        System.out.println("User verifies currency list is as expected");
    }

    @When("user tries to calculate cost without selecting a currency")
    public void userTriesToCalculateCostWithoutSelectingACurrency() {
        System.out.println("User clicks purchase button without filling required fields");
        payBillsPage.clickPurchaseCurrencyButton();
    }

    @Then("error message should be displayed")
    public void errorMessageShouldBeDisplayed() {
        Assert.assertTrue("Alert message is not displayed",
                payBillsPage.getJSAlertTextMessage().contains("Please, ensure that you have filled all the required fields "));
        System.out.println("User verifies that error message is displayed");
    }


    @Then("user enters amount {string}")
    public void userEntersAmount(String amount) {
        payBillsPage.entersCurrencyPurchaseAmount(amount);
    }

    @When("user tries to calculate cost without entering a value")
    public void userTriesToCalculateCostWithoutEnteringAValue() {
        System.out.println("User clicks purchase button without filling required fields");
        payBillsPage.clickPurchaseCurrencyButton();
    }

    @Then("user selects a currency {string}")
    public void userSelectsACurrency(String currencyName) {
        payBillsPage.selectCurrency(currencyName);
    }
}
