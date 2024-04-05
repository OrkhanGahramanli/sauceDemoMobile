package hooks;

import driverSession.CreateAppiumDriverSession;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.time.Duration;

public class CucumberHook {

    public static AppiumDriver driver;
    public static String  pomName;


    @Before
    public static void beforeScenario(Scenario scenario) throws MalformedURLException {
        pomName = FilenameUtils.getBaseName(scenario.getUri().toString());
        try {
            Class<?> clazz = Class.forName("pom." + pomName + "Pom");
            Object o = clazz.getDeclaredConstructor().newInstance();
        }catch (Exception ignored){

        }
        driver = CreateAppiumDriverSession.driver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @After
    public static void afterScenario(Scenario scenario) throws InterruptedException {
        if(scenario.isFailed()) {
            Thread.sleep(300);
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        driver.quit();
    }
}
