package org.fundacionjala.pivotal.cucumber.steps.api;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import io.restassured.response.Response;
import org.fundacionjala.core.api.RequestManager;
import org.fundacionjala.util.ScenarioContext;
import org.fundacionjala.util.StringUtil;

import java.util.Map;

/**
 * Common steps.
 */
public class ApiSteps {

    private Response resp;

    /**
     * This is a generic API POST method.
     *
     * @param endpoint is the strings that is needed to complete the url for the
     *                 endpoint.
     * @param param    is the attributes read on the feature
     *                 file.
     */
    @Given("sends a POST request {string}")
    public void sendsAPOSTRequest(final String endpoint,
                                  final Map<String, String> param) {
        final String builtEndpoint = StringUtil.getExplicitEndpoint(endpoint);
        resp = RequestManager.postRequest(builtEndpoint, param);
    }

    /**
     * This step deletes an specified project.
     *
     * @param arg0 end point bareURl
     */
    @And("send a DELETE request {string}")
    public void sendADELETERequest(final String arg0) {
        final String url = StringUtil.getExplicitEndpoint(arg0);
        resp = RequestManager.deleteRequest(url);
    }

    /**
     * saves the response.
     *
     * @param keyContext for save the response
     */
    @And("stores response as {string}")
    public void storesResponseAs(final String keyContext) {
        ScenarioContext.getInstance().setContext(keyContext, resp);
    }
}
