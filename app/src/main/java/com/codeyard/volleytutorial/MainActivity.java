package com.codeyard.volleytutorial;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getName();

    private ArrayList<UserModel> userModels;
    private DoubleTextViewCustomAdapter doubleTextViewCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button getRequest, postRequest;
        final EditText nameEditText, emailEditText;

        getRequest = findViewById(R.id.get_request_btn);
        postRequest = findViewById(R.id.post_request_btn);
        nameEditText = findViewById(R.id.name_edit_text);
        emailEditText = findViewById(R.id.email_edit_text);

        ListView listView = findViewById(R.id.list_view);
        userModels = new ArrayList<>();
        doubleTextViewCustomAdapter = new DoubleTextViewCustomAdapter(getApplicationContext(), userModels);

        listView.setAdapter(doubleTextViewCustomAdapter);

        postRequest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                JSONObject jsonObject = new JSONObject();

                try {
                    jsonObject.put("name", nameEditText.getText().toString());
                    jsonObject.put("email", emailEditText.getText().toString());
                } catch (JSONException je) {
                    Log.e(TAG, "onClick: ", je);
                    Toast.makeText(MainActivity.this, "JSON Exception", Toast.LENGTH_SHORT).show();
                }


                new ServerClass().sendPOSTRequestToServer(
                        getApplicationContext(),
                        jsonObject,
                        Constants.URL,
                        new ServerResponseCallback() {
                            @Override
                            public void onJSONResponse(JSONObject jsonObject) {

                            }

                            @Override
                            public void onJSONArrayResponse(JSONArray jsonArray) {

                            }

                            @Override
                            public void onError(Exception e) {
                                Log.e(TAG, "onError: ", e);
                                Toast.makeText(MainActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        getRequest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
//                TODO get the data
                new ServerClass().sendGETRequestToServer(getApplicationContext(), Constants.URL,
                        new ServerResponseCallback() {
                            @Override
                            public void onJSONResponse(JSONObject jsonObject) {

                            }

                            @Override
                            public void onJSONArrayResponse(JSONArray jsonArray) {
                                userModels.clear();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    try {


                                        JSONObject jsonObject = jsonArray.optJSONObject(i);
                                        UserModel userModel = new UserModel();
                                        userModel.setEmail(jsonObject.getString("email"));
                                        userModel.setName(jsonObject.getString("name"));
                                        userModels.add(userModel);
                                    } catch (JSONException j) {
                                        Log.e(TAG, "onJSONArrayResponse: ", j);
                                        Toast.makeText(MainActivity.this, "JSONException", Toast.LENGTH_SHORT).show();

                                    }

                                }
                                doubleTextViewCustomAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onError(Exception e) {
                                Log.e(TAG, "onError: ", e);
                                Toast.makeText(MainActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
