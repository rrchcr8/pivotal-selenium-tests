@wip
Feature: Story
  Background:
    Given send a POST request "/projects"
      |name|a1|
    And stores response as "project_response"

  Scenario:
    Given logs in with user "owner1"
    When creates a story called "carlos test"
    When creates other a story called "carlos test2222222"
    Then verify the story is created
    And send a DELETE request "/projects/{project_id}"








#    Given a project called 'awt-02'
#    When click on the project link
#    And click on add Story
#    And creates a default story as
#      | NAME        | awt-02-story-01  |
#      | DESCRIPTION | Test             |
#
#    Then the story is created
#    And it appear listed
