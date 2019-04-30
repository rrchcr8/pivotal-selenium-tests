Feature: Story second part
  Test cases with common background.

  Background:
    Given sends a POST request "/projects"
      | name | a1 |
    And stores response as "project_response"
    And sends a POST request "/projects/{project_response.id}/stories"
      | name | story1 |
    And stores response as "story_response"
    And logs in with user "owner1"
    And goes to Dashboard "project"
    And opens a project "project_response.name"

  Scenario: delete a story using delete button inside story page
    When expands the story "story1"
    And clicks delete button
    And clicks confirm delete button
    Then verifies that the story "story_response.name" is deleted
    And sends a DELETE request "/projects/{project_response.id}"


  Scenario: delete a story selecting bulk
    When selects the bulk of "story_response.name"
    And clicks delete button of Header container
    And clicks confirm delete button
    Then verifies that the story "story_response.name" is deleted
    And sends a DELETE request "/projects/{project_response.id}"
