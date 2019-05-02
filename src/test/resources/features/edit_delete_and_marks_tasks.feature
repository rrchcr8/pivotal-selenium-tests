@bvt
Feature: Task management

  Background:
    Given sends a POST request "/projects"
      | name | task project |
    And stores response as "project_response"
    And sends a POST request "/projects/{project_response.id}/stories"
      | name | task story |
    And stores response as "story_response"
    And sends a POST request "/projects/{project_response.id}/stories/{story_response.id}/tasks"
      | description | task item |
    And stores response as "task_response"
    And logs in with user "owner1"
    And goes to dashboard "Projects"
    And opens a project "project_response.name"
    And expands the story "story_response.name"

  @wip
  Scenario: Edit name of task
    When edits the task name
      | name | Edited Task |
    Then the old task should not be listed
    And the newly task should be listed
    And sends a DELETE request "/projects/{project_response.id}"


  @wip
  Scenario: Mark task as complete
    When marks the task as "complete"
    Then the task should be colored
      | color | green |
    And sends a DELETE request "/projects/{project_response.id}"


  @wip
  Scenario: Mark task as incomplete
    Given marks the task as "complete"
    When marks the task as "incomplete"
    Then the task should be colored
      | color | default |
    And sends a DELETE request "/projects/{project_response.id}"


  @wip
  Scenario: Delete a given task
    When deletes the task "task_response.description"
    Then the "task_response.description" should not be listed
    And sends a DELETE request "/projects/{project_response.id}"


