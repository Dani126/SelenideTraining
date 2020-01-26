package tests;

import base.TestBase;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.matchesText;
import static com.codeborne.selenide.Selenide.*;
import static utils.DataUtils.getExpectedSpells;

public class SpelleologyTest extends TestBase {

    @Before
    public void openPage() {
        open("/spelleology.php");
    }

//    @Test
//    public void itShouldContainSpells() {
//        String[] spellsToBePresent = {
//                "counters sonorus",
//                "erases memories",
//                "counterspells",
//                "controls a person – unforgivable"
//        };
//
//        new WebDriverWait(driver, 10)
//                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul.spells li")));
//        List<String> displayedSpells = driver.findElements(By.cssSelector("ul.spells li"))
//                .stream()
//                .map(WebElement::getText)
//                .collect(Collectors.toList());
//
//        for (String spellToCheck : spellsToBePresent) {
//            Assert.assertTrue(displayedSpells.contains(spellToCheck));
//        }
//    }

    @Test
    public void itShouldContainSpellsSelenide() throws FileNotFoundException {
        List<String> expectedSpells = getExpectedSpells();

        $("ul.spells")
                .findAll("li")
                .shouldHave(sizeGreaterThan(1))
                .shouldHave(texts(expectedSpells));
//       toto dalej uz je nepotrebny kod, pretoze shouldHave(texts(expectedSpells)) to spravi za mna
//                .stream()
//                .map(SelenideElement::getText)
//                .collect(Collectors.toList());
//
//        for (String spellToCheck : spellsToBePresent) {
//            Assert.assertTrue(displayedSpells.contains(spellToCheck));
//        }
    }


//    @Test
//    public void itShouldDisplayTortureSpell() {
//        new WebDriverWait(driver, 10)
//                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul.spells li")));
//        List<WebElement> spellElements = driver.findElements(By.cssSelector("ul.spells li"));
//
//        for (WebElement spellElement : spellElements) {
//            if (spellElement.getText().equals("tortures a person")) {
//                spellElement.click();
//            }
//        }
//        new WebDriverWait(driver, 10)
//                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.modal-container")));
//        WebElement modal = $(By.cssSelector("div.modal-container"));
//        Assert.assertTrue(modal.getText().contains("Crucio"));
//    }

    @Test
    public void itShouldDisplayTortureSpellSelenide() {
        $("ul.spells")
                .findAll("li")
                .shouldHave(CollectionCondition.sizeGreaterThan(1))
                .find(Condition.text("tortures a person"))
                .click();
        $("div.modal-container").waitUntil(Condition.visible, 10000).shouldHave(text("Crucio"));
    }


//    @Test
//    public void itShouldFilterSpells() {
//        $("input").sendKeys("tortures a person");
//        new WebDriverWait(driver, 10).until(ExpectedConditions
//                .numberOfElementsToBe(By.cssSelector("ul.spells li"), 1));
//        Assert.assertEquals(driver.findElements(By.cssSelector("ul.spells li")).size(), 1);
//    }

    @Test
    public void itShouldFilterSpellsSelenide() {
        $("input").sendKeys("tortures a person");
        $$("ul.spells li").shouldHave(size(1));
    }

    //    @Test
//    public void itShouldExcludeSpells(){
//        $("ul.spells")
//                .findAll("li")
//                .shouldHave(sizeGreaterThan(1))
//                .forEach(selenideElement -> System.out.println(selenideElement.getText()));
//    }

    @Test
    public void itShouldExcludeSpellsSelenide(){
        $("ul.spells")
                .findAll("li")
                .shouldHave(sizeGreaterThan(1))
                .filterBy(matchesText("^shoots.*"))
                .exclude(readonly)
                .exclude(matchesText("^opens.*"))
                .exclude(matchesText("^[a|b|c|d].*"))
                .forEach(selenideElement -> System.out.println(selenideElement.getText()));
    }
}
