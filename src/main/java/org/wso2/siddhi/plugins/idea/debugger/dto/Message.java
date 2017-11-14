/*
 *  Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.wso2.siddhi.plugins.idea.debugger.dto;

import java.util.Map;

/**
 * DTO class for message.
 */
public class Message {

    private String code;

    private String message;

    private String queryName;

    private Object eventInfo;

    private Map<String, Object> queryState;

    private BreakPoint location;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getQueryState() {
        return queryState;
    }

    public void setQueryState(Map<String, Object> queryState) {
        this.queryState = queryState;
    }

    public BreakPoint getLocation() {
        return location;
    }

    public void setLocation(BreakPoint location) {
        this.location = location;
    }

    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    public Object getEventInfo() {
        return eventInfo;
    }

    public void setEventInfo(Object eventInfo) {
        this.eventInfo = eventInfo;
    }

}
