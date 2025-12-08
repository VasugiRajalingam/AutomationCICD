Feature: Place an order in Ecomm app

  Background:
    Given Launch the application

  @Order
  Scenario Outline: Submit an order
    Given Logged in to app using <username> and <password>
    When Add a <productName> to cart
    And checkout the <productName> and submit an order
    Then verify "Thankyou for the order." message is displaying
    Then close browser

    Examples:
      | username            | password      | productName     |
      | vasugiraj@gmail.com | VamiSash@2025 | ADIDAS ORIGINAL |