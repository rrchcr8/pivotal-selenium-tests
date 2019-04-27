@wip
Feature: Deletion of workspaces

  Background:
    Given sends a POST request "/workspaces"
      | name | trash workspace |
    And stores response as "workspace_response"
    And logs in with user "owner1"
    And goes to dashboard "Workspaces"

  Scenario: Workspace can be deleted from projects section.

    Given the settings page from the particular workspace
      | name | workspace_response.name |
    When clicks on delete workspace link
    Then a "{workspace_response.name} was successfully deleted." message should be displayed
    And validates "workspace.name" on header title
    And opens the popover from header title
    And validates "workspace.name" on "Workspaces" group list
    And goes to dashboard "Workspaces"
    And validates "workspace.name" on "Workspaces" dashboard tab
    And sends a DELETE request "/workspace/{workspace.id}"

  Scenario: Workspace can be deleted from its dashboard.

    Given the settings workspace page from its dashboard
      | name | workspace_response.name |
    When clicks on delete workspace link
    Then a "{workspace_response.name} was successfully deleted." message should be displayed
    And validates "workspace.name" on header title
    And opens the popover from header title
    And validates "workspace.name" on "Workspaces" group list
    And goes to dashboard "Workspaces"
    And validates "workspace.name" on "Workspaces" dashboard tab
    And sends a DELETE request "/workspace/{workspace.id}"
