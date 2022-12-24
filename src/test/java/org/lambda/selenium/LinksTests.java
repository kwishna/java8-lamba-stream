package org.lambda.selenium;

import org.lambda.utils.LinkUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

public class LinksTests {
    private WebDriver driver;

    @BeforeTest
    public void setDriver() {
        this.driver = new ChromeDriver(new ChromeOptions() {{
            setHeadless(true);
            addArguments("--start-maximized");
            setImplicitWaitTimeout(Duration.ofSeconds(10));
            setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
        }});
    }

    @Test
    public void brokenLinks() {
        this.driver.get("https://the-internet.herokuapp.com/broken_images");
        this.driver.findElements(By.xpath("//*[@src]")).stream()
                .map(_el -> _el.getAttribute("src"))
                .forEach(_url -> {
                    System.out.format("%s -> %d\n", _url, LinkUtils.getResponseCode(_url));
                });
    }

    @Test
    public void anyBrokenLinks() {
        this.driver.get("https://the-internet.herokuapp.com/broken_images");
        boolean anyMatch = this.driver.findElements(By.xpath("//*[@src]")).stream()
                .map(_el -> _el.getAttribute("src"))
                .map(LinkUtils::getResponseCode)
                .anyMatch(_code -> _code != 200);
        Assert.assertTrue(anyMatch, "any match is false");
    }

    @Test
    public void listOfBrokenLinks() {
        this.driver.get("https://the-internet.herokuapp.com/broken_images");
        List<String> all200Links = this.driver.findElements(By.xpath("//*[@src]")).stream()
                .map(_el -> _el.getAttribute("src"))
                .filter(_src -> LinkUtils.getResponseCode(_src) != 200)
                .toList();
        Assert.assertEquals(all200Links, List.of("https://the-internet.herokuapp.com/asdf.jpg", "https://the-internet.herokuapp.com/hjkl.jpg"));
    }

    @Test
    public void nonParallel() {
        this.driver.get("https://the-internet.herokuapp.com/broken_images");
        LocalTime lt1 = LocalTime.now();
        System.out.println("Before --> " + lt1);
        List<String> all200Links = this.driver.findElements(By.xpath("//*[@src]")).stream()
                .map(_el -> _el.getAttribute("src"))
                .filter(_src -> LinkUtils.getResponseCode(_src) != 200)
                .toList();
        LocalTime lt2 = LocalTime.now();
        System.out.println("After --> " + lt2);
        System.out.println("Time Taken --> " + Duration.between(lt2, lt1));
        Assert.assertEquals(all200Links, List.of("https://the-internet.herokuapp.com/asdf.jpg", "https://the-internet.herokuapp.com/hjkl.jpg"));
    }

    @Test
    public void parallel() {
        this.driver.get("https://the-internet.herokuapp.com/broken_images");
        LocalTime lt1 = LocalTime.now();
        System.out.println("Before --> " + lt1);
        List<String> all200Links = this.driver.findElements(By.xpath("//*[@src]")).stream()
                .parallel()
                .map(_el -> _el.getAttribute("src"))
                .filter(_src -> LinkUtils.getResponseCode(_src) != 200)
                .toList();
        LocalTime lt2 = LocalTime.now();
        System.out.println("After --> " + lt2);
        System.out.println("Time Taken --> " + Duration.between(lt2, lt1));
        Assert.assertEquals(all200Links, List.of("https://the-internet.herokuapp.com/asdf.jpg", "https://the-internet.herokuapp.com/hjkl.jpg"));
    }

    @AfterTest
    public void quit() {
        this.driver.quit();
    }
}
