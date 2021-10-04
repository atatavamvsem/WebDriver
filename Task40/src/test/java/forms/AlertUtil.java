package forms;

import org.openqa.selenium.WebDriver;
import utils.WebDriverManager;

public class AlertUtil {
    private WebDriver driver;

    public AlertUtil() {
        this.driver = WebDriverManager.getInstance();
    }

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public void dismissAlert() {
        driver.switchTo().alert().dismiss();
    }

    public void sendKeys(String message) {
        driver.switchTo().alert().sendKeys(message);
    }
}
