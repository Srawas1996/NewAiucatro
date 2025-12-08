
  Feature: Knowledge Base

    Scenario: Creating Folder
      Given the user is logged in to the website
      When the user navigates to the Bot section and selects a Bot
      And The user Clicks On The Knowledge Base
      Then Click on Create Folder
      And Fill all the information and click save

    Scenario: Added new Doc file inside the Folder
      Given the user is logged in to the website
      When the user navigates to the Bot section and selects a Bot
      And The user Clicks On The Knowledge Base
      Then Click on add Data Source
      And The user select the folder location
      When the user click save
      Then the user should check if the folder is uploaded

    Scenario: Check The folder is exist
      Given the user is logged in to the website
      When the user navigates to the Bot section and selects a Bot
      And The user Clicks On The Knowledge Base
      Then The user clicks on the folder


    Scenario: Search bar for exist Knowledge Base
      Given the user is logged in to the website
      When the user navigates to the Bot section and selects a Bot
      And The user Clicks On The Knowledge Base
      Then search for exist Knowledge base
      And check the knowledge base is exist


    Scenario: Search For non exist Knowledge Base
      Given the user is logged in to the website
      When the user navigates to the Bot section and selects a Bot
      And The user Clicks On The Knowledge Base
      Then search for None exist Knowledge base
      And check the Icon is appear


    Scenario: Delete Creation Folder
      Given the user is logged in to the website
      When the user navigates to the Bot section and selects a Bot
      And The user Clicks On The Knowledge Base
      When The User click on Delete Icon
      Then Click on Confirmation Delete and Check the validation message