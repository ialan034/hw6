package com.zerobank.pages;

import com.zerobank.pages.login.LoginPage;
import com.zerobank.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    protected WebDriver driver= Driver.getDriver();
    protected WebDriverWait wait=new WebDriverWait(driver,15);
    @FindBy(id = "signin_button")
    WebElement signinButton;
    @FindBy(xpath = "//li[@class='active']//a")
    WebElement activePage;

    public BasePage(){
        PageFactory.initElements(driver, this);
    }

    public LoginPage clickSignin(){
        wait.until(ExpectedConditions.elementToBeClickable(signinButton));
        signinButton.click();
        return new LoginPage();
    }

    public void navigateTo(String pageTitle){
        String pageXpath="//a[text()='"+pageTitle+"']";
        WebElement pageLink=driver.findElement(By.xpath(pageXpath));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pageXpath)));
        wait.until(ExpectedConditions.elementToBeClickable(pageLink)).click();
    }
    public void navigateToTab(String tabName){
        By xpath= By.xpath("//a[text()='"+tabName+"']");
        wait.until(ExpectedConditions.elementToBeClickable(xpath));
        driver.findElement(xpath).click();
    }

    public String getActivePageTitle(){
        return activePage.getText();
    }

    public String getPageTitle(){
        return driver.getTitle().trim();
    }
}
