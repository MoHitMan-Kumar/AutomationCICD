Feature: Error Validation
    I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Incorrect Login Validation
    Given I landed on Ecommerce Page
    When Logged in with username "<email>" and password "<password>"
    Then "Incorrect email or password." message is displayed

    Examples:
      | email               | password          |
      | mohitmb02@gmail.com | hit@RahulAcademy1 |
