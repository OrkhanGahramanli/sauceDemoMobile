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
    private final By productIncreaseBtn = AppiumBy.xpath("//*[@content-desc='counter plus button']");
    private final By inventoryPageFooter = AppiumBy.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[4]");

    static {
        elementsMap.put("addToCartBtn", AppiumBy.accessibilityId("Add To Cart button"));
        elementsMap.put("basketBtn", AppiumBy.xpath("//*[@content-desc='cart badge']"));
        elementsMap.put("proceedCheckoutBtn", AppiumBy.accessibilityId("Proceed To Checkout button"));
        elementsMap.put("fullName", AppiumBy.accessibilityId("Full Name* input field"));
        elementsMap.put("addressLine", AppiumBy.accessibilityId("Address Line 1* input field"));
        elementsMap.put("city", AppiumBy.accessibilityId("City* input field"));
        elementsMap.put("zipCode", AppiumBy.accessibilityId("Zip Code* input field"));
        elementsMap.put("country", AppiumBy.accessibilityId("Country* input field"));
        elementsMap.put("toPaymentBtn", AppiumBy.accessibilityId("To Payment button"));
        elementsMap.put("cardNumber", AppiumBy.accessibilityId("Card Number* input field"));
        elementsMap.put("expirationDate", AppiumBy.accessibilityId("Expiration Date* input field"));
        elementsMap.put("securityCode", AppiumBy.accessibilityId("Security Code* input field"));
        elementsMap.put("reviewOrderBtn", AppiumBy.accessibilityId("Review Order button"));
        elementsMap.put("placeOrderBtn", AppiumBy.accessibilityId("Place Order button"));
        elementsMap.put("sortProductBtn", AppiumBy.xpath("//*[@content-desc='sort button']"));
    }


}
