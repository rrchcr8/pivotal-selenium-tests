@wip
Feature: Deletion of workspaces

  Background:
    Given sends a POST request "/workspaces"
      | title | trash workspace |
    And stores response as "workspace_response"
    And logs in with user "owner1"
    And goes to dashboard "Workspaces"

  Scenario: Workspace can be deleted from projects section.

    Given the settings page from the particular workspace
      | title | workspace_response.name |
    When clicks on delete workspace link
    Then a deletion success message should be displayed
    And goes to dashboard "Workspaces"
    And validates deletion on workspace section
    And opens the popover from header title
    And validates deletion on header workspaces column

  Scenario: Workspace can be deleted from its dashboard.

    Given the settings workspace page from its dashboard
      | title | workspace_response.name |
    When clicks on delete workspace link
    Then a deletion success message should be displayed
    And goes to dashboard "Workspaces"
    And validates deletion on workspace section
    And opens the popover from header title
    And validates deletion on header workspaces column
