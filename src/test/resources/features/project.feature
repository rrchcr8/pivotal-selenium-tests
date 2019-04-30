Feature: Project

  Background:
    Given logs in with user "owner1"
    And goes to Dashboard "Project"

  Scenario: Create new project from Dashboard
    Given clicks on create new project button
    When creates project as
      | name    | at-01   |
      | account | one     |
      | privacy | private |
    And Set "project_response"
    Then validates "at-01" name on project's header title
    And validate creation on header project's list
    And validate creation on project's section
    And sends a DELETE request "/projects/{project_response.id}"

#  Scenario: Create new project from header menu
#    Given A create new button on header menu
#    When creates project as
#      | name   | at-02 |
#      | account | two   |
#    Then validate creation on project's dashboard
#    And validate creation on header project's list
#    And validate creation on project's section

#  Scenario: Create new project from project's section
#    Given An option to create a new project on project's section
#    When user creates project as
#      | title   | at-03  |
#      | account | three  |
#      | privacy | public |
#    Then validate creation on project's dashboard
#    And validate creation on header project's list
#    And validate creation on project's section
#
