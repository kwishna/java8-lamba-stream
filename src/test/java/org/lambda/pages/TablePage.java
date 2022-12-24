package org.lambda.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.function.Predicate;

public class TablePage {

    WebDriver driver;

    public TablePage(WebDriver driver) {
        this.driver = driver;
    }

    public void goTo() {
        this.driver.get("https://vins-udemy.s3.amazonaws.com/java/html/java8-stream-table-1.html");
    }

    public void selectRow(Predicate<List<WebElement>> predicate) {
        this.driver.findElements(By.tagName("tr"))
                .stream()
                .skip(1)
                .map(tRow -> tRow.findElements(By.tagName("td")))
                .filter(tdList -> tdList.size() == 4)
                .filter(predicate)
                .map(tdList -> tdList.get(3))
                .map(td -> td.findElement(By.tagName("input")))
                .forEach(WebElement::click);
    }
}
