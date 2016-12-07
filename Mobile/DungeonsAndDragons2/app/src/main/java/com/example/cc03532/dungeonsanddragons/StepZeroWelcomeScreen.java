package com.example.cc03532.dungeonsanddragons;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.android.volley.toolbox.StringRequest;
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

        SharedPreferences sharedPref = getSharedPreferences("SPR_FILE_KEY", Context.MODE_PRIVATE);
        final String vUsername = sharedPref.getString("vUserName", "BOB");

        final Spinner spCharacterSelect = (Spinner) findViewById(R.id.spCharacterSelect);
        final Button bCharacterEdit = (Button) findViewById(R.id.bCharacterEdit);
        final Button bCharacterNew = (Button) findViewById(R.id.bCharacterNew);
        final Button bCharacterDelete = (Button) findViewById(R.id.bCharacterDelete);
        final Button bLogout = (Button) findViewById(R.id.bLogout);

        String url = "https://f9vh5g1il2.execute-api.us-west-2.amazonaws.com/v1/character?userName="+ vUsername;

        final List<String> stCharacterArray = new ArrayList<>();

        final ArrayAdapter<String> arCharacter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, stCharacterArray);
        arCharacter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if (spCharacterSelect != null) {
            spCharacterSelect.setAdapter(arCharacter);
        }

        final RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        final JsonObjectRequest jsObjRequestCharacters = new JsonObjectRequest
                (Request.Method.GET, url,null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsCharacterArray = response.getJSONArray("Items");
                            int jsItemsArray = jsCharacterArray.length();
                            if(jsItemsArray>=1){
                                bCharacterEdit.setEnabled(true);
                                bCharacterDelete.setEnabled(true);
                            }
                            for(int i = 0; i < jsItemsArray; i++){
                                stCharacterArray.add(jsCharacterArray.getJSONObject(i).getString("characterName").replaceAll("_"," "));
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


        queue.add(jsObjRequestCharacters);
        arCharacter.notifyDataSetChanged();

        if (spCharacterSelect != null) {
            spCharacterSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

        if (bCharacterNew != null) {
            bCharacterNew.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), StepOneRace.class);
                    Bundle extras = new Bundle();

                    character.setCHARACTER_NAME(null);

                    character.setRACE_VALUE(null);
                    character.setSUBRACE_VALUE(null);
                    character.setCLASS_VALUE(null);
                    character.setHIT_DIE(null);

                    character.setSTRENGTH_VALUE(0);
                    character.setDEXTERITY_VALUE(0);
                    character.setCONSTITUTION_VALUE(0);
                    character.setINTELLIGENCE_VALUE(0);
                    character.setWISDOM_VALUE(0);
                    character.setCHARISMA_VALUE(0);


                    character.setSTRENGTH_RACE_MODIFIER(0);
                    character.setDEXTERITY_RACE_MODIFIER(0);
                    character.setCONSTITUTION_RACE_MODIFIER(0);
                    character.setINTELLIGENCE_RACE_MODIFIER(0);
                    character.setWISDOM_RACE_MODIFIER(0);
                    character.setCHARISMA_RACE_MODIFIER(0);

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
                        characterName = characterName.replaceAll(" ", "_");
                    }

                    String urlStats = "https://f9vh5g1il2.execute-api.us-west-2.amazonaws.com/v1/character/stats?userName=" + vUsername + "&characterName=" + characterName;

                    JsonObjectRequest jsonObjectRequestCharacterData = new JsonObjectRequest(Request.Method.GET, urlStats, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            JSONObject jsonItemObject = null;

                            try {
                                jsonItemObject = (response).getJSONArray("Items").getJSONObject(0);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                character.setCHARACTER_NAME(jsonItemObject.getString("characterName"));

                                character.setRACE_VALUE(jsonItemObject.getString("race"));
                                character.setSUBRACE_VALUE(jsonItemObject.getString("subRace"));
                                character.setCLASS_VALUE(jsonItemObject.getString("class"));
                                character.setHIT_DIE(jsonItemObject.getString("hitDie"));

                                character.setSTRENGTH_VALUE(jsonItemObject.getJSONArray("ability").getInt(0));
                                character.setDEXTERITY_VALUE(jsonItemObject.getJSONArray("ability").getInt(1));
                                character.setCONSTITUTION_VALUE(jsonItemObject.getJSONArray("ability").getInt(2));
                                character.setINTELLIGENCE_VALUE(jsonItemObject.getJSONArray("ability").getInt(3));
                                character.setWISDOM_VALUE(jsonItemObject.getJSONArray("ability").getInt(4));
                                character.setCHARISMA_VALUE(jsonItemObject.getJSONArray("ability").getInt(5));


                                character.setSTRENGTH_RACE_MODIFIER(jsonItemObject.getJSONArray("abilityRaceModifier").getInt(0));
                                character.setDEXTERITY_RACE_MODIFIER(jsonItemObject.getJSONArray("abilityRaceModifier").getInt(1));
                                character.setCONSTITUTION_RACE_MODIFIER(jsonItemObject.getJSONArray("abilityRaceModifier").getInt(2));
                                character.setINTELLIGENCE_RACE_MODIFIER(jsonItemObject.getJSONArray("abilityRaceModifier").getInt(3));
                                character.setWISDOM_RACE_MODIFIER(jsonItemObject.getJSONArray("abilityRaceModifier").getInt(4));
                                character.setCHARISMA_RACE_MODIFIER(jsonItemObject.getJSONArray("abilityRaceModifier").getInt(5));

                                Intent intent = new Intent(getApplicationContext(), StepOneRace.class);
                                Bundle extras = new Bundle();
                                extras.putSerializable("CHARACTER", character);
                                intent.putExtras(extras);
                                startActivity(intent);

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

                    queue.add(jsonObjectRequestCharacterData);

                }
            });
        }

        if (bCharacterDelete != null) {
            bCharacterDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    RequestQueue queueDelete = Volley.newRequestQueue(getApplicationContext());

                    String characterName = null;
                    if (spCharacterSelect != null) {
                        characterName = spCharacterSelect.getSelectedItem().toString();
                    }
                    final String urlDelete = "https://f9vh5g1il2.execute-api.us-west-2.amazonaws.com/v1/character?userName="+vUsername+"&characterName="+characterName.replaceAll(" ","_");

                    try {
                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, urlDelete, new JSONObject("{}"), new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                arCharacter.remove(spCharacterSelect.getSelectedItem().toString());
                                arCharacter.notifyDataSetChanged();
                                    if(arCharacter.isEmpty()){
                                        bCharacterDelete.setEnabled(false);
                                        bCharacterEdit.setEnabled(false);
                                    }

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                            }
                        });

                        queueDelete.add(jsonObjectRequest);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        if (bLogout != null) {
            bLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences preferences = getSharedPreferences("vUserName", 0);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.clear();
                    editor.apply();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
    @Override
    public void onBackPressed()
    {

    }
}

