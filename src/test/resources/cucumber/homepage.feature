Feature: verify homepage elements

  Scenario: verify slide elements
    #Given I am at the homepage
    Then I should see these elements
      | Boutique   |
      | Realme     |
      | Immobilier |
@dze
  Scenario: verify side menu elements
    #Given I am at the homepage
    Then I should see these elements on side Menu
      | Sport   |
      | Emploi     |
      | Informatique |
    And I click on "Service"