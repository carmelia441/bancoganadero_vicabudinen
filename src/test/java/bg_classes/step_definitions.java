package bg_classes;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.lang.Integer.parseInt;

public class step_definitions {
    WebDriver driver;
    public String cond = "";

    @Given("the user is in mercado libre and selects \"([^\"]*)\"$")
    public void the_user_is_in_mercado_libre(String country) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(" https://mercadolibre.com/");
        driver.findElement(By.linkText(country)).click();

    }

    @When("^the user searches for \"([^\"]*)\"$")
    public void the_user_searches_for(String product) {
        driver.findElement(By.cssSelector(".nav-search>.nav-search-input")).sendKeys(product + Keys.ENTER);
    }

    @When("^the user filters by condition \"([^\"]*)\"$")
    public String the_user_filters_by_condition(String condition) throws InterruptedException {
        String var = "//span[text()='"+condition+"']";
        WebElement element = driver.findElement(By.xpath(var));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(500);
        element.click();

        return cond = condition;
    }

    @Then("^the user validates at least (\\d+) products$")
    public void the_user_validates_at_least_products(int prod) throws InterruptedException {
       String products =  driver.findElement(By.className("ui-search-search-result__quantity-results")).getText();
       products = products.replace("resultados", "").trim();
        Assert.assertTrue(parseInt(products) > prod);
        driver.quit();

    }

}
