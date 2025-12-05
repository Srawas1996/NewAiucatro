
  Feature: Chat Interface

    Scenario: Check the response back from the bot (Bau)
      Given the user is logged in to the website
      And the user navigates to the Bot section and selects a Bot
      And the user clicks on Integration and then System
      When the user Navigate to the chat Interface
      Then The user ask any question

    Scenario: Check the response back from the bot (Canvas)
      Given the user is logged in to the website
      When the user navigates to the Bot section and selects a Bot Canvas
      When the user Navigate to the chat Interface Canvas
      Then The user ask any question Canvas
