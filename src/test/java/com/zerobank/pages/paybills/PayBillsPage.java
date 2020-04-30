package com.zerobank.pages.paybills;

import com.zerobank.pages.BasePage;
import com.zerobank.utilities.BrowserUtilities;
import com.zerobank.utilities.Driver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class PayBillsPage extends BasePage {

    @FindBy(id = "sp_amount")
    WebElement amountField;
    @FindBy(id = "sp_date")
    WebElement dateField;
    @FindBy(id = "sp_description")
    WebElement descriptionField;
    @FindBy(id = "pay_saved_payees")
    WebElement payButton;
    @FindBy(id = "alert_content")
    WebElement alertMessage;

    @FindBy(id = "np_new_payee_name")
    WebElement npPayeeNameField;
    @FindBy(id = "np_new_payee_address")
    WebElement npPayeeAddressField;
    @FindBy(id = "np_new_payee_account")
    WebElement npPayeeAccountField;
    @FindBy(id = "np_new_payee_details")
    WebElement npPayeeDetailsField;
    @FindBy(id = "add_new_payee")
    WebElement addNewPayeeButton;
    @FindBy(id = "pc_currency")
    WebElement currencyDropdown;

    @FindBy(id = "purchase_cash")
    WebElement purchaseCurrencyButton;
    @FindBy(id = "pc_amount")
    WebElement purchaseCurrencyAmountField;

    public String getJSAlertTextMessage(){
        Alert alert=driver.switchTo().alert();
        return alert.getText();
    }

    public void entersCurrencyPurchaseAmount(String amount){
        wait.until(ExpectedConditions.elementToBeClickable(purchaseCurrencyAmountField));
        purchaseCurrencyAmountField.sendKeys(amount);
    }

    public void clickPurchaseCurrencyButton(){
        wait.until(ExpectedConditions.elementToBeClickable(purchaseCurrencyButton));
        purchaseCurrencyButton.click();
    }

    public List<String> getAllCurrencies(){
        wait.until(ExpectedConditions.elementToBeClickable(currencyDropdown));
        Select currencySelect=new Select(currencyDropdown);
        List<WebElement> options=currencySelect.getOptions();
        options.remove(0);
        return BrowserUtilities.getTextFromWebElements(options);
    }

    public void selectCurrency(String name){
        wait.until(ExpectedConditions.elementToBeClickable(currencyDropdown));
        Select currencySelect=new Select(currencyDropdown);
        currencySelect.selectByVisibleText(name);
    }


    public void selectPayee(String option){
        wait.until(ExpectedConditions.elementToBeClickable(By.id("sp_payee")));
        Select payees=new Select(driver.findElement(By.id("sp_payee")));
        payees.selectByVisibleText(option);
    }
    public void selectAccountType(String option){
        wait.until(ExpectedConditions.elementToBeClickable(By.id("sp_account")));
        Select accounts=new Select(driver.findElement(By.id("sp_account")));
        accounts.selectByVisibleText(option);
    }

    public void  setPaymentAmount(String value){
        wait.until(ExpectedConditions.elementToBeClickable(amountField)).sendKeys(value);
    }

    public void setPaymentDate(String value){
        wait.until(ExpectedConditions.elementToBeClickable(dateField)).sendKeys(value);
    }

    public void setDescription(String value){
        wait.until(ExpectedConditions.elementToBeClickable(descriptionField)).sendKeys(value);
    }

    public void submitPayment(){
        wait.until(ExpectedConditions.elementToBeClickable(payButton)).click();
    }

    public String getAlertMessage(){
        wait.until(ExpectedConditions.visibilityOf(alertMessage));
        return alertMessage.getText();
    }

    public String getEmptyWarningForAmount(){
        return amountField.getAttribute("validationMessage");
    }

    public String getEmptyWarningForDate(){
        return dateField.getAttribute("validationMessage");
    }

    public void entersNewPayeeName(String name){
        wait.until(ExpectedConditions.elementToBeClickable(npPayeeNameField)).sendKeys(name);
    }

    public void entersNewPayeeAddress(String address){
        wait.until(ExpectedConditions.elementToBeClickable(npPayeeAddressField)).sendKeys(address);
    }
    public void entersNewPayeeAccount(String account){
        wait.until(ExpectedConditions.elementToBeClickable(npPayeeAccountField)).sendKeys(account);
    }
    public void entersNewPayeeDetails(String details){
        wait.until(ExpectedConditions.elementToBeClickable(npPayeeDetailsField)).sendKeys(details);
    }
    public void clickAddNewPayeeButton(){
        wait.until(ExpectedConditions.elementToBeClickable(addNewPayeeButton)).click();
    }

}
