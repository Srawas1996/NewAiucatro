Feature: Login Page


  Scenario Outline: Invalid login
    Given user open website and login to the systems
    When User fill email as "<username>" and "<password>" and click login
    Then Error message should appear

    Examples:
      |     username         |         password        |
      |     MZ_UAT         |         12345679        |
      |     MZ_UAT33       |         12345678        |
      |     MZ_UAT333     |         12345679        |

  Scenario: valid login
    Given user open website and login to the systems
    When user fill the data
    Then Validate you are in the dashboard