#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
Feature: Validate product in Basket is present or not
  In Order to validate that
  I Want to add product into Basket page

  Scenario: Verify product should be present after login
    Given Open Browser and user on the "https://www.amazon.com/" page
    When Title should be same
    Then user click Sign in button
    Then user enter the "twetwetwet@dfgd.com" as mailid
    Then user click continue button
    Then user should be see error message "We cannot find an account with that email address"
    Then user enter the "mahaautomation2011@gmail.com" as mailid
    Then user click continue button
    Then user enter the "password" as password
    Then user click signin button
    Then user should be see error message "Your password is incorrect"
    Then user enter the "mahaautomation123" as password
    Then user click signin button
    Then user should be see homepage
    Then user enter "iphone 8 64 GB" in Search page
    Then user click Search button
    Then user Collect Product Name and Price
    Then user select the product
    Then user click Add to Cart Button
    Then user check added product in product page "Added to Cart"
    Then user click Cart button
    Then user check added product in basket page
    Then user logout
    Then user enter the "mahaautomation2011@gmail.com" as mailid
    Then user click continue button
    Then user enter the "mahaautomation123" as password
    Then user click signin button
    Then user click Cart button
    Then user check added product in basket page
    Then user logout
    And Close Browser
