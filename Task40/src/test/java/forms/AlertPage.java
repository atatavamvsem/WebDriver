package forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WebDriverManager;

public class AlertPage {
    private WebDriver driver;

    public static Element confirmBoxButton = new Element(By.xpath("//div[contains(text(),'Java Script Confirm Box')]/following::div//button[contains(text(),'Click me')]"));
    public static Element promptBoxButton = new Element(By.xpath("//button[contains(text(),'Click for Prompt Box')]"));
    public static Element confirmMessage = new Element(By.xpath("//p[@id='confirm-demo']"));
    public static Element promptMessage = new Element(By.xpath("//p[@id='prompt-demo']"));

    public AlertPage() {
        this.driver = WebDriverManager.getInstance();
        if (!"Selenium Easy Demo - Automate All Scenarios".equalsIgnoreCase(this.driver.getTitle())) {
            driver.get("https://www.seleniumeasy.com/test/javascript-alert-box-demo.html");
        }
    }

    public void clickConfirmBoxButton() {
        confirmBoxButton.click();
    }

    public String getTextConfirmBox(Element element) {
        return element.findElement().getText();
    }

    public void clickPromptBoxButton() {
        promptBoxButton.click();
    }
}
