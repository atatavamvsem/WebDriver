package forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.WebDriverManager;

public class Element {
    public By locator;

    public Element(By locator) {
        this.locator = locator;
    }

    public void click() {
        findElement().click();
    }

    public WebElement findElement() {
        return WebDriverManager.getInstance().findElement(this.locator);
    }
}
