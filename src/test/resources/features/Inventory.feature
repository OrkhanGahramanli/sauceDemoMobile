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
        And User clicks "addToCartBtn" button
        Then Added products count should be visible on basket icon
        And User clicks "basketBtn" button
        Then Added products should be visible in basket page