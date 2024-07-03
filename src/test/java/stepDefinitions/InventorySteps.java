package stepDefinitions;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_old.Ac;
import io.cucumber.java.eo.Se;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import pom.InventoryPom;

import java.util.*;

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
        waitPresenceElement(findByText(selectedProductName), 5);
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
        boolean canScrollMore;
        List<WebElement> list;
        Set<String> products = new LinkedHashSet<>();
        List<String> productsList;
        switch (sortType){
            case "Name - Ascending":
                do {
                    list = driver.findElements(inventoryPom.getProducts());
                    for (WebElement element : list){
                        products.add(element.getText());
                    }
                    canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                            "left", 100, "top", 100, "width", 200, "height", 200,
                            "direction", "down",
                            "percent", 3.0
                    ));
                }while (canScrollMore);
                productsList = new ArrayList<>(products);

                for (int i = 1; i < productsList.size(); i++){
                    Assert.assertTrue(productsList.get(i-1).
                        compareTo(productsList.get(i)) <= 0);
                }
                break;

            case "Name - Descending":
                do {
                    list = driver.findElements(inventoryPom.getProducts());
                    for (WebElement element : list){
                        products.add(element.getText());
                    }
                    canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                            "left", 100, "top", 100, "width", 200, "height", 200,
                            "direction", "down",
                            "percent", 3.0
                    ));
                }while (canScrollMore);
                productsList = new ArrayList<>(products);

                for (int i = 1; i < productsList.size(); i++){
                    Assert.assertTrue(productsList.get(i-1).
                        compareTo(productsList.get(i)) >= 0);
                }
            break;

            case "Price - Ascending":
                do {
                    list = driver.findElements(inventoryPom.getProductPrices());
                    for (WebElement element : list){
                        products.add(element.getText());
                    }
                    canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                            "left", 100, "top", 100, "width", 200, "height", 200,
                            "direction", "down",
                            "percent", 3.0
                    ));
                }while (canScrollMore);
                productsList = new ArrayList<>(products);

                for (int i = 1; i < productsList.size(); i++){
                    Assert.assertTrue(Double.parseDouble(productsList.get(i-1).replace("$", "")) <=
                            (Double.parseDouble(productsList.get(i).replace("$", ""))));
                }
            break;

            case "Price - Descending":
                do {
                    list = driver.findElements(inventoryPom.getProductPrices());
                    for (WebElement element : list){
                        products.add(element.getText());
                    }
                    canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                            "left", 100, "top", 100, "width", 200, "height", 200,
                            "direction", "down",
                            "percent", 3.0
                    ));
                }while (canScrollMore);
                productsList = new ArrayList<>(products);

                for (int i = 1; i < productsList.size(); i++){
                    Assert.assertTrue(Double.parseDouble(productsList.get(i-1).replace("$", "")) >=
                            (Double.parseDouble(productsList.get(i).replace("$", ""))));
                }
                break;
        }
    }
}
