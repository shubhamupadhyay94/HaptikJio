Feature: Verify right watch is added into cart

  Background: Launch the amazon url
    Given I am on the amazon home page


  Scenario: As a amazon user, I should be able to add watch in cart and proceed to checkout page
    And I search for "fossil watch for women" in search box
    And I select price range between 10000 and 20000
    And I verify search mobile price range is between 10000 and 20000
    And I select band material as "silicone"
    And I click on first watch from searched list
    And I verify the band material in new open tab
    And I add the watch in cart
    And I verify the right watch is added into cart
    When I click on proceed to buy
    Then I should be on amazon login page


