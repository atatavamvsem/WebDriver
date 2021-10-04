package forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.GenerateRandomUtil;
import utils.WebDriverManager;

import java.util.HashSet;
import java.util.List;

public class MultiSelectPage {
    private WebDriver driver;

    public MultiSelectPage() {
        this.driver = WebDriverManager.getInstance();
        if (!"Selenium Easy Demo - Automate All Scenarios".equalsIgnoreCase(this.driver.getTitle())) {
            driver.get("https://www.seleniumeasy.com/test/basic-select-dropdown-demo.html");
        }
    }

    public HashSet<String> selectRandomOptions(int quantity) {
        Select objSelect = new Select(driver.findElement(By.id("multi-select")));
        HashSet<String> optionsValue = new HashSet<>();

        List<WebElement> elementCount = objSelect.getOptions();
        int[] random = GenerateRandomUtil.getArrayWithRandomInt(elementCount.size(), quantity);

        for (int i = random.length; i > 0; i--) {
            optionsValue.add(driver.findElement(By.xpath(String.format("//select[@id='multi-select']/option[%d]", random[i - 1] + 1))).getAttribute("value"));
            objSelect.selectByIndex(random[i - 1]);
        }

        return optionsValue;
    }

    public void clickGetAllSelected() {
        WebElement getAllSelected = driver.findElement(By.id("printAll"));
        getAllSelected.click();
    }

    public List<WebElement> getAllSelected() {
        Select objSelect = new Select(driver.findElement(By.id("multi-select")));

        return objSelect.getAllSelectedOptions();
    }
}
