package org.fundacionjala.util;

import io.restassured.path.json.JsonPath;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/** Test for {@link StringUtil}. **/
public class StringUtilTest {

    /** This method load basic data for testcases. **/
    @Before
    public void setup() {
        ScenarioContext.getInstance().setContext("project_id", "23");
        ScenarioContext.getInstance().setContext("workspace_id", "52");
        ScenarioContext.getInstance().setContext("project",
                JsonPath.from("{\"id\":524,\"name\":\"My project\"}"));
        ScenarioContext.getInstance().setContext("story",
                JsonPath.from("{\"id\":12,\"name\":\"story 1\"}"));
        ScenarioContext.getInstance().setContext("workspace",
                JsonPath.from("{\"id\":742,\"name\":\"My little workSpace\","
                        .concat("\"time_zone\":{\"olson_name\": \"America/")
                        .concat("Los_Angeles\",\"offset\": \"-07:00\"}}")));
    }

    /** this test verify get url for simple url. **/
    @Test
    public void testGetExplicitEndpointForSimpleUrl() {
        final String projectUrl = StringUtil.getExplicitEndpoint("/projects/");
        assertEquals("https://www.pivotaltracker.com/services/v5/projects/",
                projectUrl);
        final String workspaceUrl = StringUtil
                .getExplicitEndpoint("/my/workspaces/");
        assertEquals("https://www.pivotaltracker.com/services/v5/my/"
                .concat("workspaces/"), workspaceUrl);
    }

    /** this test verify get url for 1 simple parameter url. **/
    @Test
    public void testGetExplicitEndpointForUrlWith1SimpleParameter() {
        final String projectUrl = StringUtil
                .getExplicitEndpoint("/projects/{project_id}");
        assertEquals("https://www.pivotaltracker.com/services/v5/projects/23",
                projectUrl);
        final String workspaceUrl = StringUtil
                .getExplicitEndpoint("/my/workspaces/{workspace_id}");
        assertEquals("https://www.pivotaltracker.com/services/v5/my"
                .concat("/workspaces/52"), workspaceUrl);
    }

    /** this test verify get url for 1 simple parameter url. **/
    @Test
    public void testGetExplicitEndpointForUrlWith1CompositeParameter() {
        final String projectUrl = StringUtil
                .getExplicitEndpoint("/projects/{project.id}");
        assertEquals("https://www.pivotaltracker.com/services/v5/projects/524",
                projectUrl);
        final String workspaceUrl = StringUtil
                .getExplicitEndpoint("/my/workspaces/{workspace.id}/time/"
                        .concat("{workspace.time_zone.olson_name}"));
        assertEquals("https://www.pivotaltracker.com/services/v5/my"
                        .concat("/workspaces/742/time/America/Los_Angeles"),
                workspaceUrl);
    }

    /** this test verify get url for 1 simple parameter url. **/
    @Test
    public void testGetExplicitEndpointForUrlWith2Parameters() {
        final String projectUrl = StringUtil.getExplicitEndpoint(
                "/projects/{project_id}/stories/{story.id}");
        assertEquals("https://www.pivotaltracker.com/services/v5/projects"
                .concat("/23/stories/12"), projectUrl);
    }
}
