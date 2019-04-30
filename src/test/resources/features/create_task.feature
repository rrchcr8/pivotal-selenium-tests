@wip
Feature: Add task test

  Background:
    Given sends a POST request "/projects"
      | name | task project |
    And stores response as "project_response"
    And sends a POST request "/projects/{project_response.id}/stories"
      | name | task story |
    And stores response as "story_response"
    And logs in with user "owner1"
    And goes to dashboard "Projects"
    And opens a project "project_response.name"
    And expands the story "story_response.name"

  Scenario: Create a task on existing Story
    When adds a task to current Story
      | name | First Task |
    Then validates task aggregation
#    And validates task counter
#      | operation | increment |
    And deletes the task
