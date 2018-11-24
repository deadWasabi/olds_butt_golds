package com.accenture.SmartOffice.model.web;

import java.io.Serializable;

public class WebUserSwapModel implements Serializable {

    Long firstUserId;

    Long secondUserId;

    public Long getFirstUserId() {
        return firstUserId;
    }

    public void setFirstUserId(Long firstUserId) {
        this.firstUserId = firstUserId;
    }

    public Long getSecondUserId() {
        return secondUserId;
    }

    public void setSecondUserId(Long secondUserId) {
        this.secondUserId = secondUserId;
    }
}
