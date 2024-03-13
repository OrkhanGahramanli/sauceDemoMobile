package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pom.InventoryPom;

import java.util.List;

import static pom.ElementsMap.elementsMap;

public class InventorySteps extends BaseMethods{
    
    InventoryPom inventoryPom = InventoryPom.getInstance();

    String selectedProductName;
    
    @When("User selects any product")
    public void userSelectsAnyProduct(){
        List<WebElement> products = driver.findElements(inventoryPom.getProducts());
        int productIndex = (int) (Math.random() * products.size());
        selectedProductName = products.get(productIndex).getText();
        products.get(productIndex).click();
    }

    @Then("Product name in details page should equal selected product name")
    public void productNameShouldEqualSelectedProduct() {
        waitVisibilityElement(findElementByText(selectedProductName), 5);
        Assert.assertEquals(findElementByText(selectedProductName).getText(), selectedProductName);
    }

    @Then("Added products count should be visible on basket icon")
    public void addedProductsCountShouldBeVisibleOnBasketIcon() {
        Assert.assertEquals(driver.findElement(inventoryPom.getProductCountAddToCart()).getText(),
                                            driver.findElement(elementsMap.get("basketBtn")).getText());
    }

    @Then("Added products should be visible in basket page")
    public void addedProductsShouldBeVisibleInBasketPage() {
        Assert.assertTrue(driver.findElement(inventoryPom.getProductInBasket()).isDisplayed());
    }
}
