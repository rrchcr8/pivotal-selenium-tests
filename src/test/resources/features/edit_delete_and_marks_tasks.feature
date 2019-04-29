@wip
Feature: Task management

  Background:
    Given sends a POST request "/projects"
      | name | task project |
    And stores response as "project_response"
    And sends a POST request "/projects/{project_response.id}/stories"
      | name | task story |
    And stores response as "story_response"
    And sends a POST request "/projects/{project_response.id}/stories/{story_response.id}/tasks"
      | name | task item |
    And stores response as "task_response"
    And logs in with user "owner1"
    And goes to dashboard "Projects"
    And opens a project "project_response.name"
    And expands the story "story_respone.name"
    And saves task counters on context
      | countTask     |
      | doneCountTask |

  Scenario: Edit name of task
    When edits the task name
      | name | Edited Task |
    Then the old task should not be listed
    And the newly task should be listed
    And validates task counter
      | operation | equal |
    And deletes the task

  Scenario: Mark task as complete
    When marks the task as "complete"
    Then the task should be colored
      | color | green |
    And validates done task counter
      | operation | increment |
    And deletes the task

  Scenario: Mark task as incomplete
    Given marks the task as "complete"
    When marks the task as "incomplete"
    Then the task should be colored
      | color | default |
    And validates done task counter
      | operation | decrement |
    And deletes the task

  Scenario: Delete a task
    When deletes the task
    Then the "task_response.name" should not be listed
    And validates task counter
      | operation | decrement |

