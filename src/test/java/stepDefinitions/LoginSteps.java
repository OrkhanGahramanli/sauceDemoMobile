package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pom.LoginPom;

import static pom.ElementsMap.elementsMap;

public class LoginSteps extends BaseMethods {

    LoginPom loginPom = LoginPom.getInstance();

    @And("User clicks {string} button from dropdown")
    public void userClicksButtonDropdown(String text){
        findElementByText(text).click();
    }

    @Then("User should be navigated to HomePage")
    public void userShouldBeNavigatedToHomePage() {
        Assert.assertTrue(findElementByText("Products").isDisplayed());
    }

    @And("User clicks {string} button on pop-up window")
    public void userClicksButtonOnPopUpWindow(String element) {
        driver.findElement(elementsMap.get(element)).click();
    }
}
