package com.example.cc03532.dungeonsanddragons;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

public class StepFourDescription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_four_description);

        Bundle extras = getIntent().getExtras();
        final GlobalVariables character = (GlobalVariables) extras.getSerializable("CHARACTER");

        final TextView race_value = (TextView) findViewById(R.id.race_value);
        race_value.setText(character.getRACE_VALUE());

        TextView subrace_value = (TextView) findViewById(R.id.subrace_value);
        subrace_value.setText(character.getSUBRACE_VALUE());

        TextView strength_total = (TextView) findViewById(R.id.strength_total);
        strength_total.setText(String.format("%1$d",character.getSTRENGTH_VALUE()));
        TextView strength_modifier = (TextView) findViewById(R.id.strength_modifier);
        strength_modifier.setText(String.format("%1$d",character.getSTRENGTH_RACE_MODIFIER()));

        TextView dexterity_total = (TextView) findViewById(R.id.dexterity_total);
        dexterity_total.setText(String.format("%1$d",character.getDEXTERITY_VALUE()));
        TextView dexterity_modifier = (TextView) findViewById(R.id.dexterity_modifier);
        dexterity_modifier.setText(String.format("%1$d",character.getDEXTERITY_RACE_MODIFIER()));

        TextView constitution_total = (TextView) findViewById(R.id.constitution_total);
        constitution_total.setText(String.format("%1$d",character.getCONSTITUTION_VALUE()));
        TextView constitution_modifier = (TextView) findViewById(R.id.constitution_modifier);
        constitution_modifier.setText(String.format("%1$d",character.getCONSTITUTION_RACE_MODIFIER()));

        TextView intelligence_total = (TextView) findViewById(R.id.intelligence_total);
        intelligence_total.setText(String.format("%1$d",character.getINTELLIGENCE_VALUE()));
        TextView intelligence_modifier = (TextView) findViewById(R.id.intelligence_modifier);
        intelligence_modifier.setText(String.format("%1$d",character.getINTELLIGENCE_RACE_MODIFIER()));

        TextView wisdom_total = (TextView) findViewById(R.id.wisdom_total);
        wisdom_total.setText(String.format("%1$d",character.getWISDOM_VALUE()));
        TextView wisdom_modifier = (TextView) findViewById(R.id.wisdom_modifier);
        wisdom_modifier.setText(String.format("%1$d",character.getWISDOM_RACE_MODIFIER()));

        TextView charisma_total = (TextView) findViewById(R.id.charisma_total);
        charisma_total.setText(String.format("%1$d",character.getCHARISMA_VALUE()));
        TextView charisma_modifier = (TextView) findViewById(R.id.charisma_modifier);
        charisma_modifier.setText(String.format("%1$d",character.getCHARISMA_RACE_MODIFIER()));

        TextView hitdie_total = (TextView) findViewById(R.id.hitdie_total);
        hitdie_total.setText(character.getHIT_DIE());

    }

    public void stepFourSubmit (View view) throws JSONException {
        Bundle extras = getIntent().getExtras();
        final GlobalVariables character = (GlobalVariables) extras.getSerializable("CHARACTER");

        final EditText etCharacterName = (EditText) findViewById(R.id.etCharacterName);
        character.setCHARACTER_NAME(etCharacterName.getText().toString());

        String url = "https://f9vh5g1il2.execute-api.us-west-2.amazonaws.com/prod/createNewCharacter";
        String jsonRequestString = "{\"userName\":\""+ character.getUSERNAME() +"\"," +
                "\"characterName\":\""+ character.getCHARACTER_NAME() +"\"," +
                "\"race\":\""+ character.getRACE_VALUE() +"\"," +
                "\"subRace\":\""+ character.getSUBRACE_VALUE() +"\"," +
                "\"class\":\""+ character.getCLASS_VALUE() +"\"," +
                "\"hitDie\":\""+ character.getHIT_DIE() +"\"," +
                "\"strStat\":\""+ character.getSTRENGTH_VALUE() +"\"," +
                "\"dexStat\":\""+ character.getDEXTERITY_VALUE() +"\"," +
                "\"conStat\":\""+ character.getCONSTITUTION_VALUE() +"\"," +
                "\"intStat\":\""+ character.getINTELLIGENCE_VALUE() +"\"," +
                "\"wisStat\":\""+ character.getWISDOM_VALUE() +"\"," +
                "\"chaStat\":\""+ character.getCHARISMA_VALUE() +"\"," +
                "\"strRaceMod\":\""+ character.getSTRENGTH_RACE_MODIFIER() +"\"," +
                "\"dexRaceMod\":\""+ character.getDEXTERITY_RACE_MODIFIER() +"\"," +
                "\"conRaceMod\":\""+ character.getCONSTITUTION_RACE_MODIFIER() +"\"," +
                "\"intRaceMod\":\""+ character.getINTELLIGENCE_RACE_MODIFIER() +"\"," +
                "\"wisRaceMod\":\""+ character.getWISDOM_RACE_MODIFIER() +"\"," +
                "\"chaRaceMod\":\""+ character.getCHARISMA_RACE_MODIFIER() +"\"}";

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                    (Request.Method.PUT, url, new JSONObject(jsonRequestString), new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            TextView tvResponse = (TextView) findViewById(R.id.race_value);
                            tvResponse.setText(response.toString());
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO Auto-generated method stub

                        }
                    });

            queue.add(jsObjRequest);

    }
}
