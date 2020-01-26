package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class SavingsCalculatorPage {
    private SelenideElement emailInput = $(byId("emailInput"));

    private SelenideElement yearsInput= $(byId("yearsInput"));

    private SelenideElement oneTimeInvestmentInput= $(byId("oneTimeInvestmentInput"));

    private SelenideElement fundSelect= $(byId("fundSelect"));

    private SelenideElement applyButton= $("button.btn");

    private SelenideElement resultElement= $("div.result");

    private SelenideElement mostRecentSavingsDetail= $("ul.saving-list").find("div.saving-detail");

    public SavingsCalculatorPage() {
        page(this);
    }

    public void enterEmail(String email) {
//        emailInput.clear();
//        emailInput.sendKeys(email);
//        emailInput.sendKeys(Keys.TAB);
//        $(emailInput).val(email).pressTab(); obalovanie rusim, kedze som na stranke zmenil vsetky elemenety na selenide
        emailInput.val(email).pressTab();
    }

    public void enterYears(int years) {
//        yearsInput.clear();
//        yearsInput.sendKeys(String.valueOf(years));
//        $(yearsInput).val(String.valueOf(years));
        yearsInput.val(String.valueOf(years));

    }

    public void enterOneTimeInvestment(String amount) {
        //oneTimeInvestmentInput.clear();
        //oneTimeInvestmentInput.sendKeys(amount);
        //kod vyssie nahradeny prikazom s metodou val
//        $(oneTimeInvestmentInput).val(amount);
        oneTimeInvestmentInput.val(amount);
    }

    public void selectFund(String fundToSelect) {
//        new Select(fundSelect).selectByVisibleText(fundToSelect);
//        $(fundSelect).selectOption(fundToSelect);
        fundSelect.selectOption(fundToSelect);
    }

    public void applyForSaving() {
        applyButton.click();
    }


    public SelenideElement getCalculatedTotalIncomeElement() {
//        return resultElement.findElement(By.xpath("./div[1]/p"));
//        return $(resultElement).find(By.xpath("./div[1]/p"));
        return resultElement.find(By.xpath("./div[1]/p"));
    }

    public SelenideElement getCalculatedInterestIncomeElement() {
//        return pageDriver.findElement(By.cssSelector("div.result > div:nth-child(2) p"));
//        return $(resultElement).find(By.cssSelector("div.result > div:nth-child(2) p"));
        return resultElement.find(By.cssSelector("div.result > div:nth-child(2) p"));
    }

    public SelenideElement getCalculatedRiskElement() {
//        return resultElement.findElement(By.xpath("./div[3]/p"));
//        return $(resultElement).find("div",2).find("p");
        return resultElement.find("div",2).find("p");
    }

    public SelenideElement getRecentRequestDetail() {
//        return $(mostRecentSavingsDetail);
        return mostRecentSavingsDetail;
    }

    public SelenideElement getApplyButton() {
//        return $(applyButton);
        return applyButton;
    }


    public SelenideElement getEmailInputWrapper(){
//        return pageDriver.findElement(By.xpath("//input[@id='emailInput']/.."));
//        return $(emailInput).parent();
        return emailInput.parent();
    }
}