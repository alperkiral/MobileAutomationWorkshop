@runAll
Feature: Ebebek Shopping Automation

  Background:
    Given Setup the driver

  @run
  Scenario: Completing shopping steps
    Given the notification popup is denied.
    When the user clicks category button
    And the user selects Bebek Odasi category
    And the user selects Bebek Odasi Aksesuar sub category
    And the user filters the price range betwen 50 to 100
    And the user selects sorting by most reviewed
    And the user adds the first product to basket
    And the user goes to basket
    And the user increases the amount by one
    Then the price should be matched with the current amount
    Then the user clicks checkout button


