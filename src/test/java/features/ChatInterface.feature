
  Feature: Test The Chat Interface
    Scenario: Make sure that the messages contain the keywords
      Given the user is logged in to the website
      When the user navigates to the Bot section and selects a Bot
      Then the user navigates to the chat interface
      When the chat interface is open
      Then the user ask any question
      When the bot response back
      Then Make sure that the response contain some keywords
