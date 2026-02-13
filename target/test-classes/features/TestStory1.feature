Feature: User Account Management on Automation Exercise Website



  Scenario: Full Registration & Deletion
    Given Launch browser and go to 'http://automationexercise.com'
    Then I verify that home page is visible successfully
    When I click on Signup_Login button
    Then I verify that 'New User Signup' text is displayed on the page
    And Enter Name and Email address and click 'Signup'
    Then Fill Title, Name, Email, Password, Date of Birth
    When Select Newsletter and Special Offers checkboxes
    Then Fill Address, Country, State, City, Zipcode, Mobile
    And Click 'Create Account' button
    Then Click 'Continue' button
    When Click 'Delete Account' button
    Then Click 'Continue to Delete' button

  Scenario: Login User with correct email and password
    Then I verify that home page is visible successfully
    When I click on Signup_Login button
    And  Verify 'Login to your account' is visible
    When  Enter correct email address and password 
    Then  Click 'login' button
    And Verify that 'Logged in as username' is visible
    When Click 'Delete Account' button
    Then Click 'Continue to Delete' button
    
  



   
