package stepDefinitions;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_old.Ac;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import pom.InventoryPom;

import java.util.ArrayList;
import java.util.List;

import static pom.ElementsMap.elementsMap;

public class InventorySteps extends BaseMethods{
    
    InventoryPom inventoryPom = InventoryPom.getInstance();

    String selectedProductName;
    String addedProductsCount;
    
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
        addedProductsCount = driver.findElement(inventoryPom.getProductCountAddToCart()).getText();
        Assert.assertEquals(addedProductsCount, driver.findElement(elementsMap.get("basketBtn")).getText());
    }

    @Then("Added products should be visible in basket page")
    public void addedProductsShouldBeVisibleInBasketPage() {
        Assert.assertTrue(driver.findElement(inventoryPom.getProductInBasket()).isDisplayed());
        Assert.assertEquals(driver.findElement(inventoryPom.getProductCountAddToCart()).getText(), addedProductsCount);
    }

    @And("User chooses product count for adding to cart")
    public void userChoosesProductCountForAddingToCart() {
        int randomNum = (int) (Math.random() * 10);
        for (int i =0; i<randomNum; i++){
            driver.findElement(inventoryPom.getProductIncreaseBtn()).click();
        }
    }

    @Then("Products order should by {string}")
    public void productsOrderShouldBy(String sortType) {

        switch (sortType){
            case "Name - Ascending":
            scrollAndAddToList(driver.findElements(inventoryPom.getProducts()));
            for (int i=0; i< scrollAndAddToList(driver.findElements(inventoryPom.getProducts())).size(); i++){

                if (i+1 == scrollAndAddToList(driver.findElements(inventoryPom.getProducts())).size()) break;

                Assert.assertTrue(scrollAndAddToList(driver.findElements(inventoryPom.getProducts())).get(i+1).getText().
                        compareTo(scrollAndAddToList(driver.findElements(inventoryPom.getProducts())).get(i).getText()) >= 0);
            }
            break;

            case "Name - Descending":
                scrollAndAddToList(driver.findElements(inventoryPom.getProducts()));
                for (int i=0; i< scrollAndAddToList(driver.findElements(inventoryPom.getProducts())).size(); i++){

                    if (i+1 == scrollAndAddToList(driver.findElements(inventoryPom.getProducts())).size()) break;

                    Assert.assertTrue(scrollAndAddToList(driver.findElements(inventoryPom.getProducts())).get(i+1).getText().
                            compareTo(scrollAndAddToList(driver.findElements(inventoryPom.getProducts())).get(i).getText()) <= 0);
                }
            break;

            case "Price - Ascending":
                scrollAndAddToList(driver.findElements(inventoryPom.getProductPrices()));
                for (int i=0; i< scrollAndAddToList(driver.findElements(inventoryPom.getProductPrices())).size(); i++){
                    if (i+1 == scrollAndAddToList(driver.findElements(inventoryPom.getProductPrices())).size()) break;

                    Assert.assertTrue(Double.parseDouble(scrollAndAddToList(driver.findElements(inventoryPom.getProductPrices())).get(i+1).getText().
                            replace("$", ""))
                            >= Double.parseDouble(scrollAndAddToList(driver.findElements(inventoryPom.getProductPrices())).get(i).getText().
                            replace("$", "")));
                }
            break;

            case "Price - Descending":
                scrollAndAddToList(driver.findElements(inventoryPom.getProductPrices()));
                for (int i=0; i< scrollAndAddToList(driver.findElements(inventoryPom.getProductPrices())).size(); i++){

                    if (i+1 == scrollAndAddToList(driver.findElements(inventoryPom.getProductPrices())).size()) break;

                    Assert.assertTrue(Double.parseDouble(scrollAndAddToList(driver.findElements(inventoryPom.getProductPrices())).get(i+1).getText().
                            replace("$", ""))
                            <= Double.parseDouble(scrollAndAddToList(driver.findElements(inventoryPom.getProductPrices())).get(i).getText().
                            replace("$", "")));
                }
                break;
        }
    }
}
