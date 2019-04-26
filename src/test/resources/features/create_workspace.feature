@wip
Feature: Creating workspaces

  Background:
    Given logs in with user "owner1"
    And Go to Dashboard "Workspace"

  Scenario: Create new workspace from dashboard
    Given a create workspace button on workspace tab
    When user creates a workspace
      | title | from bab workspace |
    And Set "workspace_response.id"
    Then validates creation on workspace home
    And Go to Dashboard "Workspace"
    And validates creation on workspace dashboard
    And opens the popover
    And validates creation on header workspace's list
    And opens all the workspaces
    And validates creation on workspace's section
    And sends a DELETE request "/workspace/{workspace_response.id}"

  Scenario: Create new workspace from projects section
    Given a create workspace link on projects section
    When user creates a workspace
      | title | from projects workspace |
    Then validate creation on workspace dashboard
    And validate creation on header workspace's list
    And validate creation on workspace's section
    And Set "workspace_response.id"
    And sends a DELETE request "/workspace/{workspace_response.id}"

  Scenario: Create new workspace from header menu section
    Given a create workspace link on header menu section
    When user creates a workspace"
      | title | from projects workspace |
    Then validate creation on workspace dashboard
    And validate creation on header workspace's list
    And validate creation on workspace's section
    And Set "workspace_response.id"
    And sends a DELETE request "/workspace/{workspace_response.id}"
