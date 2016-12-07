package com.example.cc03532.dungeonsanddragons;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

        final EditText etCharacterName = (EditText) findViewById(R.id.etCharacterName);

        if (character != null && character.getCHARACTER_NAME() != null) {
            if (etCharacterName != null) {
                etCharacterName.setText(character.getCHARACTER_NAME().replaceAll("_"," "));
                etCharacterName.setEnabled(false);
            }
        }

        final TextView race_value = (TextView) findViewById(R.id.race_value);
        race_value.setText("Race: "+ character.getRACE_VALUE());

        TextView subrace_value = (TextView) findViewById(R.id.subrace_value);
        subrace_value.setText("SubRace: "+ character.getSUBRACE_VALUE());

        TextView strength_total = (TextView) findViewById(R.id.strength_total);
        strength_total.setText("Strength: "+ String.format("%1$d",character.getSTRENGTH_VALUE()));
        if(character.getSTRENGTH_RACE_MODIFIER() != 0) {
            TextView strength_modifier = (TextView) findViewById(R.id.strength_modifier);
            strength_modifier.setText("+"+String.format("%1$d", character.getSTRENGTH_RACE_MODIFIER()));
        }
        TextView dexterity_total = (TextView) findViewById(R.id.dexterity_total);
        dexterity_total.setText("Dexterity: "+ String.format("%1$d",character.getDEXTERITY_VALUE()));
        if(character.getDEXTERITY_RACE_MODIFIER() != 0) {
            TextView dexterity_modifier = (TextView) findViewById(R.id.dexterity_modifier);
            dexterity_modifier.setText("+"+String.format("%1$d", character.getDEXTERITY_RACE_MODIFIER()));
        }
        TextView constitution_total = (TextView) findViewById(R.id.constitution_total);
        constitution_total.setText("Constitution: "+ String.format("%1$d",character.getCONSTITUTION_VALUE()));
        if(character.getCONSTITUTION_RACE_MODIFIER() != 0) {
            TextView constitution_modifier = (TextView) findViewById(R.id.constitution_modifier);
            constitution_modifier.setText("+"+String.format("%1$d", character.getCONSTITUTION_RACE_MODIFIER()));
        }
        TextView intelligence_total = (TextView) findViewById(R.id.intelligence_total);
        intelligence_total.setText("Intelligence: "+ String.format("%1$d",character.getINTELLIGENCE_VALUE()));
        if(character.getINTELLIGENCE_RACE_MODIFIER() != 0) {
            TextView intelligence_modifier = (TextView) findViewById(R.id.intelligence_modifier);
            intelligence_modifier.setText("+"+String.format("%1$d", character.getINTELLIGENCE_RACE_MODIFIER()));
        }
        TextView wisdom_total = (TextView) findViewById(R.id.wisdom_total);
        wisdom_total.setText("Wisdom: "+ String.format("%1$d",character.getWISDOM_VALUE()));
        if(character.getWISDOM_RACE_MODIFIER() != 0) {
            TextView wisdom_modifier = (TextView) findViewById(R.id.wisdom_modifier);
            wisdom_modifier.setText("+"+String.format("%1$d", character.getWISDOM_RACE_MODIFIER()));
        }
        TextView charisma_total = (TextView) findViewById(R.id.charisma_total);
        charisma_total.setText("Charisma: "+ String.format("%1$d",character.getCHARISMA_VALUE()));
        if(character.getCHARISMA_RACE_MODIFIER() != 0) {
            TextView charisma_modifier = (TextView) findViewById(R.id.charisma_modifier);
            charisma_modifier.setText("+"+String.format("%1$d", character.getCHARISMA_RACE_MODIFIER()));
        }
        TextView hitdie_total = (TextView) findViewById(R.id.hitdie_total);
        hitdie_total.setText("HitDie: " + character.getHIT_DIE());

    }

    public void stepFourSubmit (View view) throws JSONException {
        Bundle extras = getIntent().getExtras();
        final GlobalVariables character = (GlobalVariables) extras.getSerializable("CHARACTER");

        final EditText etCharacterName = (EditText) findViewById(R.id.etCharacterName);

        if (etCharacterName.getText().toString().matches("")) {
            final AlertDialog.Builder adNameRequired = new AlertDialog.Builder(StepFourDescription.this);
            adNameRequired.setMessage("Must a Enter Character Name:")
                    .setTitle("Character Name is Empty")
                    .setCancelable(true)
                    .setPositiveButton("OK",new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            adNameRequired.show();
        } else {
            if (character != null) {
                character.setCHARACTER_NAME(etCharacterName.getText().toString());
            }

            String url = "https://f9vh5g1il2.execute-api.us-west-2.amazonaws.com/v1/character";

            String jsonRequestString = null;
            if (character != null) {
                SharedPreferences sharedPref = getSharedPreferences("SPR_FILE_KEY", Context.MODE_PRIVATE);
                jsonRequestString = "{\"userName\":\"" + sharedPref.getString("vUserName", "BOB") + "\"," +
                        "\"characterName\":\"" + character.getCHARACTER_NAME().replaceAll(" ", "_") + "\"," +
                        "\"race\":\"" + character.getRACE_VALUE() + "\"," +
                        "\"subRace\":\"" + character.getSUBRACE_VALUE() + "\"," +
                        "\"class\":\"" + character.getCLASS_VALUE() + "\"," +
                        "\"hitDie\":\"" + character.getHIT_DIE() + "\"," +
                        "\"strStat\":\"" + character.getSTRENGTH_VALUE() + "\"," +
                        "\"dexStat\":\"" + character.getDEXTERITY_VALUE() + "\"," +
                        "\"conStat\":\"" + character.getCONSTITUTION_VALUE() + "\"," +
                        "\"intStat\":\"" + character.getINTELLIGENCE_VALUE() + "\"," +
                        "\"wisStat\":\"" + character.getWISDOM_VALUE() + "\"," +
                        "\"chaStat\":\"" + character.getCHARISMA_VALUE() + "\"," +
                        "\"strRaceMod\":\"" + character.getSTRENGTH_RACE_MODIFIER() + "\"," +
                        "\"dexRaceMod\":\"" + character.getDEXTERITY_RACE_MODIFIER() + "\"," +
                        "\"conRaceMod\":\"" + character.getCONSTITUTION_RACE_MODIFIER() + "\"," +
                        "\"intRaceMod\":\"" + character.getINTELLIGENCE_RACE_MODIFIER() + "\"," +
                        "\"wisRaceMod\":\"" + character.getWISDOM_RACE_MODIFIER() + "\"," +
                        "\"chaRaceMod\":\"" + character.getCHARISMA_RACE_MODIFIER() + "\"}";
            }

            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

            int method = Request.Method.POST;

            if(!etCharacterName.isEnabled()) {
                method = Request.Method.PUT;
            }

            JsonObjectRequest jsObjRequest = new JsonObjectRequest
                    (method, url, new JSONObject(jsonRequestString), new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            Intent intent = new Intent(StepFourDescription.this, StepZeroWelcomeScreen.class);
                            startActivity(intent);
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
}
