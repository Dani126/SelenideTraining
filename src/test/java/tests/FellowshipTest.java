package tests;

import base.TestBase;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FellowshipTest extends TestBase {

    @Before
    public void openPage() {
        open(BASE_URL + "/fellowship.php");
    }

    @Test
    public void itShouldContainNameForEachFellow() {
        ElementsCollection fellowElements = getFellowElements();
        for (SelenideElement fellowElement : fellowElements) {
            fellowElement.find("h1").shouldNotBe(Condition.empty);
        }
    }

    @Test
    public void itShouldContainSpecifiedFellows() {
        List<String> fellowNames = new ArrayList<>();

        for (SelenideElement fellowElement : getFellowElements()) {
            fellowNames.add(fellowElement.find("h1").getText());
        }
        System.out.println(fellowNames);
        Assert.assertTrue(fellowNames.contains("Gandalf"));
        Assert.assertTrue(fellowNames.contains("Aragorn"));
        Assert.assertTrue(fellowNames.contains("Frodo"));
    }

    @Test
    public void itShouldDisplayMessageComplete() {
        List<String> fellowsToSelect = new ArrayList<>();
        fellowsToSelect.add("Gandalf");
        fellowsToSelect.add("Aragorn");
        fellowsToSelect.add("Legolas");
        fellowsToSelect.add("Frodo");

        for (String fellowToSelect : fellowsToSelect) {
            selectFellow(fellowToSelect);
        }
        $("div.points-left h3").shouldHave(text("Complete"));
    }

//    @Test
//    public void itShouldHighlightFellows() {
//        List<String> fellowsToSelect = new ArrayList<String>();
//        fellowsToSelect.add("Gandalf");
//        fellowsToSelect.add("Aragorn");
//        fellowsToSelect.add("Legolas");
//        fellowsToSelect.add("Frodo");
//
//        for (String fellowToSelect : fellowsToSelect) {
//            selectFellow(fellowToSelect);
//        }
//
//        List<String> higlightedFellows =
//                $$(By.xpath("//ul[contains(@class,'list-of-fellows')]/li/div[contains(@class,'active')]//h1"))
//                        .stream()
//                        .map(SelenideElement::getText)
//                        .collect(Collectors.toList());
//
//        for (String higlightedFellow : higlightedFellows) {
//            Assert.assertTrue(fellowsToSelect.contains(higlightedFellow));
//        }
//    }

    @Test
    public void itShouldHighlightFellowsSelenide() {
        List<String> fellowsToSelect = new ArrayList<String>();
        fellowsToSelect.add("Gandalf");
        fellowsToSelect.add("Aragorn");
        fellowsToSelect.add("Legolas");
        fellowsToSelect.add("Frodo");

        for (String fellowToSelect : fellowsToSelect) {
            selectFellow(fellowToSelect);
        }

        $("ul.list-of-fellows")
                .findAll("li > div")
                .filterBy(Condition.cssClass("active"))
                .shouldHave(CollectionCondition.textsInAnyOrder(fellowsToSelect));
    }

    @Test
    public void itShouldDisplayPointsForEachFellow() {
//        ElementsCollection displayedFellows = getFellowElements();
//        for (WebElement displayedFellow : displayedFellows){
//            String actualPoints = displayedFellow.findElement(By.cssSelector("div.fellow-points h2")).getText();
//
//            Assert.assertFalse(actualPoints.isEmpty());

        //cely text navrchu nahradeny tymto jednym prikazom
        getFellowElements().forEach(selenideElement -> selenideElement.find("div.fellow-points h2").shouldNotBe(empty));
    }

    private void selectFellow(String fellowName) {

        //$x("//h1[contains(text(),'" + fellowName + "')]").click();
        $(byText(fellowName)).click();
    }

    private ElementsCollection getFellowElements() {
        return $("ul.list-of-fellows").findAll("li");
    }
}
