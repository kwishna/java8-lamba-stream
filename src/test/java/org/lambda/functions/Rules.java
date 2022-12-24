package org.lambda.functions;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Rules {
    static final Predicate<WebElement> IS_BLANK = (_el) -> _el.getText().trim().length() == 0;
    static final Predicate<WebElement> HAS_CHINA = (_el) -> _el.getText().toLowerCase().contains("china");

    public static List<Predicate<WebElement>> filters() {
        return new ArrayList<>() {{
            add(IS_BLANK);
            add(HAS_CHINA);
        }};
    }
}
