
  Feature: Chat Interface

    Background:
      Given the user is logged in to the website
      And the user navigates to the Bot section and selects a Bot
      And the user clicks on Integration and then System

    Scenario: Check the response back from the bot
      When the user Navigate to the chat Interface
      Then The user ask any question
