import forms.MultiSelectPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ResourcesProperties;
import utils.WebDriverManager;

import java.util.Set;

public class MultiSelectTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = WebDriverManager.getInstance();
        driver.manage().window().maximize();
    }

    @Test
    public void multiSelectListTest() {
        MultiSelectPage multiSelectPage = new MultiSelectPage();

        Set<String> expectedOptions = multiSelectPage.selectRandomOptions(Integer.parseInt(ResourcesProperties.getDataProperty("quantity")));

        multiSelectPage.clickGetAllSelected();

        for (WebElement option : multiSelectPage.getAllSelected()) {
            Assertions.assertTrue(expectedOptions.contains(option.getAttribute("value")), "Wrong item was selected");
        }
    }

    @AfterEach
    public void closeUp() {
        driver.quit();
    }
}
