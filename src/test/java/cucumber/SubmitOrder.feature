Feature: Purchase Order from E-commerce Website
  I want to use this template for my feature file

Background:
    Given I landed on Ecommerce Page

@SubmitOrder
  Scenario Outline: Positive Test for submitting an order
    Given Logged in with username "<email>" and password "<password>"
    When I add product "<productName>" to the cart
    And checkout "<productName>" and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on the confirmation page

    Examples:
      | email                        | password            | productName     |
      | mohitmb02@gmail.com          | Mohit@RahulAcademy1 | ZARA COAT 3     |
      | iamsidhartha.kumar@gmail.com | SidPassword@1       | ADIDAS ORIGINAL |
