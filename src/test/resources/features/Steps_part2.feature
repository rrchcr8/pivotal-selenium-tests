
Feature: Story second part
  Test cases with common background.
  Background:
    Given sends a POST request "/projects"
      | name | a1 |
    And stores response as "project_response"
    And sends a POST request "/projects/{project_response.id}/stories"
      | name | story1 |
    And stores response as "story_response"

  Scenario: delete a story using delete button
    Given logs in with user "owner1"
    And Go to Dashboard
    And opens a project "project_response.name"
    When selects the dropdown button of the story "story_response.name"
    #And click delete button
    Then Verify that the story "story_response.name" is deleted
    And send a DELETE request "/projects/{project_response.id}"

  Scenario: delete a story using checkbox button
    Given Go to Dashboard
    And opens a project "project_response.name"
    When deletes selecting the checkboxof "story_response.name"
    Then Verify that the story "story_response.name" is deleted
    And send a DELETE request "/projects/{project_response.id}"