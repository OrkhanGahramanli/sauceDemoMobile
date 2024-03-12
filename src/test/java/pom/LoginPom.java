package pom;

import io.appium.java_client.AppiumBy;
import io.cucumber.java.an.Dada;
import lombok.Data;
import org.openqa.selenium.By;

import static pom.ElementsMap.elementsMap;

@Data
public class LoginPom {

    private LoginPom(){
    }

    private static LoginPom INSTANCE;

    public static LoginPom getInstance(){
        if (INSTANCE == null){
            INSTANCE = new LoginPom();
            return INSTANCE;
        }
        return INSTANCE;
    }

    {
        elementsMap.put("username", AppiumBy.accessibilityId("Username input field"));
        elementsMap.put("password", AppiumBy.accessibilityId("Password input field"));
        elementsMap.put("loginBtn", AppiumBy.accessibilityId("Login button"));
        elementsMap.put("logOutBtn", AppiumBy.id("android:id/button1"));
    }
}
