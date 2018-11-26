package com.accenture.SmartOffice.model.web;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WebOfficeFullModel extends WebOfficeModelViewModel implements Serializable {
    private List<WebWorkspaceModel> workspaces;

    public List<WebWorkspaceModel> getWorkspaces() {
        return workspaces;
    }

    public void setWorkspaces(List<WebWorkspaceModel> workspaces) {
        this.workspaces = workspaces;
    }
}
