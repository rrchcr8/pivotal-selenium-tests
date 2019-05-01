Feature: Story feature allow bulk delete (multiple delete).
  This feature file represent all possible scenarios over bulk delete of stories.

  Scenario: delete a story selecting bulk
    Given sends a POST request "/projects"
      | name | project b |
    And stores response as "project_response"
    And sends a POST request "/projects/{project_response.id}/stories"
      | name | story1 |
    And stores response as "story_response1"
    And sends a POST request "/projects/{project_response.id}/stories"
      | name | story2 |
    And stores response as "story_response2"
    And sends a POST request "/projects/{project_response.id}/stories"
      | name | story3 |
    And stores response as "story_response3"
    And logs in with user "owner1"
    And goes to Dashboard "project"
    When opens a project "project_response.name"
    And selects the bulk of:
      | {story_response1.name} |
      | {story_response2.name} |
    And clicks delete button of Header container
    And clicks confirm delete button
    Then verifies that the stories are not present on panel
    And opens header menu
    And selects show all projects
    And verifies the story count for project "project_response.name" is equal "1" in  project list
