@wip
Feature: edition of workspaces

  Background:
    Given sends a POST request "/workspaces"
      | name | original workspace |
    And stores response as "workspace_response"
    And logs in with user "owner1"
    And goes to dashboard "Workspaces"

  Scenario: workspace can be edited from Workspaces dashboard.

    Given the settings page from a particular workspace
      | name | workspace_response.name |
    When edits attributes of the workspace
      | name | random name |
    And saves data on context
      | id   |
      | name |
    Then a "Changes saved." message should be displayed
    And validates "workspace.name" on header title
    And opens the popover from header title
    And validates "workspace.name" on "Workspaces" group list
    And goes to dashboard "Workspaces"
    And validates "workspace.name" on "Workspaces" dashboard tab
    And sends a DELETE request "/workspace/{workspace.id}"


  Scenario: workspace can be edited from workspace home.
    Given the workspace home
      | name | workspace_response.name |
    And clicks on "MORE" tab on header menu
    When edits attributes of the workspace
      | name | random name |
    And saves data on context
      | id   |
      | name |
    Then validates presence on workspace home
    Then validates "workspace.name" on header title
    And opens the popover from header title
    And validates "workspace.name" on "Workspaces" group list
    And goes to dashboard "Workspaces"
    And validates "workspace.name" on "Workspaces" dashboard tab
    And sends a DELETE request "/workspace/{workspace.id}"
