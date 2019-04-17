package org.fundacionjala.core.api.services;

import org.fundacionjala.core.api.RequestManager;
import org.fundacionjala.util.ScenarioContext;

import java.util.HashMap;
import java.util.Map;

/** This class has workspace api rest services. **/
public final class WorkSpaceService {
    private static final String BASE_URL =
            ScenarioContext.getContextAsString(ScenarioContext.API_URL_KEY)
                    .concat("/my/workspaces");

    /** Private constructor. **/
    private WorkSpaceService() {
    }

    /**
     * Method to create a workspace via API.
     *
     * @param name Workspace name.
     * @return ID of the created workspace.
     */
    public static int createWorkspace(final String name) {
        final Map<String, String> parameters = new HashMap<>();
        parameters.put("name", name);
        return RequestManager.postRequest(BASE_URL, parameters).body().jsonPath().get("id");
    }

    /**
     * this method edit a workspace and return its Id.
     *
     * @param workspaceId int
     * @param name        string
     * @return int
     */
    public static int workedit(final int workspaceId, final String name) {
        final Map<String, String> parameters = new HashMap<>();
        parameters.put("name", name);
        return RequestManager.putRequest(String.format(BASE_URL,
                workspaceId), parameters)
                .body().jsonPath().get("id");
    }

    /**
     * Deletes the specified story in the specified project.
     *
     * @param workspaceId the ID of the Work Space.
     */
    public static void deleteworkspaceId(final int workspaceId) {
        RequestManager.deleteRequest(String.format(BASE_URL.concat("/%d"),
                workspaceId));
    }
}
