package org.lambda.selenium;

import org.lambda.functions.Rules;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class GoogleTests {

    private static final Path LOG_FILE = Path.of("./logs/logger.txt");
    private static final Consumer<String> OUT_LOGGER = System.out::println;
    private static final Consumer<String> ERROR_LOGGER = System.err::println;
    private static final Consumer<String> FILE_LOGGER = str -> {
        try {
            Files.writeString(LOG_FILE, str, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    };
    private static final Consumer<String> CHAINED_LOGGER = OUT_LOGGER.andThen(FILE_LOGGER);
    private WebDriver driver;

    @BeforeTest
    @Parameters(value = "browser")
    public void setDriver(@Optional(value = "chrome") String browser) {
        System.out.println("webdriver.enabled: " + System.getProperty("webdriver.enabled"));
        System.out.println("webdriver.enabled env: " + System.getenv("webdriver.enabled"));
        System.out.println("Browser: " + System.getProperty("browserName"));
        System.out.println("Browser env: " + System.getenv("browserName"));
        System.out.println("Browsers: " + System.getProperty("browsers"));
        System.out.println("Browser name: " + browser);
//        System.getProperties().forEach((_key, _val) -> {
//            try {
//                Files.writeString(logFile, _key + " -> " + _val + "\n", StandardOpenOption.APPEND);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });
        this.driver = DriverFactory.getDriver(browser);
    }

    @BeforeMethod
    public void beforeMethod() {
        this.driver.get("https://www.google.com");
        this.driver.findElement(By.name("q")).sendKeys("Free Taiwan");
        this.driver.findElement(By.name("btnK")).submit();
    }

    @Test
    public void googleTest() {

        List<WebElement> allLinks = this.driver.findElements(By.cssSelector("div.g a h3"));

        // allLinks.removeIf(isBlank.or(hasChina));
        Rules.filters().forEach(allLinks::removeIf);

        Function<WebElement, String> getText = WebElement::getText;
        allLinks.stream().map(getText).forEach(CHAINED_LOGGER);
    }

    @Test
    public void googleTest2() {

        Predicate<WebElement> isBlank = (_el) -> _el.getText().trim().length() == 0; // Predicate
        Predicate<WebElement> hasChina = (_el) -> _el.getText().toLowerCase().contains("china"); // Predicate

        this.driver.findElements(By.cssSelector("div.g a h3"))
                .stream()
                .filter(isBlank.negate())
                .filter(hasChina.negate())
                .map(WebElement::getText)
                .forEach(CHAINED_LOGGER);
    }

    @AfterTest
    public void quit() {
        this.driver.quit();
    }
}
