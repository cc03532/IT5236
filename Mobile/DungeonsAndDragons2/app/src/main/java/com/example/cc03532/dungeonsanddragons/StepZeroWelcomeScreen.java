package com.example.cc03532.dungeonsanddragons;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StepZeroWelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_zero_welcome_screen);

        final String vUsername = getIntent().getStringExtra("USERNAME");

        final TextView tvWelcome = (TextView) findViewById(R.id.tvWelcome);
        final Spinner spCharacterSelect = (Spinner) findViewById(R.id.spCharacterSelect);
        final Button bCharacterEdit = (Button) findViewById(R.id.bCharacterEdit);
        final Button bCharacterNew = (Button) findViewById(R.id.bCharacterNew);

        String url = "https://7b7mbzuckg.execute-api.us-west-2.amazonaws.com/prod/getcharacter";
        String jsonRequestString = "{\"userName\":\""+ vUsername +"\"}";

        final List<String> stCharacterArray = new ArrayList<>();

        final ArrayAdapter<String> arCharacter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, stCharacterArray);
        arCharacter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if (spCharacterSelect != null) {
            spCharacterSelect.setAdapter(arCharacter);
        }

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        JsonObjectRequest jsObjRequest = null;

        try {
            jsObjRequest = new JsonObjectRequest
                    (Request.Method.POST, url, new JSONObject(jsonRequestString), new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                JSONArray jsCharacterArray = response.getJSONArray("Items");
                                for(int i = 0; i < jsCharacterArray.length(); i++){
                                    stCharacterArray.add(jsCharacterArray.getJSONObject(i).getString("characterName"));
                                    arCharacter.notifyDataSetChanged();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();

                        }
                    });
        } catch (JSONException e) {
            e.printStackTrace();
        }

        queue.add(jsObjRequest);
        arCharacter.notifyDataSetChanged();

        if (spCharacterSelect != null) {
            spCharacterSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (tvWelcome != null) {
                        tvWelcome.setText(spCharacterSelect.getSelectedItem().toString());
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    if (tvWelcome != null) {
                        tvWelcome.setText("Nothing");
                    }
                }
            });
        }

        if (bCharacterNew != null) {
            bCharacterNew.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), StepOneRace.class);
                    GlobalVariables character = new GlobalVariables();
                    Bundle extras = new Bundle();
                    extras.putSerializable("CHARACTER", character);
                    intent.putExtras(extras);
                    startActivity(intent);
                }
            });
        }

        if (bCharacterEdit != null) {
            bCharacterEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
