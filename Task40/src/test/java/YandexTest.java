import org.junit.jupiter.api.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.JsonParser;
import utils.User;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;


public class YandexTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private static User[] users;
    private static JsonParser parser;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 30);
    }

    private static Stream<User> usersProvider(){
        parser = new JsonParser();
        users = parser.readFromFile(new File("src/test/resources/users.json"));
        return Stream.of(users);
    }

    @ParameterizedTest
    @MethodSource("usersProvider")
    public void loginTest(User user) throws InterruptedException {
        driver.get("https://mail.yandex.com/");

        WebElement inputButton = driver.findElement(By.xpath("//a[contains(@class,'HeadBanner-Button-Enter')]"));
        inputButton.click();

        WebElement loginInput = driver.findElement(By.xpath("//input[@id='passp-field-login']"));
        loginInput.sendKeys(user.getUserName());

        WebElement loginButton = driver.findElement(By.xpath("//button[@id='passp:sign-in']"));
        loginButton.click();

        //Is not type of waits. It is interruption of the thread
        Thread.sleep(1000);

        WebElement passwordInput = driver.findElement(By.xpath("//input[@id='passp-field-passwd']"));
        passwordInput.sendKeys(user.getPassword());

        WebElement loginSecondButton = driver.findElement(By.xpath("//button[@id='passp:sign-in']"));
        loginSecondButton.click();

        //Wait disabled for to avoid problems with to combine implicit and explicit waits
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wait.pollingEvery(1, TimeUnit.SECONDS);

        Assertions.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class,'user-account_left-name')]/span[@class='user-account__name']"))).getText(),
                user.getUserName(),"Login failed");
    }

    @AfterEach
    public void closeUp() {
        driver.quit();
    }
}