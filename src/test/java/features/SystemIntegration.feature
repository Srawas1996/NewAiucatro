

Feature: System Integration

  Background:
    Given the user is logged in to the website
    And the user navigates to the Bot section and selects a Bot
    And the user clicks on Integration and then System

  Scenario: Delete System Connection
    Then Click on the Delete Button
    And Click Confirm
    And Check the Delete Message

  Scenario: Cancel adding a new system connection
    And the user clicks on Add New Connection
    And the user fills in all required fields with valid information
    Then the user clicks cancel

  Scenario: Successfully create a new system connection
    And the user clicks on Add New Connection
    And the user fills in all required fields with valid information
    And the user submits the form
    And the user selects the Semester from the dropdown
    Then the system should create the new connection successfully
    And the user should see the newly created connection listed in the System Connections table

  Scenario: View system connection details
    Then The user check if the details are exist in the connection

  Scenario: Search system with exist connections by keywords
    Then the user search for exist Connection

  Scenario: Edit system connection
    And the user clicks on Edit System Connection
    When the user modifies one or more field values
    And the user edit selects the Semester from the dropdown
    Then a confirmation or validation popup message should appear
    And the user should see the edited created connection listed in the System Connections table

  Scenario: Check the Duplicate Validation error message
    And the user clicks on Add New Connection Duplicate
    And the user fills in all required fields with valid information
    And the user submits the formTwo
    Then the user check the duplicate Message

  Scenario: Check the Validation error message
    And the user clicks on Add New Connection Duplicate
    Then Leave the fields Empty

  Scenario: SwitchFromDisconnectToConnect
    Then The user Switch the system to Disconnect System

  Scenario: SwitchFromConnectToDisconnect
    Then The user Switch the system to connect System

  Scenario: Search system with none exist connections by keywords
    Then the user search for None exist Connection

  Scenario: Validate Semester Dropdown Values are not exist when the connection is wrong
    And the user clicks on Add New Connection Duplicate
    And the user fills in all required fields with Invalid information
    And the user submits invalid form
    And the user Check if there Semesters that are exist

