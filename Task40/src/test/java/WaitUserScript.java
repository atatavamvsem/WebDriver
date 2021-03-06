import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUserScript {
    private static WebDriver driver;
    private static WebDriverWait wait;

    public static void main(String[] args){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.seleniumeasy.com/test/dynamic-data-loading-demo.html");
        wait = new WebDriverWait(driver, 30);

        WebElement inputButton = driver.findElement(By.xpath("//button[@id='save']"));
        inputButton.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='loading']/br")));

        System.out.println(driver.findElement(By.xpath("//div[@id='loading']")).getText());

        driver.quit();
    }
}
