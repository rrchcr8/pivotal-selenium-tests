@bvt
Feature: Project

  Scenario: create new project
    Given user creates a default project as
      | name    | awt-04  |
      | account | davaid  |
      | privacy | private |
    Then validate the project is created with specify name

