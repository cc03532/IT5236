package com.example.cc03532.dungeonsanddragons;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final TextView tvRegister = (TextView) findViewById(R.id.tvRegister);
        final Button bLogin = (Button) findViewById(R.id.bLogin);

        if (tvRegister != null) {
            tvRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent newIntent = new Intent(getApplicationContext(), Register.class);
                    startActivity(newIntent);
                }
            });
        }

        if (bLogin != null) {
            bLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String vUsername = etUsername.getText().toString();
                    final String vPassword = etPassword.getText().toString();

                    final String url ="https://f9vh5g1il2.execute-api.us-west-2.amazonaws.com/v1/user?userName="+vUsername;

                    final AlertDialog.Builder adIncorrectPasswordObject = new AlertDialog.Builder(MainActivity.this);
                    adIncorrectPasswordObject.setMessage("Incorrect Username/Password:")
                            .setTitle("Incorrect Username/Password")
                            .setCancelable(true)
                            .setPositiveButton("OK",new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });

                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

                    JsonObjectRequest jsObjRequest;
                    jsObjRequest = new JsonObjectRequest
                            (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject response) {

                                    try{

                                        JSONObject jsonItemObject =(response).getJSONObject("Item");
                                        String jsonPasswordValue = jsonItemObject.getString("password");

                                        if(jsonPasswordValue.equals(vPassword)) {
                                            Intent intent = new Intent(getApplicationContext(), StepZeroWelcomeScreen.class);

                                            SharedPreferences sharedPref = getSharedPreferences("SPR_FILE_KEY", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor sprEditor = sharedPref.edit();
                                            sprEditor.putString("vUserName",vUsername);
                                            sprEditor.apply();

                                            startActivity(intent);
                                        } else {
                                            AlertDialog adIncorrectPassword = adIncorrectPasswordObject.create();
                                            adIncorrectPassword.show();
                                        }

                                    }catch (Exception e) {
                                        e.printStackTrace();
                                        AlertDialog adIncorrectPassword = adIncorrectPasswordObject.create();
                                        adIncorrectPassword.show();
                                    }
                                }
                            }, new Response.ErrorListener() {

                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    error.printStackTrace();

                                }
                            });
                    queue.add(jsObjRequest);
                }
            });
        }
    }

    @Override
    public void onBackPressed()
    {

    }
}