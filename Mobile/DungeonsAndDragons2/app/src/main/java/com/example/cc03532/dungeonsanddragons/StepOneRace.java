package com.example.cc03532.dungeonsanddragons;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Random;

public class StepOneRace extends AppCompatActivity {

    JSONObject joRace;
    TextView race_description;
    TextView subrace_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_one_race);

        Bundle extras = getIntent().getExtras();
        final GlobalVariables character = (GlobalVariables) extras.getSerializable("CHARACTER");

        race_description = (TextView) findViewById(R.id.race_description);
        subrace_description = (TextView) findViewById(R.id.subrace_description);
        final Spinner race_spinner = (Spinner) findViewById(R.id.race_spinner);
        ArrayAdapter<CharSequence> race_adapter = ArrayAdapter.createFromResource(this,
                R.array.race_array, android.R.layout.simple_spinner_item);
        race_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (race_spinner != null) {
            race_spinner.setAdapter(race_adapter);
        }

        try {
            InputStream is = StepOneRace.this.getAssets().open("race_desc.json");

            int size = is.available();

            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String text = new String(buffer);
            joRace = new JSONObject(text);


        } catch (IOException | JSONException e) {
            race_description.setText(e.toString());
        }

        final Spinner subrace_spinner = (Spinner) findViewById(R.id.subrace_spinner);

        if (race_spinner != null) {
            race_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> arg0, View v, int position, long id)
                {
                    Context context = getBaseContext();
                    ArrayAdapter<CharSequence> subrace_adapter = ArrayAdapter.createFromResource(context,R.array.dwarf_subrace_array, android.R.layout.simple_spinner_item);
                    switch (race_spinner.getSelectedItem().toString()) {
                        case "Dwarf":
                            DisplayRaceAttributes(0);
                            subrace_adapter = ArrayAdapter.createFromResource(context,
                                    R.array.dwarf_subrace_array, android.R.layout.simple_spinner_item);
                            subrace_adapter.notifyDataSetChanged();
                            break;
                        case "Elf":
                            DisplayRaceAttributes(1);
                            subrace_adapter = ArrayAdapter.createFromResource(context,
                                    R.array.elf_subrace_array, android.R.layout.simple_spinner_item);
                            subrace_adapter.notifyDataSetChanged();
                            break;
                        case "Halfling":
                            DisplayRaceAttributes(2);
                            subrace_adapter = ArrayAdapter.createFromResource(context,
                                    R.array.halfling_subrace_array, android.R.layout.simple_spinner_item);
                            subrace_adapter.notifyDataSetChanged();
                            break;
                        case "Human":
                            DisplayRaceAttributes(3);
                            subrace_adapter = ArrayAdapter.createFromResource(context,
                                    R.array.human_subrace_array, android.R.layout.simple_spinner_item);
                            subrace_adapter.notifyDataSetChanged();
                            break;
                        case "Dragonborn":
                            DisplayRaceAttributes(4);
                            subrace_adapter = ArrayAdapter.createFromResource(context,
                                    R.array.dragonborn_subrace_array, android.R.layout.simple_spinner_item);
                            subrace_adapter.notifyDataSetChanged();
                            break;
                        case "Gnome":
                            DisplayRaceAttributes(5);
                            subrace_adapter = ArrayAdapter.createFromResource(context,
                                    R.array.gnome_subrace_array, android.R.layout.simple_spinner_item);
                            subrace_adapter.notifyDataSetChanged();
                            break;
                        case "Half-Elf":
                            DisplayRaceAttributes(6);
                            subrace_adapter = ArrayAdapter.createFromResource(context,
                                    R.array.halfelf_subrace_array, android.R.layout.simple_spinner_item);
                            subrace_adapter.notifyDataSetChanged();
                            break;
                        case "Half-Orc":
                            DisplayRaceAttributes(7);
                            subrace_adapter = ArrayAdapter.createFromResource(context,
                                    R.array.halforc_subrace_array, android.R.layout.simple_spinner_item);
                            subrace_adapter.notifyDataSetChanged();
                            break;
                        case "Tiefling":
                            DisplayRaceAttributes(8);
                            subrace_adapter = ArrayAdapter.createFromResource(context,
                                    R.array.tiefling_subrace_array, android.R.layout.simple_spinner_item);
                            subrace_adapter.notifyDataSetChanged();
                            break;
                    }

                    if (subrace_adapter != null) {
                        subrace_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        if (subrace_spinner != null) {
                            subrace_spinner.setAdapter(subrace_adapter);
                            subrace_adapter.notifyDataSetChanged();
                            if(character.getSUBRACE_VALUE()!=null){
                                subrace_spinner.setSelection(subrace_adapter.getPosition(character.getSUBRACE_VALUE()));
                            }
                        }

                    }
                }


                public void onNothingSelected(AdapterView<?> arg0)
                {
                    Log.v("routes", "nothing selected");
                }
            });
        }

        if (subrace_spinner != null) {
            subrace_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> arg0, View v, int position, long id)
                {
                    switch (subrace_spinner.getSelectedItem().toString()) {
                        case "Hill Dwarf":
                            DisplaySubRaceAttributes(0,0);
                            break;
                        case "Mountain Dwarf":
                            DisplaySubRaceAttributes(0,1);
                            break;
                        case "High Elf":
                            DisplaySubRaceAttributes(1,0);
                            break;
                        case "Wood Elf":
                            DisplaySubRaceAttributes(1,1);
                            break;
                        case "Dark Elf (Drow)":
                            DisplaySubRaceAttributes(1,2);
                            break;
                        case "Lightfoot":
                            DisplaySubRaceAttributes(2,0);
                            break;
                        case "Stout":
                            DisplaySubRaceAttributes(2,1);
                            break;
                        case "Damaran":
                            subrace_description.setText("");
                            break;
                        case "Illuskan":
                            subrace_description.setText("");
                            break;
                        case "Mulan":
                            subrace_description.setText("");
                            break;
                        case "Rashemi":
                            subrace_description.setText("");
                            break;
                        case "Shou":
                            subrace_description.setText("");
                            break;
                        case "Tethyrian":
                            subrace_description.setText("");
                            break;
                        case "Turami":
                            subrace_description.setText("");
                            break;
                        case "Black Dragon":
                            subrace_description.setText("");
                            break;
                        case "Blue Dragon":
                            subrace_description.setText("");
                            break;
                        case "Brass Dragon":
                            subrace_description.setText("");
                            break;
                        case "Bronze Dragon":
                            subrace_description.setText("");
                            break;
                        case "Copper Dragon":
                            subrace_description.setText("");
                            break;
                        case "Gold Dragon":
                            subrace_description.setText("");
                            break;
                        case "Green Dragon":
                            subrace_description.setText("");
                            break;
                        case "Red Dragon":
                            subrace_description.setText("");
                            break;
                        case "Silver Dragon":
                            subrace_description.setText("");
                            break;
                        case "White Dragon":
                            subrace_description.setText("");
                            break;
                        case "Forest Gnome":
                            DisplaySubRaceAttributes(5,0);
                            break;
                        case "Rock Gnome":
                            DisplaySubRaceAttributes(5,1);
                            break;
                        case "Half-Elf":
                            subrace_description.setText("");
                            break;
                        case "Half-Orc":
                            subrace_description.setText("");
                            break;
                        case "Tiefling":
                            subrace_description.setText("");
                            break;
                    }
                }
                public void onNothingSelected(AdapterView<?> arg0)
                {
                    Log.v("routes", "nothing selected");
                }
            });
        }

        if (character.getRACE_VALUE()!=null) {
            race_spinner.setSelection(race_adapter.getPosition(character.getRACE_VALUE().toString()));
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

    public void DisplayRaceAttributes(int race) {
        try {
            race_description.setText("Race Modifications:\n");
            race_description.append(joRace.getJSONArray("Races").getJSONObject(race).getString("Stats"));
            race_description.append("\n");
            race_description.append(joRace.getJSONArray("Races").getJSONObject(race).getString("Speed"));
            int traitLength = joRace.getJSONArray("Races").getJSONObject(race).getJSONArray("Traits").length();
            if(traitLength > 0) {
                race_description.append("\n");
                race_description.append("Traits:");
                while (traitLength > 0) {
                    traitLength--;
                    race_description.append("\n");
                    race_description.append("   ");
                    race_description.append(joRace.getJSONArray("Races").getJSONObject(race).getJSONArray("Traits").getJSONObject(traitLength).getString("Trait"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void DisplaySubRaceAttributes(int race, int subrace) {
        try {
            subrace_description.setText("\nSub-Race Modifications:\n");
            subrace_description.append(joRace.getJSONArray("Races").getJSONObject(race).getJSONArray("SubRace").getJSONObject(subrace).getString("Stats"));
            subrace_description.append("\n");
            int traitLength = joRace.getJSONArray("Races").getJSONObject(race).getJSONArray("SubRace").getJSONObject(subrace).getJSONArray("Traits").length();
            if(traitLength > 0) {
                subrace_description.append("Traits:");
                while (traitLength > 0) {
                    traitLength--;
                    subrace_description.append("\n");
                    subrace_description.append("   ");
                    subrace_description.append(joRace.getJSONArray("Races").getJSONObject(race).getJSONArray("SubRace").getJSONObject(subrace).getJSONArray("Traits").getJSONObject(traitLength).getString("Trait"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

