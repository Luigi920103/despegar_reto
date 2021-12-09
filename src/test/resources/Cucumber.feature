Feature: Round trip flight
  In order to create a round trip flight
  As a normal user
  I want to see the complete data page after choose the flights


  Scenario Outline: Round trip Creation - confirm data page
    Given the user choose the <Navigator>
    And the user creates a round-trip flight
    And the user selects the number of travelers <adults>,with the class <class>
    And the user selects the <departure_city> with the <destination_city>
    And the user selects the gaps: for the departure day <months_gap>, <days_gap>,for the comeback flight <comeback_days_gap>
    When  the user selects the <package_option> with the <departure_option>, <comeback_option>
    Then The new page shows the <message>

    Examples:
      |Navigator|adults|class|departure_city|destination_city|months_gap|days_gap|comeback_days_gap|package_option|departure_option|comeback_option|message|
      |"chrome"|"1"|"Premium economy"|"MEDE"|"bBOG"|"1"|"4"|"7"|"1"|"1"|"1"|"¡Falta poco! Completa tus datos y finaliza tu compra"|