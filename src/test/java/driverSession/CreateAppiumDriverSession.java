package driverSession;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class CreateAppiumDriverSession {

    public static AppiumDriver driver() throws MalformedURLException {
        URL url = new URL("http://127.0.0.1:4723/");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("app",System.getProperty("user.dir") + "/app/sauceDemo.apk" );
        return new AppiumDriver(url, capabilities);
    }
}
