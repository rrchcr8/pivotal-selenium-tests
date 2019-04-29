@wip
Feature: Creating workspaces

  Background:
    Given logs in with user "owner1"
    And goes to dashboard "Workspaces"

  Scenario: Create new workspace from dashboard
    Given clicks on create workspace button
    When creates a workspace
      | name | from tab workspace |
    And saves data on context
      | id   |
      | name |
    Then validates "workspace.name" on header title
    And opens the popover from header title
    And validates "workspace.name" on "Workspaces" group list
    And goes to dashboard "Workspaces"
    And validates "workspace.name" on "Workspaces" dashboard tab
    And sends a DELETE request "/workspace/{workspace.id}"


  Scenario: Create new workspace from projects section
    Given clicks on create workspace button
    When creates a workspace
      | name | from projects section |
    And saves data on context
      | id   |
      | name |
    Then validates "workspace.name" on header title
    And opens the popover from header title
    And validates "workspace.name" on "Workspaces" group list
    And goes to dashboard "Workspaces"
    And validates "workspace.name" on "Workspaces" dashboard tab
    And sends a DELETE request "/workspace/{workspace.id}"


  Scenario: Create new workspace from header menu section
    Given clicks on create workspace button
    When creates a workspace
      | name | from header menu |
    And saves data on context
      | id   |
      | name |
    Then validates "workspace.name" on header title
    And opens the popover from header title
    And validates "workspace.name" on "Workspaces" group list
    And goes to dashboard "Workspaces"
    And validates "workspace.name" on "Workspaces" dashboard tab
    And sends a DELETE request "/workspace/{workspace.id}"

