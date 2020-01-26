package tests;

import base.TestBase;
import com.codeborne.selenide.Condition;
import org.junit.Before;
import org.junit.Test;
import pages.SavingsCalculatorPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SavingsCalculatorTest extends TestBase {
    private SavingsCalculatorPage savingsCalculatorPage;

    @Before
    public void openPage() {
        open("/savingscalculator.php");
        savingsCalculatorPage = new SavingsCalculatorPage();
    }

    @Test
    public void itShouldEnableApplyButton() {
        savingsCalculatorPage.selectFund("Hoggwart's Fund");
        savingsCalculatorPage.enterOneTimeInvestment("15000");
        savingsCalculatorPage.enterYears(20);
        savingsCalculatorPage.enterEmail("info@furbo.sk");

        //assertTrue(savingsCalculatorPage.getApplyButton().isEnabled());
        savingsCalculatorPage.getApplyButton().shouldBe(Condition.enabled);
    }

//    @Test
//    public void najdiPodlaAtributu(){
//        //driver.findElement(By.xpath("//input[@placeholder='One time investment']")).sendKeys("25");
//        $(byAttribute("placeholder","One time investment")).sendKeys("20");
//        System.out.println("nieco");
//    }

    @Test
    public void itShouldDisplayCalculatedAmounts() {
        savingsCalculatorPage.selectFund("Hoggwart's Fund");
        savingsCalculatorPage.enterOneTimeInvestment("15000");
        savingsCalculatorPage.enterYears(20);
        savingsCalculatorPage.enterEmail("info@furbo.sk");

//        assertFalse(savingsCalculatorPage.getCalculatedTotalIncomeElement().getText().isEmpty());
//        assertFalse(savingsCalculatorPage.getCalculatedInterestIncomeElement().getText().isEmpty());
        savingsCalculatorPage.getCalculatedTotalIncomeElement().shouldNotBe(Condition.empty).shouldHave(Condition.text("kr"));
        savingsCalculatorPage.getCalculatedInterestIncomeElement().shouldNotBe(Condition.empty).shouldHave(Condition.text("kr"));
    }

    @Test
    public void itShouldDisplayCalculatedRisk() {
        savingsCalculatorPage.selectFund("Hoggwart's Fund");
        savingsCalculatorPage.enterOneTimeInvestment("15000");
        savingsCalculatorPage.enterYears(20);
        savingsCalculatorPage.enterEmail("info@furbo.sk");

//        assertFalse(savingsCalculatorPage.getCalculatedRiskElement().getText().isEmpty());
        savingsCalculatorPage.getCalculatedRiskElement().shouldNotBe(Condition.empty);
    }


    @Test
    public void itShouldContainFundNameInNewRequest() {
        String fundToSelect = "Hoggwart's Fund";

        savingsCalculatorPage.selectFund(fundToSelect);
        savingsCalculatorPage.enterOneTimeInvestment("25000");
        savingsCalculatorPage.enterYears(20);
        savingsCalculatorPage.enterEmail("info@furbo.sk");

        savingsCalculatorPage.applyForSaving();

//        assertEquals(
//                fundToSelect,
//                savingsCalculatorPage.getRecentRequestDetail().findElement(cssSelector("p.fund-description")).getText());
        savingsCalculatorPage
                .getRecentRequestDetail()
                .find("p.fund-description")
                .shouldHave(exactText(fundToSelect).because("Preco toto slovicko hladam"));
    }

    @Test
    public void itShouldDisplayErrorMessageWhenEmailIsInvalid() {
        savingsCalculatorPage.enterEmail("invalid");
//        assertTrue(savingsCalculatorPage.getEmailInputWrapper().getAttribute("class").contains("error"));
        savingsCalculatorPage.getEmailInputWrapper().shouldHave(cssClass("error"));
    }

    @Test
    public void itShouldHighlightNewRequestOnHover() throws InterruptedException {
        savingsCalculatorPage.selectFund("Hoggwart's Fund");
        savingsCalculatorPage.enterOneTimeInvestment("15000");
        savingsCalculatorPage.enterYears(20);
        savingsCalculatorPage.enterEmail("info@furbo.sk");
        savingsCalculatorPage.applyForSaving();

//        Actions action = new Actions(driver);
//        WebElement we = $("div.saving-detail");
//        action.moveToElement(we).build().perform();
//        Thread.sleep(300);
//        assertEquals("rgba(4, 102, 156, 1)", we.getCssValue("background-color"));
        $("div.saving-detail")
                .hover()
                .shouldHave(cssValue("background-color", "rgba(4, 102, 156, 1)"));
    }
}



