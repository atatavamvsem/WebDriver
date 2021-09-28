import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class MultiSelectTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void multiSelectListDemoTest(){
        driver.get("https://www.seleniumeasy.com/test/basic-select-dropdown-demo.html");

        Select objSelect = new Select(driver.findElement(By.id("multi-select")));

        List<WebElement> elementCount = objSelect.getOptions();
        System.out.println(elementCount.size());
    }

    @AfterEach
    public void closeUp() {
        driver.quit();
    }


}
