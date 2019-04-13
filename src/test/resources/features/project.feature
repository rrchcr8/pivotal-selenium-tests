@bvt
Feature: Project

  Background:
    Given User is located on main page

  Scenario: Create new project
    When user creates a default project as
      | name    | awt-22 |
      | account | aleri  |
      | privacy | public |
    Then validate the project is created with specify name

  Scenario: Edit project
    Given an existing project known as "awt-55" that user intends to edit
    And change values on form as
      | title       | awt-55-2             |
      | description | For testing purpose  |
      | account     | david                |
      | taskEnable  | Allow                |
      | privacy     | public               |
    Then A successful message is displayed


  Scenario: Delete a project
    Given an existing project named as "awt-01" that user intends to delete
    And open project's settings
    When user click over delete project link
    Then The project no longer appear on projects section

