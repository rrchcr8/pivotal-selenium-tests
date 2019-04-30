@wip
Feature: edition of workspaces

  Background:
    Given sends a POST request "/my/workspaces"
      | name | original workspace |
    And stores response as "workspace_response"
    And logs in with user "owner1"
    And goes to dashboard "Workspaces"

  Scenario: workspace can be edited from Workspaces dashboard.

    Given the settings page from the particular workspace
      | name | workspace_response.name |
    When edits attributes of the workspace
      | workspace.name | random |
    Then a "Changes saved." message should be displayed
    And validates "workspace.name" on workspace header title
    And opens the popover from header title
    And validates "workspace.name" on "Workspaces" group list
    And goes to dashboard "Workspaces"
    And validates "workspace.name" on "Workspaces" dashboard tab
    And sends a DELETE request "/my/workspaces/{workspace_response.id}"


  Scenario: workspace can be edited from workspace home.
    Given the workspace home
      | name | workspace_response.name |
    And clicks on "more" tab on header menu
    When edits attributes of the workspace
      | workspace.name | random |
    Then a "Changes saved." message should be displayed
    And validates "workspace.name" on workspace header title
    And opens the popover from header title
    And validates "workspace.name" on "Workspaces" group list
    And goes to dashboard "Workspaces"
    And validates "workspace.name" on "Workspaces" dashboard tab
    And sends a DELETE request "/my/workspaces/{workspace_response.id}"
