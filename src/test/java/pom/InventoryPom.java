package pom;

import io.appium.java_client.AppiumBy;
import lombok.Data;
import org.openqa.selenium.By;

import static pom.ElementsMap.elementsMap;

@Data
public class InventoryPom {

    private InventoryPom(){
    }

    private static InventoryPom INSTANCE;

    public static InventoryPom getInstance(){
        if (INSTANCE == null){
            INSTANCE = new InventoryPom();
            return INSTANCE;
        }
        return INSTANCE;
    }

    private final By products = AppiumBy.xpath("//*[@content-desc='store item text']");
    private final By productPrices = AppiumBy.xpath("//*[@content-desc='store item price']");
    private final By productInBasket = AppiumBy.accessibilityId("product label");
    private final By productCountAddToCart = AppiumBy.accessibilityId("counter amount");

    {
        elementsMap.put("addToCartBtn", AppiumBy.accessibilityId("Add To Cart button"));
        elementsMap.put("basketBtn", AppiumBy.xpath("//*[@content-desc='cart badge']"));
    }


}
