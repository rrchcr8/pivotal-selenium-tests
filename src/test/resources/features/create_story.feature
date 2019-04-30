@wip
Feature: Story
  This feature file contains scenarios to create story.

  Background:
    Given sends a POST request "/projects"
      | name | a1 |
    And stores response as "project_response"

  Scenario: creates a story with the minimum required field
    Given logs in with user "owner1"
    And goes to Dashboard "project"
    And opens a project "project_response.name"
    When creates a story with:
      | name | carlos test |
    Then verifies the story is created in panel
    And expands the story "carlos test"
    And verifies the story is created in story
    And opens header menu
    And selects show all projects
    And verifies the story is created in project list
    And sends a DELETE request "/projects/{project_response.id}"

  Scenario: creates a story with the all possible fields
    Given logs in with user "owner1"
    And goes to Dashboard "project"
    And opens a project "project_response.name"
    When creates a story with:
      | name            | new story         |
      | storyType       | feature           |
      | estimatedPoints | 2 points          |
      | requester       | owner1            |
      | owners          | member1           |
      | description     | story description |
    Then verifies the story is created in panel
    And expands the story "new story"
    And verifies the story is created in story
    And opens header menu
    And selects show all projects
    And verifies the story is created in project list
    And sends a DELETE request "/projects/{project_response.id}"
