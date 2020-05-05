package com.zerobank.pages.onlinestatements;

import com.zerobank.pages.BasePage;
import com.zerobank.utilities.BrowserUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
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
        WebElement link=driver.findElement(By.xpath(xpath));
        wait.until(ExpectedConditions.elementToBeClickable(link)).click();
        String filePath = "";
        if (System.getProperty("os.name").toLowerCase().contains("mac"))
            filePath = System.getProperty("user.home") + "/Downloads/" + getDownloadedFileName(link);
        else
            filePath = System.getProperty("user.home") + "\\Downloads\\" + getDownloadedFileName(link);

        File temp=new File(filePath);
        int timeout=0;
        while(!temp.exists() && timeout<10){
            BrowserUtilities.wait(1);
            timeout++;
        }
        if(timeout==10)
            throw new RuntimeException("File is not downloaded");
    }

    public String getFullFileName(String name){
        String xpath="//a[contains(@href,'"+name+"')]";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        WebElement fileLink=driver.findElement(By.xpath(xpath));
        return getDownloadedFileName(fileLink);
    }
    private String getDownloadedFileName(WebElement link){
        String temp=link.getAttribute("href");
        return temp.substring(temp.indexOf("=")+1,temp.length());
    }

}
