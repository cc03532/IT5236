package com.example.cc03532.dungeonsanddragons;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Random;

public class StepOneRace extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_one_race);

        Bundle extras = getIntent().getExtras();
        GlobalVariables character = (GlobalVariables) extras.getSerializable("CHARACTER");

        TextView tvCurrent = (TextView) findViewById(R.id.tvCurrent);

        if (tvCurrent != null) {

            tvCurrent.setText("");

            tvCurrent.setText("Not Triggering tvCurrent.setText();");

            if (character != null) {
                tvCurrent.setText(character.getRACE_VALUE());
                tvCurrent.append("\n"+character.getSUBRACE_VALUE());
            } else {
                tvCurrent.setText("Character is Null");
            }
        }

        final Spinner race_spinner = (Spinner) findViewById(R.id.race_spinner);
        ArrayAdapter<CharSequence> race_adapter = ArrayAdapter.createFromResource(this,
                R.array.race_array, android.R.layout.simple_spinner_item);
        race_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (race_spinner != null) {
            race_spinner.setAdapter(race_adapter);
        }

        if (race_spinner != null) {
            race_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> arg0, View v, int position, long id)
                {
                    subraceArray(race_spinner.getSelectedItem().toString());
                }

                public void onNothingSelected(AdapterView<?> arg0)
                {
                    Log.v("routes", "nothing selected");
                }
            });
        }

        final Spinner subrace_spinner = (Spinner) findViewById(R.id.subrace_spinner);

        if (subrace_spinner != null) {
            subrace_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> arg0, View v, int position, long id)
                {

                }
                public void onNothingSelected(AdapterView<?> arg0)
                {
                    Log.v("routes", "nothing selected");
                }
            });
        }
    }

    public void raceRandomizer (View view) {
        Spinner spinner = (Spinner) findViewById(R.id.race_spinner);
        int maxCount = 0;
        if (spinner != null) {
            maxCount = spinner.getAdapter().getCount();
        }
        Random rand = new Random();
        int randRace = rand.nextInt(maxCount);
        if (spinner != null) {
            spinner.setSelection(randRace);
        }
    }

    public void subraceRandomizer (View view) {
        Spinner spinner = (Spinner) findViewById(R.id.subrace_spinner);
        int maxCount = 0;
        if (spinner != null) {
            maxCount = spinner.getAdapter().getCount();
        }
        Random rand = new Random();
        int subrandRace = rand.nextInt(maxCount);
        if (spinner != null) {
            spinner.setSelection(subrandRace);
        }
    }

    public void stepOneSubmit(View view) {

        Bundle extras = getIntent().getExtras();
        GlobalVariables character = (GlobalVariables) extras.getSerializable("CHARACTER");

        Intent intent = new Intent(this, StepTwoClass.class);

        Spinner race_spinner = (Spinner) findViewById(R.id.race_spinner);
        if (race_spinner != null) {
            character.setRACE_VALUE(race_spinner.getSelectedItem().toString());
        }

        Spinner subrace_spinner = (Spinner) findViewById(R.id.subrace_spinner);
        if (subrace_spinner != null) {
            character.setSUBRACE_VALUE(subrace_spinner.getSelectedItem().toString());
        }

        switch (character.getRACE_VALUE()) {
            case "Dwarf":
                character.setCONSTITUTION_RACE_MODIFIER(2);
                if (character.getSUBRACE_VALUE().equals("Hill Dwarf")) {
                    character.setWISDOM_RACE_MODIFIER(1);
                } else if (character.getSUBRACE_VALUE().equals("Mountain Dwarf")) {
                    character.setSTRENGTH_RACE_MODIFIER(2);
                }
                break;
            case "Elf":
                character.setDEXTERITY_RACE_MODIFIER(2);
                switch (character.getSUBRACE_VALUE()) {
                    case "High Elf":
                        character.setINTELLIGENCE_RACE_MODIFIER(1);
                        break;
                    case "Wood Elf":
                        character.setWISDOM_RACE_MODIFIER(1);
                        break;
                    case "Dark Elf (Drow)":
                        character.setCHARISMA_RACE_MODIFIER(1);
                        break;
                }
                break;
            case "Halfling":
                character.setDEXTERITY_RACE_MODIFIER(2);
                if (character.getSUBRACE_VALUE().equals("Lightfoot")) {
                    character.setCHARISMA_RACE_MODIFIER(1);
                } else if (character.getSUBRACE_VALUE().equals("Stout")) {
                    character.setCONSTITUTION_RACE_MODIFIER(1);
                }
                break;
            case "Human":

                character.setSTRENGTH_RACE_MODIFIER(1);
                character.setDEXTERITY_RACE_MODIFIER(1);
                character.setCONSTITUTION_RACE_MODIFIER(1);
                character.setINTELLIGENCE_RACE_MODIFIER(1);
                character.setWISDOM_RACE_MODIFIER(1);
                character.setCHARISMA_RACE_MODIFIER(1);

                switch (character.getSUBRACE_VALUE()) {
                    case "Damaran":

                        break;
                    case "Illuskan":

                        break;
                    case "Mulan":

                        break;
                    case "Rashemi":

                        break;
                    case "Shou":

                        break;
                    case "Tethyrian":

                        break;
                    case "Turami":

                        break;
                }
                break;
            case "Dragonborn":
                character.setSTRENGTH_RACE_MODIFIER(2);
                character.setCHARISMA_RACE_MODIFIER(1);
                switch (character.getSUBRACE_VALUE()) {
                    case "Black Dragon":

                        break;
                    case "Blue Dragon":

                        break;
                    case "Brass Dragon":

                        break;
                    case "Bronze Dragon":

                        break;
                    case "Copper Dragon":

                        break;
                    case "Gold Dragon":

                        break;
                    case "Green Dragon":

                        break;
                    case "Red Dragon":

                        break;
                    case "Silver Dragon":

                        break;
                    case "White Dragon":

                        break;
                }
                break;
            case "Gnome":
                character.setINTELLIGENCE_RACE_MODIFIER(2);
                if (character.getSUBRACE_VALUE().equals("Forest Gnome")) {
                    character.setDEXTERITY_RACE_MODIFIER(1);
                } else if (character.getSUBRACE_VALUE().equals("Rock Gnome")) {
                    character.setCONSTITUTION_RACE_MODIFIER(1);
                }
                break;
            case "Half-Elf":
                character.setCHARISMA_RACE_MODIFIER(2);
                //Choose Two
                break;
            case "Half-Orc":
                character.setSTRENGTH_RACE_MODIFIER(2);
                character.setCONSTITUTION_RACE_MODIFIER(1);
                break;
            case "Tiefling":
                character.setINTELLIGENCE_RACE_MODIFIER(1);
                character.setCHARISMA_RACE_MODIFIER(2);
                break;
        }

        Bundle newExtras = new Bundle();
        newExtras.putSerializable("CHARACTER",character);
        intent.putExtras(newExtras);
        startActivity(intent);
    }

    public void subraceArray(String race_value) {

        Spinner subrace_spinner = (Spinner) findViewById(R.id.subrace_spinner);
        ArrayAdapter<CharSequence> race_adapter = null;

        switch (race_value) {
            case "Dwarf":
                race_adapter = ArrayAdapter.createFromResource(this,
                        R.array.dwarf_subrace_array, android.R.layout.simple_spinner_item);
                break;
            case "Elf":
                race_adapter = ArrayAdapter.createFromResource(this,
                        R.array.elf_subrace_array, android.R.layout.simple_spinner_item);
                break;
            case "Halfling":
                race_adapter = ArrayAdapter.createFromResource(this,
                        R.array.halfling_subrace_array, android.R.layout.simple_spinner_item);
                break;
            case "Human":
                race_adapter = ArrayAdapter.createFromResource(this,
                        R.array.human_subrace_array, android.R.layout.simple_spinner_item);
                break;
            case "Dragonborn":
                race_adapter = ArrayAdapter.createFromResource(this,
                        R.array.dragonborn_subrace_array, android.R.layout.simple_spinner_item);
                break;
            case "Gnome":
                race_adapter = ArrayAdapter.createFromResource(this,
                        R.array.gnome_subrace_array, android.R.layout.simple_spinner_item);
                break;
            case "Half-Elf":
                race_adapter = ArrayAdapter.createFromResource(this,
                        R.array.halfelf_subrace_array, android.R.layout.simple_spinner_item);
                break;
            case "Half-Orc":
                race_adapter = ArrayAdapter.createFromResource(this,
                        R.array.halforc_subrace_array, android.R.layout.simple_spinner_item);
                break;
            case "Tiefling":
                race_adapter = ArrayAdapter.createFromResource(this,
                        R.array.tiefling_subrace_array, android.R.layout.simple_spinner_item);
                break;
        }

        if (race_adapter != null) {
            race_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        }
        if (subrace_spinner != null) {
            subrace_spinner.setAdapter(race_adapter);
        }
    }
}

