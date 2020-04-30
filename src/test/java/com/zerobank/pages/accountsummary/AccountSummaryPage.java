package com.zerobank.pages.accountsummary;

import com.zerobank.pages.BasePage;
import com.zerobank.utilities.BrowserUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.swing.*;
import java.util.List;

public class AccountSummaryPage extends BasePage {
    @FindBy(tagName = "h2")
    List<WebElement> accountTypes;

    @FindBy(xpath = "(//h2[text()='Credit Accounts']//following-sibling::div//table)[1]//tr/th")
    List<WebElement> creditAccountsHeaders;

    public List<String> getAccountTypes(){
        wait.until(ExpectedConditions.visibilityOfAllElements(accountTypes));
        return BrowserUtilities.getTextFromWebElements(accountTypes);
    }

    public List<String > getCreditAccountsHeaders(){
        wait.until(ExpectedConditions.visibilityOfAllElements(creditAccountsHeaders));
        return BrowserUtilities.getTextFromWebElements(creditAccountsHeaders);
    }
    public void navigateToAccount(String accountName){
        String xpath="//a[text()='"+accountName+"']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        driver.findElement(By.xpath(xpath)).click();
    }
}
