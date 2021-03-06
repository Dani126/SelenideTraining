package tests;

import java.util.List;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import base.TestBase;

import static com.codeborne.selenide.Selenide.*;

public class RandomTableTest extends TestBase {
    @Before
    public void openPage() {
        //1.otvorit stranku
        open("/tabulka.php");
    }

    @Test
    public void itShouldContainDataForEachRow() {
        for (SelenideElement tableRow : getRows()) {
            Assert.assertFalse(tableRow.getText().isEmpty());
        }
    }

    @Test
    public void itShouldContainNameForEachRow() {
        ElementsCollection tableRows = getRows();
        for (WebElement tableRow : tableRows) {
            tableRow.findElement(By.cssSelector("td:nth-child(2)"));
            WebElement rowName = tableRow.findElement(By.xpath("./td[2]"));
            Assert.assertFalse(rowName.getText().isEmpty());
        }
    }

    @Test
    public void itShouldScrollToLastElement() {
//        WebElement lastRow = $("table > tbody > tr:last-child");
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", lastRow);
        $("table > tbody > tr:last-child").scrollIntoView(false);
    }

//    @Test
//    public void hladanieVTabulke(){
//        System.out.println(driver.findElement(By.xpath("//table/tbody/tr[2]/td[4]")).getText());
//        System.out.println($("table > tbody > tr",1).find("td",3).getText());
//    }

    private ElementsCollection getRows() {
        return $$("table tbody tr");
    }
}
