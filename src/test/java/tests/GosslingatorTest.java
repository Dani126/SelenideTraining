package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class GosslingatorTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/win/chromedriver79_win.exe");
        driver = new ChromeDriver();
        WebDriverRunner.setWebDriver(driver);
        open("http://localhost:82/gosslingator.php");
    }

//    @Test
//    public void itShouldDisplayTitle() {
//        Assert.assertEquals("GOSLINGATE ME", $(".ryan-title").getText());
//    }

    @Test
    public void itShouldDisplayTitleSelenide() {
        $(".ryan-title").shouldHave(text("GOSLINGATE ME"));
    }

//    @Test
//    public void itShouldAddOneRyan() {
//        $(By.id("addRyan")).click();
//
//        String actualNumberOfRyans = $(By.id("ryanCounter")).getText();
//        Assert.assertEquals("1", actualNumberOfRyans);
//
//        System.out.println("Number of ryans: " + $("div.ryan-counter h2").getText());
//        Assert.assertEquals("ryan", $("div.ryan-counter h3").getText());
//    }

    @Test
    public void itShouldAddOneRyanSelenide() {
        $(By.id("addRyan")).click();
        $(By.id("ryanCounter")).shouldHave(text("1"));;

        System.out.println("Number of ryans: " + $("div.ryan-counter h2").getText());
        $("div.ryan-counter h3").shouldHave(text("ryan"));
    }

//    @Test
//    public void itShouldTwoRyans() {
//        $(By.id("addRyan")).click();
//        $(By.id("addRyan")).click();
//
//        String actualNumberOfRyans = $(By.id("ryanCounter")).getText();
//        String actualRyanDescription = $("div.ryan-counter h3").getText();
//
//        Assert.assertEquals("2", actualNumberOfRyans);
//        Assert.assertEquals("ryans", actualRyanDescription);
//    }

    @Test
    public void itShouldTwoRyansSelenide() {
        $(By.id("addRyan")).click();
        $(By.id("addRyan")).click();

        $(By.id("ryanCounter")).shouldHave(text("2"));
        $("div.ryan-counter h3").shouldHave(text("ryans"));
    }

//    @Test
//    public void itShouldDisplayWarningMessage() {
//        WebElement addRyanButton = $(By.id("addRyan"));
//        for (int i = 0; i < 50; i++) {
//            addRyanButton.click();
//        }
//        Assert.assertEquals(
//                "NUMBER OF\n" +
//                        "RYANS\n" +
//                        "IS TOO DAMN\n" +
//                        "HIGH",
//                $("h1.tooManyRyans").getText()
//        );
//    }

    @Test
    public void itShouldDisplayWarningMessageSelenide() {
        WebElement addRyanButton = $(By.id("addRyan"));
        for (int i = 0; i < 50; i++) {
            addRyanButton.click();
        }
        $("h1.tooManyRyans").shouldHave(text("NUMBER OF\n" +
                        "RYANS\n" +
                        "IS TOO DAMN\n" +
                        "HIGH"));
    }

//    @Test
//    public void itShouldDisplayNoRyanOnPageOpen() {
//        Assert.assertEquals(0, driver.findElements(By.cssSelector("img")).size());
//    }

    @Test
    public void itShouldDisplayNoRyanOnPageOpenSelenide() {
        $("img").shouldBe(Condition.hidden);
        $(By.id("ryanCounter")).shouldHave(text("0"));

    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
