@wip
Feature: edition of workspaces

  Background:
    Given sends a POST request "/workspaces"
      | title | original workspace |
    And stores response as "workspace_response"
    And logs in with user "owner1"
    And goes to dashboard "Workspaces"

  Scenario: workspace can be edited from Workspaces dashboard.

    Given the settings page from the particular workspace
      | title | workspace_response.name |
    When the user edit attributes from the workspace
      | title | edited workspace |
    Then a success "saves changed" message should be displayed
    And validates creation on workspace home
    And opens the popover from header title
    And validates creation on header workspaces column
    And goes to dashboard "Workspaces"
    And validates creation on Workspaces section
    And sends a DELETE request "/workspace/{workspace_response.id}"


  Scenario: workspace can be edited from workspace home.
    Given the workspace home
      | title | workspace_response.name |
    And "more" tab on header
    When the user edit attributes from the workspace
      | title | edited workspace |
    Then a success "saves changed" message should be displayed
    And validates creation on workspace home
    And opens the popover from header title
    And validates creation on header workspaces column
    And goes to dashboard "Workspaces"
    And validates creation on Workspaces section
    And sends a DELETE request "/workspace/{workspace_response.id}"
