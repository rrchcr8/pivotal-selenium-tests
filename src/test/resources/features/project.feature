Feature: Project

  Background:
    Given logs in with user "owner1"
    And Go to Dashboard "Project"

  Scenario: Create new project from Dashboard
    #Given Clicks on create new project button
    Given A create new button on dashboard
    When user creates project as
      | title   | at-01   |
      | account | one     |
      | privacy | private |
    #Then validate "at-01" name on project's header title
    Then validate creation on project's dashboard
    And validate creation on header project's list
    And validate creation on project's section


  Scenario: Create new project from header menu
    Given A create new button on header menu
    When user creates project as
      | title   | at-02 |
      | account | two   |
    Then validate creation on project's dashboard
    And validate creation on header project's list
    And validate creation on project's section

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
