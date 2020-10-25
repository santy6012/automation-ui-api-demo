@pets @smoke
Feature: Pets storage stock
  As a storage admin, I can add, update, remove items in store.

  @get-pet-detail
  Scenario: Should be able to get available pets from store
    Given I am authorized to manage store resources
    When I make request to GET details for available pets
    Then I should be able to get pets with available status only

  @create-new-pet
  Scenario: Should be able to add new pets into store
    Given I am authorized to manage store resources
    When I make request to CREATE a new pet
    Then I should be able to create a new pet

  @update-existing-pet
  Scenario: Should be able to update existing pets into store
    Given I am authorized to manage store resources
    When I make request to UPDATE status as sold for a pet
    Then pet status is changed to sold

  @delete-existing-pet
  Scenario: Should be able to delete existing pets into store
    Given I am authorized to manage store resources
    When I make request to DELETE existing pet
    Then I should be able to delete the pet
