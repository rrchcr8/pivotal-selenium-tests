Feature: Story second part
  Test cases with common background.

  Background:
    Given logs in with user "owner1"
    And sends a POST request "/projects"
      | name | a1 |
    And stores response as "project_response"
    And sends a POST request "/projects/{project_response.id}/stories"
      | name | story1 |
    And stores response as "story_response" on context
    And Go to Dashboard "project"

  Scenario: delete a story using delete button inside story page
    When opens a project "project_response.name"
    #expands the story "story_response.name"
    And expands the story "story_response.name"
    And clicks delete button
    And clicks confirm delete button
    Then verifies that the story "story_response.name" is deleted
    And sends a DELETE request "/projects/{project_response.id}"

  Scenario: delete a story selecting bulk
    When opens a project "project_response.name"
    And selects the bulk of "story_response.name"
    And clicks delete button of Header container
    And clicks confirm delete button
    Then verifies that the story "story_response.name" is deleted
    And sends a DELETE request "/projects/{project_response.id}"