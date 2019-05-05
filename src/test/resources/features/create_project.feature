Feature: Project

  Background:
    Given logs in with user "owner1"
    And goes to dashboard "Project"

  Scenario: Create new project from Dashboard
    Given clicks on create new project button
    When creates project as
      | name    | at-01   |
      | account | one     |
      | privacy | private |
    And stores datatable as "project_datatable"
    Then validates "at-01" name on project's header title
    And validate creation on header project's list
    And validate creation on project's section
    And stores "project_id" that matches with "project_datatable.name" from "/projects"
    And sends a DELETE request "/projects/{project_id}"

  Scenario: Create new project from header menu
    Given clicks new button on header menu
    When creates project as
      | name    | at-02 |
      | account | two   |
    And stores datatable as "project_datatable"
    Then validates "at-02" name on project's header title
    And validate creation on header project's list
    And validate creation on project's section
    And stores "project_id" that matches with "project_datatable.name" from "/projects"
    And sends a DELETE request "/projects/{project_id}"

  Scenario: Create new project from project's section
    Given An option to create a new project on project's section
    When creates project as
      | name    | at-03   |
      | account | three   |
      | privacy | private |
#    And get project id
#    And set "project_id"
#    And saves data "project_id"
#    And saves "project_id | workspace_id" from url
#    And sends a GET request "/projects/{project_id}"
#    And stores response as "project_response"
    And stores datatable as "project_datatable"
#    Then validates "project_datatable.name" name on project's header title
#    And validate creation "project_datatable.name" on header project's list
#    And validate creation "project_datatable.name" on project's section
    And stores "project_id" that matches with "project_datatable.name" from "/projects"
    And sends a DELETE request "/projects/{project_id}"


