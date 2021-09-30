import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RefreshScript {
    private static WebDriver driver;
    private static WebDriverWait wait;

    private static final int PERCENTAGE = 50;

    public static void main(String[] args) {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.seleniumeasy.com/test/bootstrap-download-progress-demo.html");

        WebElement inputButton = driver.findElement(By.xpath("//button[@id='cricle-btn']"));
        inputButton.click();

        WebElement percentDownloading = driver.findElement(By.xpath("//div[@class='percenttext']"));

        wait = new WebDriverWait(driver, 30);
        ExpectedCondition<Boolean> percentageDownloadingMoreThan = arg0 -> Integer.parseInt(percentDownloading.getText().replace("%", "")) >= PERCENTAGE;
        wait.until(percentageDownloadingMoreThan);

        System.out.println(percentDownloading.getText());

        driver.navigate().refresh();
        driver.quit();
    }
}
