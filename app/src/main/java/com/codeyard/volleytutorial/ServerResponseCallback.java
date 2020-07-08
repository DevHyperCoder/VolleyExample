package com.codeyard.volleytutorial;

import org.json.JSONArray;
import org.json.JSONObject;

interface ServerResponseCallback {

    void onJSONResponse(JSONObject jsonObject);
    void onJSONArrayResponse(JSONArray jsonArray);
    void onError(Exception e);


}
