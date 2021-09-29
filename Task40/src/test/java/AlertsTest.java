import forms.AlertPage;
import forms.AlertUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
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

        Assertions.assertEquals("You pressed Cancel!", alertPage.getTextConfirmBox(alertPage.confirmMessage));
    }

    @Test
    public void alertBoxSentTest() {
        alertPage.clickPromptBoxButton();

        alertUtil.sendKeys(ResourcesProperties.getDataProperty("message"));
        alertUtil.acceptAlert();

        Assertions.assertEquals("You have entered '"+ ResourcesProperties.getDataProperty("message") +"' !", alertPage.getTextConfirmBox(alertPage.promptMessage));
    }

    @AfterEach
    public void closeUp() {
        driver.close();
        WebDriverManager.delDriver();
    }
}
