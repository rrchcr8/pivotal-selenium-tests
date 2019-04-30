Feature: Edit and delete a Project

  Background:
    Given sends a POST request "/projects"
    """
    {"name":"a1"}
    """
    And stores response as "project_response"
    And logs in with user "owner1"
    And goes to Dashboard "project"


  Scenario: Delete a project
    Given Opens a "project_response.name" settings
    When clicks delete project link
    Then The project no longer appear on projects section
    And the project is not present on active project


  Scenario: Edit a project
    Given Opens a "project_response.name" settings
    And change values on form as
      | name        | awt-00              |
      | description | For testing purpose |
      | taskenable  | Disable             |
    Then A successful message is displayed
    And Previous project's name no longer listed
    And The project no longer appear on projects section
    And the project is not present on active project
    And sends a DELETE request "/projects/{project_response.id}"
