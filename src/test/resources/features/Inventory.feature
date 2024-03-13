Feature: Inventory

  Background:
    Given User is in "app"
    When User clicks "burgerMenu" button
    And User clicks "Log In" button from dropdown
    And User fills "bob@example.com" in "username" input field
    And User fills "10203040" in "password" input field
    And User clicks "loginBtn" button

    @InventoryDetail
    Scenario: Check any inventory details page
      Given User is in "inventoryPage"
      When User selects any product
      Then Product name in details page should equal selected product name

    @AddToBasket
      Scenario: Add product in basket
        Given User is in "inventoryPage"
        When User selects any product
        And User chooses product count for adding to cart
        And User clicks "addToCartBtn" button
        Then Added products count should be visible on basket icon
        And User clicks "basketBtn" button
        Then Added products should be visible in basket page

    @RemoveFromBasket
      Scenario: Remove product from basket
      Given User is in "inventoryPage"
      When User selects any product
      And User clicks "addToCartBtn" button
      And User clicks "basketBtn" button
      And User find "Remove Item" button by text and click
      Then User should get "No Items" message

    @Checkout
      Scenario: Checkout product
      Given User is in "inventoryPage"
      When User selects any product
      And User clicks "addToCartBtn" button
      And User clicks "basketBtn" button
      And User clicks "proceedCheckoutBtn" button
      And User fills "Orxan" in "fullName" input field
      And User fills "Xudu Mammadov" in "addressLine" input field
      And User fills "Baku" in "city" input field
      And User fills "111" in "zipCode" input field
      And User fills "Azerbaijan" in "country" input field
      And User clicks "toPaymentBtn" button
      And Waiting 5 seconds for visibility "fullName" element
      And User fills "Orxan" in "fullName" input field
      And User fills "5258694535269854" in "cardNumber" input field
      And User fills "03/25" in "expirationDate" input field
      And User fills "100" in "securityCode" input field
      And User clicks "reviewOrderBtn" button
      And User clicks "placeOrderBtn" button
      Then User should get "Checkout Complete" message

#      @SortProducts
#        Scenario Outline: Sort products by "<sortType>"
#        Given User is in "inventoryPage"
#        When User clicks "sortProductBtn" button
#        And User selects "<sortType>" by text
#        Then Products order should by "<sortType>"
#
#        Examples:
#          | sortType           |
#          | Name - Ascending   |
#          | Name - Descending  |
#          | Price - Ascending  |
#          | Price - Descending |

        