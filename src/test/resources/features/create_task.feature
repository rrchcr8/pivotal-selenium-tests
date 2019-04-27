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
    And Open the project
      | name | project_response.name |
    And Open the story
      | name | story_response.name |

  Scenario: Create a task on existing Story
    When adds a task to current Story
      | name | First Task |
    And the newly task should be listed
    And counter varies
      | operation | increment |
    And user deletes the task
