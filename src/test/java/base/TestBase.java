package base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Configuration.*;

public class TestBase {
    protected WebDriver driver;
//    protected final String BASE_URL = "http://localhost:82";
    static {
        baseUrl = "http://localhost:82";
        timeout = 4000;
        pollingInterval = 200;
        //headless = true;
        startMaximized = true;
        holdBrowserOpen = true;

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
