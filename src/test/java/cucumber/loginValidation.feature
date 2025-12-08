Feature: Login into Ecomm app

  @Login
  Scenario Outline: Submit an order
    Given Launch the application
    Given Logged in to app using <username> and <password>
    Then "Incorrect email or password." message is displaying
    Then close browser

    Examples:
      | username             | password      |
      | vasugiraj@gmail.com  | VamiSash@2025 |
      | vasugiraj@gmail.com  | VamiSash@2024 |
      | vasugisash@gmail.com | VasuSash@2025 |