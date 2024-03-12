package pom;

import io.appium.java_client.AppiumBy;
import lombok.Data;
import org.openqa.selenium.By;

import static pom.ElementsMap.elementsMap;

@Data
public class GeneralPom {

    private GeneralPom(){
    }

    private static GeneralPom INSTANCE;

    public static GeneralPom getInstance(){
        if (INSTANCE == null){
            INSTANCE = new GeneralPom();
            return INSTANCE;
        }
        return INSTANCE;
    }

    {
        elementsMap.put("burgerMenu", AppiumBy.accessibilityId("open menu"));
    }

}
