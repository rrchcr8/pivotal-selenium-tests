@wip
Feature: New Workspace

  Background: testy
    Given logs in with user "owner1"

 # Test case 1
  Scenario: New Workspace
    When clicks the create workspace button
    And sets the workspace name: "Nuevo nombre"

    Then the workspace should be created: "Nuevo nombre"
    And the workspace board should be displayed


    # Test Case 2
  Scenario:  workspace can be edited.

    Given an workspace
    When the user clicks on workspace settings button
    And edit workspace’s title
    Then workspace title should be edited


 # Test Case 3
  Scenario:  workspace can be deleted.

    Given an workspace
    When the user clicks on workspace settings button
    And click delete workspace
    And click delete confirm
    Then workspace should be deleted

