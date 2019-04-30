@wip
Feature: Creating workspaces

  Background:
    Given logs in with user "owner1"
    And goes to dashboard "Workspaces"

  @SoftAssert
  Scenario: Create new workspace from dashboard
    Given clicks on create workspace button
    When creates a workspace
      | workspace.name | From Dashboard |
    Then validates "workspace.name" on header title
    And opens the popover from header title
    And validates "workspace.name" on "Workspaces" group list
    And goes to dashboard "Workspaces"
    And validates "workspace.name" on "Workspaces" dashboard tab
    #And sends a DELETE request "/my/workspace/{workspace.id}"
    And asserts all

  @SoftAssert
  Scenario: Create new workspace from projects section
    Given clicks on create workspace button
    When creates a workspace
      | workspace.name | From Projects |
    Then validates "workspace.name" on header title
    And opens the popover from header title
    And validates "workspace.name" on "Workspaces" group list
    And goes to dashboard "Workspaces"
    And validates "workspace.name" on "Workspaces" dashboard tab
    #And sends a DELETE request "/my/workspace/{workspace.id}"
    And asserts all

  @SoftAssert
  Scenario: Create new workspace from header menu section
    Given clicks on create workspace button
    When creates a workspace
      | workspace.name | From header |
    Then validates "workspace.name" on header title
    And opens the popover from header title
    And validates "workspace.name" on "Workspaces" group list
    And goes to dashboard "Workspaces"
    And validates "workspace.name" on "Workspaces" dashboard tab
    #And sends a DELETE request "/my/workspace/{workspace.id}"
    And asserts all

