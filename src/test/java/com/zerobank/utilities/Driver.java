package com.zerobank.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;

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
    private static ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();

    private Driver() {
    }

    public static WebDriver getDriver() {
        if (driverPool.get() == null) {
            //System.setProperty("webdriver.chrome.silentOutput", "true"); //THIS will surpress all logs expect INFO

            String sauceUserName = "ialan";
            String sauceAccessKey = "c1fcc98c-86d1-4f72-bcdb-47d214582651";

            String browserParamFromEnv = System.getProperty("browser");
            String browser = browserParamFromEnv == null ? ConfigurationReader.getProperty("browser").toLowerCase() : browserParamFromEnv;
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
                    if (!System.getProperty("os.name").toLowerCase().contains("windows"))
                        throw new WebDriverException("Your OS doesn't support IE");
                    WebDriverManager.iedriver().setup();
                    driverPool.set(new InternetExplorerDriver());
                    break;
                case "safari":
                    if (!System.getProperty("os.name").toLowerCase().contains("mac"))
                        throw new WebDriverException("Your OS doesn't support Safari");
                    driverPool.set(new SafariDriver());
                    break;
                case "remote_chrome":
                    try {
                        ChromeOptions chromeOptions = new ChromeOptions();
                        chromeOptions.setCapability("platform", Platform.ANY);
                        URL url = new URL("https://ondemand.us-west-1.saucelabs.com:443");
                        //     URL url=new URL("http://localhost:4444/wd/hub");
                        driverPool.set(new RemoteWebDriver(url, chromeOptions));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "remote_firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    try {
//                        URL url=new URL("http://18.206.228.233:4444/wd/hub");
                        URL url = new URL("http://localhost:4444/wd/hub");
                        driverPool.set(new RemoteWebDriver(url, firefoxOptions));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "sauce_safari":

                    /**
                     * In this section, we will configure our test to run on some specific
                     * browser/os combination in Sauce Labs
                     */
                    DesiredCapabilities capabilities = new DesiredCapabilities();
                    //set your user name and access key to run tests in Sauce
                    capabilities.setCapability("username", sauceUserName);
                    //set your sauce labs access key
                    capabilities.setCapability("accessKey", sauceAccessKey);
                    //set browser to Safari
                    capabilities.setCapability("browserName", "Safari");
                    //set operating system to macOS version 10.13
                    capabilities.setCapability("platform", "macOS 10.13");
                    //set the browser version to 11.1
                    capabilities.setCapability("version", "11.1");
                    //set your test case name so that it shows up in Sauce Labs
                    try {
                        URL url = new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub");
                        driverPool.set(new RemoteWebDriver(url,capabilities));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "sauce_chrome":
                    DesiredCapabilities capabilities2 = new DesiredCapabilities();
                    capabilities2.setCapability("username", sauceUserName);
                    capabilities2.setCapability("accessKey", sauceAccessKey);
                    capabilities2.setCapability("browserName", "Chrome");
                    capabilities2.setCapability("platform", "Windows 10");
                    capabilities2.setCapability("version", "83");
                    capabilities2.setCapability("name", "Zerobank Tests");

                    try {
                        URL url = new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub");
                        driverPool.set(new RemoteWebDriver(url,capabilities2));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;

                case "sauce_ie":
                    MutableCapabilities sauceOptions = new MutableCapabilities();
                    sauceOptions.setCapability("username", sauceUserName);
                    sauceOptions.setCapability("accessKey", sauceAccessKey);

                    EdgeOptions browserOptions = new EdgeOptions();
                    browserOptions.setCapability("platformName", "Windows 10");
                    browserOptions.setCapability("browserVersion", "83.0");
                    browserOptions.setCapability("sauce:options", sauceOptions);
                    try {
                        URL url = new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub");
                        driverPool.set(new RemoteWebDriver(url,browserOptions));
                    } catch (MalformedURLException e) {
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
