Feature: New Workspace

  Background: testy
    Given logs in with user "owner1"

  Scenario: New Workspace
    When clicks the create workspace button
    And sets the workspace name: "Nuevo nombre"

    Then the workspace should be created: "Nuevo nombre"
    And the workspace board should be displayed