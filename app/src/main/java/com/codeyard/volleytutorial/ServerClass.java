package com.codeyard.volleytutorial;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

class ServerClass {
    private final static String TAG = ServerClass.class.getName();

    public void sendPOSTRequestToServer(Context context, JSONObject jsonObject, String URL, final ServerResponseCallback serverResponseCallback) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                URL,
                jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "onResponse: " + response.toString());
                        serverResponseCallback.onJSONResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "onErrorResponse: ", error);serverResponseCallback.onError(error);
                    }
                }
        );

        requestQueue.add(jsonObjectRequest);
    }

    public void sendGETRequestToServer(Context context, String URL, final ServerResponseCallback serverResponseCallback) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, "onResponse: "+response.toString());
                        serverResponseCallback.onJSONArrayResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: ",error );
                serverResponseCallback.onError(error);
            }
        }
        );
        requestQueue.add(jsonArrayRequest);
    }
}
