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
        wait.until(ExpectedConditions.elementToBeClickable(userNameField));
        wait.until(ExpectedConditions.elementToBeClickable(passwordField));
        userNameField.sendKeys(username);
        passwordField.sendKeys(password);
        wait.until(ExpectedConditions.attributeContains(userNameField,"value",username));
        wait.until(ExpectedConditions.attributeContains(passwordField,"value",password));
        signin.click();
    }

    public void defaultLogin(){
        int attempts=0;
        boolean exceptionThrown=false;
        while(attempts<3) {
            exceptionThrown=false;
            try {
                userNameField.clear();
                passwordField.clear();
                wait.until(ExpectedConditions.elementToBeClickable(userNameField));
                wait.until(ExpectedConditions.elementToBeClickable(passwordField));
                userNameField.sendKeys(ConfigurationReader.getProperty("username"));
//                System.out.println(userNameField.getAttribute("value"));
                if(!userNameField.getAttribute("value").equals(ConfigurationReader.getProperty("username")))
                    throw new RuntimeException();
                passwordField.sendKeys(ConfigurationReader.getProperty("password"));
//                System.out.println(passwordField.getAttribute("value"));
                if(!passwordField.getAttribute("value").equals(ConfigurationReader.getProperty("password")))
                    throw new RuntimeException();
            }catch (Exception e){
                System.out.println("Trying to login again");
                exceptionThrown=true;
            }
            if(!exceptionThrown)
                break;
            else
                attempts++;
        }
        if(exceptionThrown)
            throw new RuntimeException("Login credentials are not correct");
        signin.click();
    }

    public String getWarningMessage(){
        wait.until(ExpectedConditions.visibilityOf(warningMessage));
        return warningMessage.getText().trim();
    }

}
