package org.fundacionjala.pivotal.cucumber.steps.api;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.fundacionjala.core.Environment;
import org.fundacionjala.core.api.RequestManager;
import org.fundacionjala.core.ui.driver.DriverManager;
import org.fundacionjala.pivotal.pages.Login;
import org.fundacionjala.pivotal.pages.Project;
import org.fundacionjala.util.ScenarioContext;
import org.fundacionjala.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Common steps.
 */
public class ApiSteps {

    private Response resp;

    /**
     * This is a generic API POST method.
     * @param endpoint is the strings that is needed to complete the url for the
     *             endpoint.
     * @param param is the attributes read on the feature
     *                          file.
     */
    @Given("sends a POST request {string}")
    public void sendsAPOSTRequest(String endpoint,
                                 final Map<String, String> param) {
        final String builtEndpoint = StringUtil.getExplicitEndpoint(endpoint);
        resp = RequestManager.postRequest(builtEndpoint, param);
    }

    @And("send a DELETE request {string}")
    public void sendADELETERequest(String arg0) {
        final String Url = StringUtil.getExplicitEndpoint(arg0);
        resp = RequestManager.deleteRequest(Url);
    }

    @And("stores response as {string}")
    public void storesResponseAs(String keyContext) {
        ScenarioContext.getInstance().setContext(keyContext, resp);
    }
}
