package tests;

import com.codeborne.selenide.Condition;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WaitForItTest extends TestBase {

    @Before
    public void openPage() {
        open("/waitforit.php");
    }

//    @Test
//    public void waitForValue() {
//        String expectedText = "dary !!!";
//        $(By.id("startWaitForText")).click();
//        WebElement input = $(By.id("waitForTextInput"));
//
//        new WebDriverWait(driver, 5)
//            .until(ExpectedConditions.attributeToBe(input, "value", expectedText));
//
//        Assert.assertEquals(expectedText, input.getAttribute("value"));
//    }

    @Test
    public void waitForValueSelenide() {
        String expectedText = "dary !!!";
        $(byId("startWaitForText")).click();
        $(byId("waitForTextInput")).shouldHave(value(expectedText));
    }

//    @Test
//    public void waitForClass() {
//        $(By.id("startWaitForProperty")).click();
//
//        new WebDriverWait(driver, 10)
//            .until(ExpectedConditions.attributeContains(By.id("waitForProperty"),"class","error"));
//    }

    @Test
    public void waitForClassSelenide() {
        $(byId("startWaitForProperty")).click();
        $(byId("waitForProperty")).shouldHave(cssClass("error"));
    }


//    @Test
//    public void itShouldDisplayResponseTimeMessage() {
//        $(By.id("startWaitForText")).click();
//
//        new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElement(
//            $("div.current-wait-time"),
//            "Response time"));
//        Assert.assertTrue($(By.cssSelector("div.current-wait-time")).getText().contains("Response time"));
//    }

    @Test
    public void itShouldDisplayResponseTimeMessageSelenide() {
        $(By.id("startWaitForText")).click();
        $("div.current-wait-time").shouldHave(Condition.text("Response time was"));
    }
}
