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

        final GlobalVariables character = new GlobalVariables();

        final String vUsername = getIntent().getStringExtra("USERNAME");

        character.setUSERNAME(vUsername);

        final TextView tvWelcome = (TextView) findViewById(R.id.tvWelcome);
        final Spinner spCharacterSelect = (Spinner) findViewById(R.id.spCharacterSelect);
        final Button bCharacterEdit = (Button) findViewById(R.id.bCharacterEdit);
        final Button bCharacterNew = (Button) findViewById(R.id.bCharacterNew);

        String url = "https://f9vh5g1il2.execute-api.us-west-2.amazonaws.com/prod/getCharacter";
        String jsonRequestString = "{\"userName\":\""+ vUsername +"\"}";

        final List<String> stCharacterArray = new ArrayList<>();

        final ArrayAdapter<String> arCharacter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, stCharacterArray);
        arCharacter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if (spCharacterSelect != null) {
            spCharacterSelect.setAdapter(arCharacter);
        }

        final RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        JsonObjectRequest jsObjRequestCharacters = null;

                                try {
                                    jsObjRequestCharacters = new JsonObjectRequest
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

        queue.add(jsObjRequestCharacters);
        arCharacter.notifyDataSetChanged();

        if (spCharacterSelect != null) {
            spCharacterSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (tvWelcome != null) {

                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    if (tvWelcome != null) {

                    }
                }
            });
        }

        if (bCharacterNew != null) {
            bCharacterNew.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), StepOneRace.class);
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

                    String characterName = null;
                    if (spCharacterSelect != null) {
                        characterName = spCharacterSelect.getSelectedItem().toString();
                    }

                    String urlStats = "https://f9vh5g1il2.execute-api.us-west-2.amazonaws.com/prod/getCharacterStats";
                    String jsonRequestString = "{\"userName\":\"" + vUsername + "\",\"characterName\":\"" + characterName + "\"}";

                    JsonObjectRequest jsonObjectRequestCharacterData = null;

                    try {
                        jsonObjectRequestCharacterData = new JsonObjectRequest(Request.Method.POST, urlStats, new JSONObject(jsonRequestString), new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                JSONObject jsonItemObject = null;

                                try {
                                    jsonItemObject = (response).getJSONObject("Item");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    character.setCHARACTER_NAME(jsonItemObject.getJSONObject("characterName").getString("S"));

                                    character.setRACE_VALUE(jsonItemObject.getJSONObject("race").getString("S"));
                                    character.setSUBRACE_VALUE(jsonItemObject.getJSONObject("subRace").getString("S"));
                                    character.setCLASS_VALUE(jsonItemObject.getJSONObject("class").getString("S"));
                                    character.setHIT_DIE(jsonItemObject.getJSONObject("hitDie").getString("S"));

                                    character.setSTRENGTH_VALUE(jsonItemObject.getJSONObject("ability").getJSONArray("L").getJSONObject(0).getInt("N"));
                                    character.setDEXTERITY_VALUE(jsonItemObject.getJSONObject("ability").getJSONArray("L").getJSONObject(1).getInt("N"));
                                    character.setCONSTITUTION_VALUE(jsonItemObject.getJSONObject("ability").getJSONArray("L").getJSONObject(2).getInt("N"));
                                    character.setINTELLIGENCE_VALUE(jsonItemObject.getJSONObject("ability").getJSONArray("L").getJSONObject(3).getInt("N"));
                                    character.setWISDOM_VALUE(jsonItemObject.getJSONObject("ability").getJSONArray("L").getJSONObject(4).getInt("N"));
                                    character.setCHARISMA_VALUE(jsonItemObject.getJSONObject("ability").getJSONArray("L").getJSONObject(5).getInt("N"));


                                    character.setSTRENGTH_RACE_MODIFIER(jsonItemObject.getJSONObject("abilityRaceModifier").getJSONArray("L").getJSONObject(0).getInt("N"));
                                    character.setDEXTERITY_RACE_MODIFIER(jsonItemObject.getJSONObject("abilityRaceModifier").getJSONArray("L").getJSONObject(1).getInt("N"));
                                    character.setCONSTITUTION_RACE_MODIFIER(jsonItemObject.getJSONObject("abilityRaceModifier").getJSONArray("L").getJSONObject(2).getInt("N"));
                                    character.setINTELLIGENCE_RACE_MODIFIER(jsonItemObject.getJSONObject("abilityRaceModifier").getJSONArray("L").getJSONObject(3).getInt("N"));
                                    character.setWISDOM_RACE_MODIFIER(jsonItemObject.getJSONObject("abilityRaceModifier").getJSONArray("L").getJSONObject(4).getInt("N"));
                                    character.setCHARISMA_RACE_MODIFIER(jsonItemObject.getJSONObject("abilityRaceModifier").getJSONArray("L").getJSONObject(5).getInt("N"));

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
                    queue.add(jsonObjectRequestCharacterData);

                    Intent intent = new Intent(getApplicationContext(), StepOneRace.class);
                    Bundle extras = new Bundle();
                    extras.putSerializable("CHARACTER", character);
                    intent.putExtras(extras);
                    startActivity(intent);
                }
            });
        }
    }
}

