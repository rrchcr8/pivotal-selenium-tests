@bvt
Feature: Project


  Scenario: Create new project
    When user creates a default project as
      | name    | awt-04  |
      | account | david   |
      | privacy | private |
    Then validate the project is created with specify name

  Scenario: Edit project
    Given an existing project known as "awt-04" that user intends to edit
    And change values on form as
      | title       | awt-05               |
      | description | For testing purpose  |
      | account     | david                |
      | taskEnable  | Allow                |
      | privacy     | public               |
    When user press save button
    Then A successful message is displayed


  Scenario: Delete a project
    Given an existing project named as "awt-04" that user intends to delete
    And open project's settings
    When user click over delete project link
    And Agree to delete
    Then The project no longer appear on projects section

