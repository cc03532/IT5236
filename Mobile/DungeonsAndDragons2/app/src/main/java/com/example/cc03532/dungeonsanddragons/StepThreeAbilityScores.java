package com.example.cc03532.dungeonsanddragons;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

public class StepThreeAbilityScores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_three_ability_scores);

        TextView tvRemainingPoints = (TextView) findViewById(R.id.tvRemainingPoints);

        NumberPicker strength_picker = (NumberPicker) findViewById(R.id.strength_picker);
        assert strength_picker != null;
        strength_picker.setMinValue(8);
        strength_picker.setMaxValue(18);

        NumberPicker dexterity_picker = (NumberPicker) findViewById(R.id.dexterity_picker);
        assert dexterity_picker != null;
        dexterity_picker.setMinValue(8);
        dexterity_picker.setMaxValue(18);

        NumberPicker constitution_picker = (NumberPicker) findViewById(R.id.constitution_picker);
        assert constitution_picker != null;
        constitution_picker.setMinValue(8);
        constitution_picker.setMaxValue(18);


        NumberPicker intelligence_picker = (NumberPicker) findViewById(R.id.intelligence_picker);
        assert intelligence_picker != null;
        intelligence_picker.setMinValue(8);
        intelligence_picker.setMaxValue(18);


        NumberPicker wisdom_picker = (NumberPicker) findViewById(R.id.wisdom_picker);
        assert wisdom_picker != null;
        wisdom_picker.setMinValue(8);
        wisdom_picker.setMaxValue(18);


        NumberPicker charisma_picker = (NumberPicker) findViewById(R.id.charisma_picker);
        assert charisma_picker != null;
        charisma_picker.setMinValue(8);
        charisma_picker.setMaxValue(18);

    }

    public void stepThreeSubmit(View view) {

        Bundle extras = getIntent().getExtras();
        final GlobalVariables character = (GlobalVariables) extras.getSerializable("CHARACTER");

        Intent newIntent = new Intent(this, StepFourDescription.class);
        Bundle newExtras = new Bundle();

        NumberPicker strength_picker = (NumberPicker) findViewById(R.id.strength_picker);
        character.setSTRENGTH_VALUE(strength_picker.getValue());

        NumberPicker dexterity_picker = (NumberPicker) findViewById(R.id.dexterity_picker);
        character.setDEXTERITY_VALUE(dexterity_picker.getValue());

        NumberPicker constitution_picker = (NumberPicker) findViewById(R.id.constitution_picker);
        character.setCONSTITUTION_VALUE(constitution_picker.getValue());

        NumberPicker intelligence_picker = (NumberPicker) findViewById(R.id.intelligence_picker);
        character.setINTELLIGENCE_VALUE(intelligence_picker.getValue());

        NumberPicker wisdom_picker = (NumberPicker) findViewById(R.id.wisdom_picker);
        character.setWISDOM_VALUE(wisdom_picker.getValue());

        NumberPicker charisma_picker = (NumberPicker) findViewById(R.id.charisma_picker);
        character.setCHARISMA_VALUE(charisma_picker.getValue());

        newExtras.putSerializable("CHARACTER",character);
        newIntent.putExtras(newExtras);
        startActivity(newIntent);
    }
}
