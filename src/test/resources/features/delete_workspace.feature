@wip
Feature: Deletion of workspaces

  Background:
    Given sends a POST request "/workspaces"
      | name | trash workspace |
    And stores response as "workspace_response"
    And logs in with user "owner1"
    And goes to dashboard "Workspaces"

  Scenario: Workspace can be deleted from workspace tab on dashboard.

    Given the settings page from the particular workspace
      | name | workspace_response.name |
    When clicks on delete workspace link
    Then a "{workspace_response.name} was successfully deleted." message should be displayed
    And opens the popover from header title
    And validates "workspace_response.name" on "Workspaces" group list
    And goes to dashboard "Workspaces"
    And validates "workspace_response.name" on "Workspaces" dashboard tab
    And sends a DELETE request "/workspace/{workspace.id}"

  Scenario: Workspace can be deleted from its home.
    Given the workspace home
      | name | workspace_response.name |
    And clicks on "more" tab on header menu
    When clicks on delete workspace link
    Then a "{workspace_response.name} was successfully deleted." message should be displayed
    And opens the popover from header title
    And validates "workspace_response.name" not listed on "Workspaces" group list
    And goes to dashboard "Workspaces"
    And validates "workspace_response.name" not listed on "Workspaces" dashboard tab
    And sends a DELETE request "/workspace/{workspace.id}"
