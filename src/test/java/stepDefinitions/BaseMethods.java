package stepDefinitions;

import com.google.common.collect.ImmutableMap;
import hooks.CucumberHook;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BaseMethods {

    public AppiumDriver driver = CucumberHook.driver;

    public WebElement findElementByText(String text){
        return driver.findElement(By.xpath("//*[@text='"+text+"']"));
    }

    protected void waitVisibilityElement(By locator, int time){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitVisibilityElement(WebElement element, int time){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitVisibilityElement(List<WebElement> elements, int time){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    protected void selectVisibleText(WebElement element, String text){
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    protected void clickWithAction(WebElement element){
        Actions actions = new Actions(driver);
        actions.click(element).perform();
    }

    protected JavascriptExecutor getJsExecutor(){
        return (JavascriptExecutor) driver;
    }

    protected void moveToElement(WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

//    protected Set<String> scrollAndAddToList(List<WebElement> list){
//        boolean canScrollMore;
//        List<WebElement> elements;
//        Set<String> elementsText = new HashSet<>();
//        do {
//            elements = list;
//            for (WebElement element : elements){
//                elementsText.add(element.getText());
//            }
//            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
//                    "left", 100, "top", 100, "width", 200, "height", 200,
//                    "direction", "down",
//                    "percent", 3.0
//            ));
//        }while (canScrollMore);
//        return elementsText;
//    }
}
