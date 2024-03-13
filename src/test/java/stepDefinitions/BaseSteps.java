package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import pom.GeneralPom;

import static pom.ElementsMap.elementsMap;

public class BaseSteps extends BaseMethods {

    GeneralPom generalPom = GeneralPom.getInstance();

    @Given("User is in {string}")
    public void userIsInPage(String text){

    }

    @When("User clicks {string} button")
    public void userClicksButton(String element) {
        if (!element.isEmpty()){
            WebElement myElement = driver.findElement(elementsMap.get(element));
            try {
                if (myElement.isDisplayed()) myElement.click();
                else {
                    moveToElement(myElement);
                    myElement.click();
                }
            } catch (Exception e) {
                try {
                    if (myElement.isDisplayed()) clickWithAction(myElement);
                    else {
                        moveToElement(myElement);
                        clickWithAction(myElement);
                    }
                } catch (Exception e2) {
                    if (myElement.isDisplayed()) getJsExecutor().executeScript("arguments[0].click();", myElement);
                    else {
                        moveToElement(myElement);
                        getJsExecutor().executeScript("arguments[0].click();", myElement);
                    }
                }
            }
        }
    }

    @And("User fills {string} in {string} input field")
    public void userFillsInInputField(String text, String element) {
        if (!text.isEmpty()) driver.findElement(elementsMap.get(element)).sendKeys(text);
    }
}
