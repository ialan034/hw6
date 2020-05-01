package com.zerobank.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.URL;
import java.util.HashMap;

/**
 * Design pattern - is a general reusable solution to a commonly occurring problem with a given context in software design.
 * Patterns are formalized best practices that programmer can use to solve some common software design problems
 * There are lots of design patterns and 3 categories:
 * Creational, Structural, Behavioral.
 * One of the most simplest creational design pattern is a Singleton.
 * Singleton - means single instance/object for entire project.
 * Rules:
 * - private static instance
 * - private constructor
 * - public getter method, that returns instance already initialized.
 * <p>
 * Singleton pattern, every single test will use same webdriver object
 */

public class Driver {
    private static WebDriver driver;
    private static ThreadLocal<WebDriver> driverPool=new ThreadLocal<>();
    private Driver() {
    }
    public static WebDriver getDriver() {
        if (driverPool.get() == null) {
            //System.setProperty("webdriver.chrome.silentOutput", "true"); //THIS will surpress all logs expect INFO

            String browserParamFromEnv= System.getProperty("browser");
            String browser = browserParamFromEnv==null?ConfigurationReader.getProperty("browser").toLowerCase():browserParamFromEnv;
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    System.setProperty("webdriver.chrome.silentOutput", "true"); //THIS will surpress all logs expect INFO
                    // Headless mode make executions faster it does everything except file uploading
                    //WebDriverManager.chromedriver().version("79.0").setup();
                    //System.setProperty("webdriver.chrome.silentOutput", "true"); //THIS will surpress all logs expect INFO
                    driverPool.set(new ChromeDriver());
                    break;
                case "chromeheadless":
                    System.setProperty("webdriver.chrome.silentOutput", "true"); //THIS will surpress all logs expect INFO
                    //to run chrome without interface
                    System.setProperty("webdriver.chrome.silentOutput", "true"); //THIS will surpress all logs expect INFO
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions optionsHeadless = new ChromeOptions();
                    optionsHeadless.setHeadless(true);      //to run browser without interface
                    //      options.addArguments("--start-maximized");
                    driverPool.set(new ChromeDriver(optionsHeadless));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                    break;
                case "firefoxheadless":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver(new FirefoxOptions().setHeadless(true)));
                case "ie":
                    if(!System.getProperty("os.name").toLowerCase().contains("windows"))
                        throw new WebDriverException("Your OS doesn't support IE");
                    WebDriverManager.iedriver().setup();
                    driverPool.set(new InternetExplorerDriver());
                    break;
                case "safari":
                    if(!System.getProperty("os.name").toLowerCase().contains("mac"))
                        throw new WebDriverException("Your OS doesn't support Safari");
                    driverPool.set(new SafariDriver());
                    break;
                case "remote_chrome":
                    try {
                        ChromeOptions chromeOptions = new ChromeOptions();
                        chromeOptions.setCapability("platform", Platform.ANY);
                        driverPool.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptions));
                    }catch (Exception e){
                        e.printStackTrace();

                    }
                    break;
                default:
                    throw new RuntimeException("Wrong browser name!!!");
            }
        }
        return driverPool.get();
    }

    public static void closeDriver() {
        driverPool.get().quit();
        driverPool.remove();
    }
}
