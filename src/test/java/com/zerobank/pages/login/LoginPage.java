package com.zerobank.pages.login;

import com.zerobank.pages.BasePage;
import com.zerobank.pages.accountsummary.AccountSummaryPage;
import com.zerobank.utilities.ConfigurationReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    @FindBy(id = "user_login")
    WebElement loginField;
    @FindBy(id = "user_password")
    WebElement passwordField;
    @FindBy(name = "submit")
    WebElement signin;
    @FindBy(className = "alert-error")
    WebElement warningMessage;

    public AccountSummaryPage login(String username, String password){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("user_login")));
        loginField.sendKeys(username);
        passwordField.sendKeys(password);
        signin.click();
        return  new AccountSummaryPage();
    }

    public AccountSummaryPage defaultLogin(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("user_login")));
        loginField.sendKeys(ConfigurationReader.getProperty("username"));
        passwordField.sendKeys(ConfigurationReader.getProperty("password"));
        signin.click();
        return new AccountSummaryPage();
    }

    public String getWarningMessage(){
        wait.until(ExpectedConditions.visibilityOf(warningMessage));
        return warningMessage.getText().trim();
    }

}
