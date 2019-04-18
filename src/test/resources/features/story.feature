@wip
Feature: Story

  Background:
    Given sends a POST request "/projects"
      | name | a1 |
    And stores response as "project_response"

  Scenario: create a story with the minimum required field
    Given logs in with user "owner1"
    And Go to Dashboard
    And opens a project "project_response.name"
    When creates a story called "carlos test"
    Then verify the story is created
    And send a DELETE request "/projects/{project_response.id}"


#  Scenario: delete a story using delete button
#    Given sends a POST request "/projects/{project_response.id}/stories"
#      | name | story1 |
#    And stores response as "story_response"
#    And Go to Dashboard
#    And opens a project "project_response.name"
#    When selects the dropdown button of the story "story_response.name"
#    #And click delete button
#    Then Verify that the story "story_response.name" is deleted
#    And send a DELETE request "/projects/{project_response.id}"
#
#  Scenario: delete a story using checkbox button
#    Given sends a POST request "/projects/{project_response.id}/stories"
#      | name | story2 |
#    And stores response as "story_response2"
#    And Go to Dashboard
#    And opens a project "project_response.name"
#    When deletes selecting the checkboxof "story_response2.name"
#    Then Verify that the story "story_response2.name" is deleted
#    And send a DELETE request "/projects/{project_response.id}"

