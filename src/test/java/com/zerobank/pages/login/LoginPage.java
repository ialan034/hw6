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
    WebElement userNameField;
    @FindBy(id = "user_password")
    WebElement passwordField;
    @FindBy(name = "submit")
    WebElement signin;
    @FindBy(className = "alert-error")
    WebElement warningMessage;

    public void login(String username, String password){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("user_login")));
        userNameField.sendKeys(username);
        passwordField.sendKeys(password);
        wait.until(ExpectedConditions.attributeContains(userNameField,"value",username));
        wait.until(ExpectedConditions.attributeContains(passwordField,"value",password));
        signin.click();
    }

    public void defaultLogin(){
        wait.until(ExpectedConditions.elementToBeClickable(userNameField));
        wait.until(ExpectedConditions.elementToBeClickable(passwordField));
        userNameField.sendKeys(ConfigurationReader.getProperty("username"));
        wait.until(ExpectedConditions.attributeContains(userNameField,"value",ConfigurationReader.getProperty("username")));
        passwordField.sendKeys(ConfigurationReader.getProperty("password"));
        wait.until(ExpectedConditions.attributeContains(passwordField,"value",ConfigurationReader.getProperty("password")));
        signin.click();
    }

    public String getWarningMessage(){
        wait.until(ExpectedConditions.visibilityOf(warningMessage));
        return warningMessage.getText().trim();
    }

}
