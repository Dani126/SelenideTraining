package base;

import com.codeborne.selenide.Browser;
import com.codeborne.selenide.Browsers;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Configuration.*;

public class TestBase {
    protected WebDriver driver;

    //    protected final String BASE_URL = "http://localhost:82";
    static {
        baseUrl = "http://localhost:82";
        timeout = 4000;
        pollingInterval = 200;
//        headless = true;
        startMaximized = true;
        holdBrowserOpen = true;
        clickViaJs = true;
//        browser = Browsers.FIREFOX;
//        remote = "localhost:4444/wd/hub";
        reportsFolder = "src\\test\\resources\\reporty";
//        reportsUrl = cesta na web napr pre Jenkins
    }

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/win/chromedriver79_win.exe");
//        driver = new ChromeDriver();
//        WebDriverRunner.setWebDriver(driver);
    }

    @After
    public void tearDown() {
//        driver.close();
//        driver.quit();
    }
}
