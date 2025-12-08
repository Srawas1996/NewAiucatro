
Feature: API Integration
  As a user
  I want to manage API connections
  So that I can configure, test, and sync integrations properly

  Background:
    Given the user is logged in to the website
    And the user navigates to the Bot section and selects a Bot
    And the user clicks on Integration and then System

  Scenario: User opens an API connection successfully
    When the user click on the Connection Card
    Then the connection name should be displayed
    And all action buttons should be enabled

  Scenario: The User is able to Clear the APis
    Then the user click on the Connection Card
    Then The user Click on Clear On the Api Page
    And The user checks if it the api is UnChecked

  Scenario: The User is able to select all the APis
    Then the user click on the Connection Card
    Then The user Click on Select All
    And The user checks if it the api is checked

  Scenario: Search for exist API
    Then the user click on the Connection Card
    Then The user search for exist api connection

  Scenario: The user should be able to Test the connection
    Then the user click on the Connection Card
    And The user Check if the APi is in a untested status
    Then The user Click on The Test Connection
    Then the user Check if the api is in a healty status

  Scenario: Open the Api Fields
    Then the user click on the Connection Card
    And The open the Api Field
    Then The user checks if all the fields are checked

  Scenario: Change the Frequent To daily for one of the api
    Then the user click on the Connection Card
    Then The User Change to the Daily Frequent

  Scenario: Click on Force Sync for one of the API
    Then the user click on the Connection Card
    Then the User is Click on Force Sync and check the Validation
    Then the user check if the Data Table is appear

  Scenario: switch to APi Logs
    Then the user click on the Connection Card
    Then the User Move To API Logs

  Scenario: Check the buttons are enable
    Then the user click on the Connection Card
    Then the User Move To API Logs
    Then The User Checks if Refresh Token is enable

  Scenario: Make sure that the the field are saved
    And the user click on the Connection Card
    And The open the Api Field
    And the user unchecks any field
    And the user saves the changes
    When the user opens the same API again
    Then the field should still be unchecked

  Scenario: Make Sure That the SelectAll And Clear are work Correctly
    And the user click on the Connection Card
    And The open the Api Field
    When The User Click on Clear
    Then the field should still be unchecked
    When The user Click on Select All Inside Fields
    Then The User Should Check all fields
    When The User Click Cancel and reopen the changes should not saved
    When the user opens the same API again
    Then the field should still be unchecked

  Scenario: Make sure that the button is disable when the Connection is disconnect
    Then The user Switch the system to Disconnect System
    Then the user click on the Connection Card
    Then The user check if the buttons are disable

