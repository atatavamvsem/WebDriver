import forms.AlertPage;
import forms.AlertUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import utils.GenerateRandomUtil;
import utils.ResourcesProperties;
import utils.WebDriverManager;

public class AlertsTest {
    private WebDriver driver;
    private AlertPage alertPage;
    private AlertUtil alertUtil;

    @BeforeEach
    public void setUp() {
        driver = WebDriverManager.getInstance();
        alertPage = new AlertPage();
        alertUtil = new AlertUtil();
    }

    @Test
    public void confirmBoxOkTest() {
        alertPage.clickConfirmBoxButton();

        alertUtil.acceptAlert();

        Assertions.assertEquals("You pressed OK!", alertPage.getTextConfirmBox(alertPage.confirmMessage));
    }

    @Test
    public void confirmBoxCancelTest() {
        alertPage.clickConfirmBoxButton();

        alertUtil.dismissAlert();

        Assertions.assertEquals("You pressed Cancel!", alertPage.getTextConfirmBox(alertPage.confirmMessage), "The message is wrong");
    }

    @Test
    public void alertBoxSentTest() {
        alertPage.clickPromptBoxButton();

        String inputMessage = GenerateRandomUtil.generateRandomString(Integer.parseInt(ResourcesProperties.getDataProperty("lengthMessage")));
        alertUtil.sendKeys(inputMessage);
        alertUtil.acceptAlert();

        Assertions.assertEquals("You have entered '" + inputMessage + "' !", alertPage.getTextConfirmBox(alertPage.promptMessage), "Messages don't match");
    }

    @AfterEach
    public void closeUp() {
        driver.quit();
        WebDriverManager.delDriver();
    }
}
