package com.example.cc03532.dungeonsanddragons;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StepThreeAbilityScores extends AppCompatActivity {

    ArrayList abilityList;
    ArrayAdapter<CharSequence> adapter;
    RadioButton rbRoll;
    RadioButton rbSelect;
    NumberPicker strength_picker;
    Spinner strength_spinner;
    NumberPicker dexterity_picker;
    Spinner dexterity_spinner;
    NumberPicker constitution_picker;
    Spinner constitution_spinner;
    NumberPicker intelligence_picker;
    Spinner intelligence_spinner;
    NumberPicker wisdom_picker;
    Spinner wisdom_spinner;
    NumberPicker charisma_picker;
    Spinner charisma_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_three_ability_scores);

        Bundle extras = getIntent().getExtras();
        final GlobalVariables character = (GlobalVariables) extras.getSerializable("CHARACTER");

        abilityList = new ArrayList<>();

        rbRoll = (RadioButton) findViewById(R.id.rbRoll);
        rbSelect = (RadioButton) findViewById(R.id.rbSelect);

        adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, abilityList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        TextView tvRemainingPoints = (TextView) findViewById(R.id.tvRemainingPoints);

        if(character != null && character.getSTRENGTH_VALUE() != 0) {
            if (rbRoll != null) {
                rbRoll.setEnabled(false);
            }
        }

        strength_picker = (NumberPicker) findViewById(R.id.strength_picker);
        assert strength_picker != null;
        strength_picker.setMinValue(8);
        strength_picker.setMaxValue(18);
        if (character != null && character.getSTRENGTH_VALUE() != 0) {
            strength_picker.setValue(character.getSTRENGTH_VALUE());
        }

        strength_spinner = (Spinner) findViewById(R.id.strength_spinner);

        dexterity_picker = (NumberPicker) findViewById(R.id.dexterity_picker);
        assert dexterity_picker != null;
        dexterity_picker.setMinValue(8);
        dexterity_picker.setMaxValue(18);
        if (character != null && character.getDEXTERITY_VALUE() != 0) {
            dexterity_picker.setValue(character.getDEXTERITY_VALUE());
        }

        dexterity_spinner = (Spinner) findViewById(R.id.dexterity_spinner);

        constitution_picker = (NumberPicker) findViewById(R.id.constitution_picker);
        assert constitution_picker != null;
        constitution_picker.setMinValue(8);
        constitution_picker.setMaxValue(18);
        if (character != null && character.getCONSTITUTION_VALUE() != 0) {
            constitution_picker.setValue(character.getCONSTITUTION_VALUE());
        }

        constitution_spinner = (Spinner) findViewById(R.id.constitution_spinner);

        intelligence_picker = (NumberPicker) findViewById(R.id.intelligence_picker);
        assert intelligence_picker != null;
        intelligence_picker.setMinValue(8);
        intelligence_picker.setMaxValue(18);
        if (character != null && character.getINTELLIGENCE_VALUE() != 0) {
            intelligence_picker.setValue(character.getINTELLIGENCE_VALUE());
        }

        intelligence_spinner = (Spinner) findViewById(R.id.intelligence_spinner);

        wisdom_picker = (NumberPicker) findViewById(R.id.wisdom_picker);
        assert wisdom_picker != null;
        wisdom_picker.setMinValue(8);
        wisdom_picker.setMaxValue(18);
        if (character != null && character.getWISDOM_VALUE() != 0) {
            wisdom_picker.setValue(character.getWISDOM_VALUE());
        }

        wisdom_spinner = (Spinner) findViewById(R.id.wisdom_spinner);

        charisma_picker = (NumberPicker) findViewById(R.id.charisma_picker);
        assert charisma_picker != null;
        charisma_picker.setMinValue(8);
        charisma_picker.setMaxValue(18);
        if (character != null && character.getCHARISMA_VALUE() != 0) {
            charisma_picker.setValue(character.getCHARISMA_VALUE());
        }

        charisma_spinner = (Spinner) findViewById(R.id.charisma_spinner);

        strength_spinner.setAdapter(adapter);
        dexterity_spinner.setAdapter(adapter);
        constitution_spinner.setAdapter(adapter);
        intelligence_spinner.setAdapter(adapter);
        wisdom_spinner.setAdapter(adapter);
        charisma_spinner.setAdapter(adapter);
    }

    public void stepThreeSubmit(View view) {

        Bundle extras = getIntent().getExtras();
        final GlobalVariables character = (GlobalVariables) extras.getSerializable("CHARACTER");

        Intent newIntent = new Intent(this, StepFourDescription.class);
        Bundle newExtras = new Bundle();

        if (character != null && rbSelect.isChecked()) {
            character.setSTRENGTH_VALUE(strength_picker.getValue());
            character.setDEXTERITY_VALUE(dexterity_picker.getValue());
            character.setCONSTITUTION_VALUE(constitution_picker.getValue());
            character.setINTELLIGENCE_VALUE(intelligence_picker.getValue());
            character.setWISDOM_VALUE(wisdom_picker.getValue());
            character.setCHARISMA_VALUE(charisma_picker.getValue());
        } else if(character != null && rbRoll.isChecked()) {
            character.setSTRENGTH_VALUE(Integer.parseInt(strength_spinner.getSelectedItem().toString()));
            character.setDEXTERITY_VALUE(Integer.parseInt(dexterity_spinner.getSelectedItem().toString()));
            character.setCONSTITUTION_VALUE(Integer.parseInt(constitution_spinner.getSelectedItem().toString()));
            character.setINTELLIGENCE_VALUE(Integer.parseInt(intelligence_spinner.getSelectedItem().toString()));
            character.setWISDOM_VALUE(Integer.parseInt(wisdom_spinner.getSelectedItem().toString()));
            character.setCHARISMA_VALUE(Integer.parseInt(charisma_spinner.getSelectedItem().toString()));
        }

        newExtras.putSerializable("CHARACTER",character);
        newIntent.putExtras(newExtras);
        startActivity(newIntent);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rbRoll:
                if (checked)
                    strength_picker.setVisibility(View.GONE);
                    strength_spinner.setVisibility(View.VISIBLE);
                    dexterity_picker.setVisibility(View.GONE);
                    dexterity_spinner.setVisibility(View.VISIBLE);
                    constitution_picker.setVisibility(View.GONE);
                    constitution_spinner.setVisibility(View.VISIBLE);
                    intelligence_picker.setVisibility(View.GONE);
                    intelligence_spinner.setVisibility(View.VISIBLE);
                    wisdom_picker.setVisibility(View.GONE);
                    wisdom_spinner.setVisibility(View.VISIBLE);
                    charisma_picker.setVisibility(View.GONE);
                    charisma_spinner.setVisibility(View.VISIBLE);
                    adapter.add(Integer.toString(FourDSixBestThreeRoll()));
                    adapter.add(Integer.toString(FourDSixBestThreeRoll()));
                    adapter.add(Integer.toString(FourDSixBestThreeRoll()));
                    adapter.add(Integer.toString(FourDSixBestThreeRoll()));
                    adapter.add(Integer.toString(FourDSixBestThreeRoll()));
                    adapter.add(Integer.toString(FourDSixBestThreeRoll()));
                    adapter.notifyDataSetChanged();
                    break;
            case R.id.rbSelect:
                if (checked)
                    strength_picker.setVisibility(View.VISIBLE);
                    strength_spinner.setVisibility(View.GONE);
                    dexterity_picker.setVisibility(View.VISIBLE);
                    dexterity_spinner.setVisibility(View.GONE);
                    constitution_picker.setVisibility(View.VISIBLE);
                    constitution_spinner.setVisibility(View.GONE);
                    intelligence_picker.setVisibility(View.VISIBLE);
                    intelligence_spinner.setVisibility(View.GONE);
                    wisdom_picker.setVisibility(View.VISIBLE);
                    wisdom_spinner.setVisibility(View.GONE);
                    charisma_picker.setVisibility(View.VISIBLE);
                    charisma_spinner.setVisibility(View.GONE);
                    adapter.clear();
                    adapter.notifyDataSetChanged();
                    break;
        }
    }

    public int FourDSixBestThreeRoll() {
        int maxCount = 5;
        int returnValue = 0;
        ArrayList<Integer> rolls = new ArrayList<>();
        Random rand = new Random();
        for(int i = 0; i < 4; i++){
            if(i < 3) {
                int randNumber = rand.nextInt(maxCount) + 1;
                rolls.add(randNumber);
            } else {
                int randNumber = rand.nextInt(maxCount) + 1;
                for(int k = 0; k < rolls.size(); k++) {
                    if(randNumber > rolls.get(k)) {
                        rolls.set(k,randNumber);
                        k = rolls.size()+1;
                    }
                }
            }
        }
        for(int i = 0; i < rolls.size(); i++) {
            returnValue += rolls.get(i);
        }
        return returnValue;
    }
}
