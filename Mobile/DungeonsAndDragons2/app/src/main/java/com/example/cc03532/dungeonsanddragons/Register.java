package com.example.cc03532.dungeonsanddragons;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final EditText etName = (EditText) findViewById(R.id.etName);
        final TextView tvResponse = (TextView) findViewById(R.id.tvResponse);
        final Button bRegister = (Button) findViewById(R.id.bRegister);

        if (bRegister != null) {
            bRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String vUserName = etUsername.getText().toString();
                    final String vPassword = etPassword.getText().toString();
                    final String vName = etName.getText().toString();

                    final String url = "https://7b7mbzuckg.execute-api.us-west-2.amazonaws.com/prod/createuser";
                    final String jsonRequestString = "{\"userName\":\""+ vUserName +"\",\"password\":\""+vPassword+"\",\"name\":\""+vName+"\"}";

                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

                    JsonObjectRequest jsObjRequest = null;
                    try {
                        jsObjRequest = new JsonObjectRequest
                                (Request.Method.PUT, url, new JSONObject(jsonRequestString), new Response.Listener<JSONObject>() {

                                    @Override
                                    public void onResponse(JSONObject response) {
                                        String sResponse = response.toString();
                                        tvResponse.setText(sResponse);
                                        //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        //startActivity(intent);
                                    }
                                }, new Response.ErrorListener() {

                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        // TODO Auto-generated method stub

                                    }
                                });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    queue.add(jsObjRequest);
                }
            });
        }
    }

}
