package com.zerobank.pages.accountactivity;

import com.zerobank.pages.BasePage;
import com.zerobank.utilities.BrowserUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AccountActivityPage extends BasePage {

    @FindBy(tagName = "th")
    List<WebElement> tableHeaders;
    @FindBy(id = "aa_description")
    WebElement descriptionField;

    @FindBy(id = "aa_fromDate")
    WebElement fromDateField;
    @FindBy(id = "aa_toDate")
    WebElement toDateField;
    @FindBy(tagName = "button")
    WebElement searchButton;
    @FindBy(xpath = "//div[@id='filtered_transactions_for_account']//td[1]")
    List<WebElement> allDates;
    @FindBy(xpath = "//div[@id='filtered_transactions_for_account']//td[2]")
    List<WebElement> allDescriptions;
    @FindBy(xpath = "//div[@id='filtered_transactions_for_account']//td[3]")
    List<WebElement> allDeposits;
    @FindBy(xpath = "//div[@id='filtered_transactions_for_account']//td[4]")
    List<WebElement> allWithdrawals;
    @FindBy(id = "aa_type")
    WebElement typeDropdown;

    public void enterFromDate(String date){
        wait.until(ExpectedConditions.elementToBeClickable(fromDateField));
        fromDateField.clear();
        fromDateField.sendKeys(date);
    }
    public void enterToDate(String date){
        wait.until(ExpectedConditions.elementToBeClickable(toDateField));
        toDateField.clear();
        toDateField.sendKeys(date);
    }
    public void enterDescription(String text){
        wait.until(ExpectedConditions.elementToBeClickable(descriptionField));
        descriptionField.clear();
        descriptionField.sendKeys(text);
    }
    public void clickSearch(){
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchButton.click();
    }

    public String getDefaultOption(){
        Select select=new Select(driver.findElement(By.id("aa_accountId")));
        return select.getFirstSelectedOption().getText();
    }

    public List<String> getAllOptions(){
        Select select=new Select(driver.findElement(By.id("aa_accountId")));
        return BrowserUtilities.getTextFromWebElements(select.getOptions());
    }

    public List<String> getTableHeaders(){
        wait.until(ExpectedConditions.visibilityOfAllElements(tableHeaders));
        return BrowserUtilities.getTextFromWebElements(tableHeaders);
    }



    public List<String> getAllDates(){
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@id='filtered_transactions_for_account']//td[1]")));
//        List<WebElement> allDates=driver.findElements(By.xpath("//div[@id='filtered_transactions_for_account']//td[1]"));
        return BrowserUtilities.getInnerHTMLFromWebElements(allDates);
    }

    public List<String> getAllDescriptions(){

        //wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOfAllElements(allDescriptions));
        BrowserUtilities.wait(1);
        return BrowserUtilities.getInnerHTMLFromWebElements(allDescriptions);
    }

    public List<String> getAllDeposits(){
        wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOfAllElements(allDeposits));
        return BrowserUtilities.getInnerHTMLFromWebElements(allDeposits);
    }
    public List<String> getAllWithdrawals(){
     //   List<WebElement> allWithdrawals=driver.findElements(By.xpath("//div[@id='filtered_transactions_for_account']//td[4]"));
        wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOfAllElements(allWithdrawals));
        return BrowserUtilities.getInnerHTMLFromWebElements(allWithdrawals);
    }

    public void selectTransactionType(String option){
        Select type=new Select(typeDropdown);
        wait.until(ExpectedConditions.visibilityOf(typeDropdown));
        type.selectByVisibleText(option);
    }

}
