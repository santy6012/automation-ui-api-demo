@customer-shopping @smoke
Feature: Customer order placement
  As a customer,  should be able explore products & place order for desired items.

  @phone-navigation
  Scenario: Should be navigate to phone product categories
    Given I am on home page
    When I click on PHONE side tab
    Then I should be able to see PHONE section

  @laptop-navigation
  Scenario: Should be navigate to laptop product categories
    Given I am on home page
    When I click on LAPTOP side tab
    Then I should be able to see LAPTOP section

  @monitor-navigation
  Scenario: Should be navigate to monitor product categories
    Given I am on home page
    When I click on MONITOR side tab
    Then I should be able to see MONITOR section

  @place-order
  Scenario: Should be able to place order for laptop
    Given I am in LAPTOP section
    When I add Sony vaio i5 in cart
    And I click on LAPTOP side tab
    And I add Dell i7 8gb in cart
    And I go to CART
    And I delete Dell i7 8gb from cart
    And I place order
    Then purchase details should be correct