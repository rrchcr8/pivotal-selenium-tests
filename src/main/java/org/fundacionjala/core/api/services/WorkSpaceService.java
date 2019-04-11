package org.fundacionjala.core.api.services;

import org.fundacionjala.core.api.RequestManager;
import org.fundacionjala.core.api.util.Properties;

import java.util.HashMap;
import java.util.Map;

/** This class has workspace api rest services. **/
public final class WorkSpaceService {
    private static final String BASE_URL = Properties.getValue("url.api")
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
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", name);
        return RequestManager.postRequest(BASE_URL, parameters).body().jsonPath().get("id");
    }

}
