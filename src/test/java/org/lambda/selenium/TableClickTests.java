package org.lambda.selenium;

import com.google.common.util.concurrent.Uninterruptibles;
import org.lambda.pages.TablePage;
import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

public class TableClickTests {
    Predicate<List<WebElement>> allMales = tRowList -> tRowList.get(1).getText().equalsIgnoreCase("male");
    Predicate<List<WebElement>> allFemales = tRowList -> tRowList.get(1).getText().equalsIgnoreCase("female");
    Predicate<List<WebElement>> allAU = tRowList -> tRowList.get(2).getText().equalsIgnoreCase("AU");
    private WebDriver driver;

    @BeforeTest
    public void setDriver() {
        this.driver = new ChromeDriver(new ChromeOptions() {{
            setHeadless(false);
            addArguments("--start-maximized");
            setImplicitWaitTimeout(Duration.ofSeconds(10));
            setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
        }});
    }

    @Test(dataProvider = "genders")
    public void tableClick(String gender) {
        this.driver.get("https://vins-udemy.s3.amazonaws.com/java/html/java8-stream-table.html");
        this.driver.findElements(By.tagName("tr"))
                .stream()
                .skip(1)
                .map(tRow -> tRow.findElements(By.tagName("td")))
                .filter(tRowList -> tRowList.get(1).getText().equalsIgnoreCase(gender))
                .map(tdList -> tdList.get(3))
                .map(td -> td.findElement(By.tagName("input")))
                .forEach(WebElement::click);

        Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
    }

    @DataProvider(name = "genders")
    public Object[][] testData() {
        return new Object[][]{{"male"}, {"female"}};
    }

    @Test(dataProvider = "predicate")
    public void tableClickFilter(Predicate<List<WebElement>> predicate) {
        TablePage page = new TablePage(this.driver);
        page.goTo();
        page.selectRow(predicate);
        Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
    }

    @DataProvider(name = "predicate")
    public Object[][] testData1() {
        return new Object[][]{{allMales}, {allFemales}, {allMales.or(allFemales)}, {allAU}, {allFemales.and(allAU)}};
    }

    @AfterTest
    public void quit() {
        this.driver.quit();
    }
}
