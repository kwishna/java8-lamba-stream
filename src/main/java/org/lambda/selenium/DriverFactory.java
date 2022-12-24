package org.lambda.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class DriverFactory {
    private static final Supplier<WebDriver> GET_CHROME_DRIVER = () -> {
//        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(new ChromeOptions() {{
            setHeadless(true);
            addArguments("--start-maximized");
            setImplicitWaitTimeout(Duration.ofSeconds(10));
            setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
        }});
    };

    private static final Supplier<WebDriver> GET_FIREFOX_DRIVER = () -> {
        System.setProperty("webdriver.firefox.driver", "D:\\geckodriver.exe");
//        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver(new FirefoxOptions() {{
            setHeadless(true);
            addArguments("--start-maximized");
            setImplicitWaitTimeout(Duration.ofSeconds(10));
            setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
        }});
    };

    private static final Supplier<WebDriver> GET_EDGE_DRIVER = () -> {
//        System.setProperty("webdriver.edge.driver", "D:\\msedgedriver.exe");
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver(new EdgeOptions() {{
            setHeadless(true);
            addArguments("--start-maximized");
            setImplicitWaitTimeout(Duration.ofSeconds(10));
            setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
        }});
    };

    private static final Map<String, Supplier<WebDriver>> MAP = new HashMap<>() {{
        put("chrome", GET_CHROME_DRIVER);
        put("firefox", GET_FIREFOX_DRIVER);
        put("edge", GET_EDGE_DRIVER);
    }};

    public static WebDriver getDriver(String browser) {
        return MAP.get(browser).get();
    }
}
