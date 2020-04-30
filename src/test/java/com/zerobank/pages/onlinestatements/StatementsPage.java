package com.zerobank.pages.onlinestatements;

import com.zerobank.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class StatementsPage extends BasePage {



    @FindBy(xpath = "//div[@class='tab-pane active']//tbody/tr")
    List<WebElement> statements;

    public void selectStatementYear(int year){
        String xpath="//a[text()='"+year+"']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
    }

    public int getNumberOfStatements(){
        wait.until(ExpectedConditions.visibilityOfAllElements(statements));
        return statements.size();
    }

    public void clickStatement(String statement){
        String xpath="//a[text()='"+statement+"']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
    }

}
