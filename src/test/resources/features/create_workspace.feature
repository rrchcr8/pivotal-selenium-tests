@wip
Feature: Creating workspaces

  Background:
    Given logs in with user "owner1"
    And goes to dashboard "Workspaces"

  Scenario: Create new workspace from dashboard
    Given clicks on create workspace button
    When creates a workspace
      | title | from tab workspace |
    And saves "workspace.id" on context
    Then validates presence on workspace home
    Then validates "from tab workspace" on header title
    And opens the popover from header title
    And validates "from tab workspace" name on workspaces column
    And goes to dashboard "Workspaces"
    And validates creation on Workspaces section
    And sends a DELETE request "/workspace/{workspace.id}"


  Scenario: Create new workspace from projects section
    Given a create workspace link on projects section
    When user creates a workspace
      | title | from projects section |
    And saves "workspace.id" on context
    Then validates presence on workspace home
    Then validates "from projects section" on header title
    And opens the popover from header title
    And validates "from projects section" name on workspaces column
    And goes to dashboard "Workspaces"
    And validates creation on Workspaces section
    And sends a DELETE request "/workspace/{workspace.id}"

  Scenario: Create new workspace from header menu section
    Given a create workspace link on header menu section
    When user creates a workspace"
      | title | from header menu |
    And saves "workspace.id" on context
    Then validates presence on workspace home
    Then validates "from header menu" on header title
    And opens the popover from header title
    And validates "from header menu" name on workspaces column
    And goes to dashboard "Workspaces"
    And validates creation on Workspaces section
    And sends a DELETE request "/workspace/{workspace.id}"
