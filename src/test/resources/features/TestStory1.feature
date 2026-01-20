Feature: User Account Management on Automation Exercise Website



  Scenario: Full Registration & Deletion
    Given Launch browser and go to 'http://automationexercise.com'
    Then I verify that home page is visible successfully
    When I click on Signup_Login button
    Then I verify that 'New User Signup' text is displayed on the page
    And Enter Name and Email address and click 'Signup'
   
