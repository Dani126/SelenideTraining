package tests;

import base.TestBase;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SortingHatTest extends TestBase {


    //    @Test
//    public void itShouldDisplayNameOfHouse() {
//        open(BASE_URL + "/sortinghat.php");
//        $(By.cssSelector("button")).click();
//        new WebDriverWait(driver, 10)
//            .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img.loading")));
//        new WebDriverWait(driver, 10)
//            .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("img.loading")));
//        Assert.assertFalse($(By.cssSelector("p.result")).getText().isEmpty());
//    }
    @Test
    public void itShouldDisplayNameOfHouseSelenide() {
        open(BASE_URL + "/sortinghat.php");
        $(By.cssSelector("button")).click();
        $("img.loading").should(appear).should(disappear);
        $("p.result").shouldBe(visible).shouldNotBe(empty);
    }
    @Test
    public void itShouldDisplayGryffindor(){
        open(BASE_URL + "/sortinghat.php");
        String generatedHouse = "";

        while (!generatedHouse.equals("Gryffindor")) {
            $("button").shouldBe(enabled).click();
            $("img.loading").should(appear).should(disappear);
            generatedHouse = $("p.result").shouldBe(visible).shouldNotBe(empty).getText();
        }
    }

    @Test
    public void waitMoreThanFourSecondsForElement(){
        $("img.loading").waitUntil(appears,15000);
    }
}
