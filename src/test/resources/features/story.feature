#@wip
Feature: Story
  Background:
    Given send a POST request "/projects"
      |name|a3|
    And stores response as "project_response"

    #And stores response as "project_response"

  Scenario:
    Given logs in with user "owner1"
    When creates a project called "carlos test"
#    Given a project called 'awt-02'
#    When click on the project link
#    And click on add Story
#    And creates a default story as
#      | NAME        | awt-02-story-01  |
#      | DESCRIPTION | Test             |
#
#    Then the story is created
#    And it appear listed
