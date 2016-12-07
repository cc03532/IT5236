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

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class StepTwoClass extends AppCompatActivity {

    TextView class_description;
    JSONObject joClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_two_class);

        Bundle extras = getIntent().getExtras();
        GlobalVariables character = (GlobalVariables) extras.getSerializable("CHARACTER");

        class_description = (TextView) findViewById(R.id.class_description);

        final Spinner class_spinner = (Spinner) findViewById(R.id.class_spinner);
        ArrayAdapter<CharSequence> class_adapter = ArrayAdapter.createFromResource(this,
                R.array.class_array, android.R.layout.simple_spinner_item);
        class_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        assert class_spinner != null;
        class_spinner.setAdapter(class_adapter);
        if (character != null && character.getCLASS_VALUE() != null) {
            class_spinner.setSelection(class_adapter.getPosition(character.getCLASS_VALUE()));
        }

        try {
            InputStream is = StepTwoClass.this.getAssets().open("class_desc.json");

            int size = is.available();

            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String text = new String(buffer);
            joClass = new JSONObject(text);


        } catch (IOException | JSONException e) {
            class_description.setText(e.toString());
        }

        class_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View v, int position, long id) {
                switch (class_spinner.getSelectedItem().toString()) {
                    case "Barbarian":
                        DisplayClassAttributes(0);
                        break;
                    case "Bard":
                        DisplayClassAttributes(1);
                        break;
                    case "Cleric":
                        DisplayClassAttributes(2);
                        break;
                    case "Druid":
                        DisplayClassAttributes(3);
                        break;
                    case "Fighter":
                        DisplayClassAttributes(4);
                        break;
                    case "Monk":
                        DisplayClassAttributes(5);
                        break;
                    case "Paladin":
                        DisplayClassAttributes(6);
                        break;
                    case "Ranger":
                        DisplayClassAttributes(7);
                        break;
                    case "Rogue":
                        DisplayClassAttributes(8);
                        break;
                    case "Sorcerer":
                        DisplayClassAttributes(9);
                        break;
                    case "Warlock":
                        DisplayClassAttributes(10);
                        break;
                    case "Wizard":
                        DisplayClassAttributes(11);
                        break;
                }
            }

            public void onNothingSelected(AdapterView<?> arg0) {
                Log.v("routes", "nothing selected");
            }
        });
    }

    public void stepTwoSubmit(View view) {

        Bundle extras = getIntent().getExtras();
        GlobalVariables character = (GlobalVariables) extras.getSerializable("CHARACTER");

        Intent newIntent = new Intent(this, StepThreeAbilityScores.class);
        Bundle newExtras = new Bundle();

        Spinner class_spinner = (Spinner) findViewById(R.id.class_spinner);
        if (class_spinner != null) {
            if (character != null) {
                character.setCLASS_VALUE(class_spinner.getSelectedItem().toString());
            }
        }


        if (character != null) {
            switch (character.getCLASS_VALUE()) {
                case "Barbarian":
                    character.setHIT_DIE("d12");
                    break;
                case "Bard":
                    character.setHIT_DIE("d8");
                    break;
                case "Cleric":
                    character.setHIT_DIE("d8");
                    break;
                case "Druid":
                    character.setHIT_DIE("d8");
                    break;
                case "Fighter":
                    character.setHIT_DIE("d10");
                    break;
                case "Monk":
                    character.setHIT_DIE("d8");
                    break;
                case "Paladin":
                    character.setHIT_DIE("d10");
                    break;
                case "Ranger":
                    character.setHIT_DIE("d10");
                    break;
                case "Rogue":
                    character.setHIT_DIE("d8");
                    break;
                case "Sorcerer":
                    character.setHIT_DIE("d6");
                    break;
                case "Warlock":
                    character.setHIT_DIE("d8");
                    break;
                case "Wizard":
                    character.setHIT_DIE("d6");
                    break;
            }
        }

        newExtras.putSerializable("CHARACTER",character);
        newIntent.putExtras(newExtras);
        startActivity(newIntent);
    }

    public void DisplayClassAttributes (int cClass) {
        try {
            class_description.setText("\nClass Description:\n");
            class_description.append(joClass.getJSONArray("Classes").getJSONObject(cClass).getString("Description"));
            class_description.append("\n\n");
            class_description.append("HitDie: " + joClass.getJSONArray("Classes").getJSONObject(cClass).getString("HitDie"));
            class_description.append("\n\n");
            class_description.append("Primary Attribute:\n   " + joClass.getJSONArray("Classes").getJSONObject(cClass).getString("Primary Ability"));
            class_description.append("\n\n");
            int savingLength = joClass.getJSONArray("Classes").getJSONObject(cClass).getJSONArray("Saving Throw Proficiencies").length();
            if(savingLength > 0) {
                class_description.append("Saving Throw Proficiencies:");
                while (savingLength > 0) {
                    savingLength--;
                    class_description.append("\n");
                    class_description.append("   ");
                    class_description.append(joClass.getJSONArray("Classes").getJSONObject(cClass).getJSONArray("Saving Throw Proficiencies").getJSONObject(savingLength).getString("Saving Throw Proficiency"));
                }
            }
            int armorLength = joClass.getJSONArray("Classes").getJSONObject(cClass).getJSONArray("Armor Proficiencies").length();
            class_description.append("\n\n");
            class_description.append("Armor Proficiencies:");
            if(armorLength > 0) {
                while (armorLength > 0) {
                    armorLength--;
                    class_description.append("\n");
                    class_description.append("   ");
                    class_description.append(joClass.getJSONArray("Classes").getJSONObject(cClass).getJSONArray("Armor Proficiencies").getJSONObject(armorLength).getString("Armor Proficiency"));
                }
            } else {
                class_description.append("\n   None");
            }
            int weaponLength = joClass.getJSONArray("Classes").getJSONObject(cClass).getJSONArray("Weapon Proficiencies").length();
            class_description.append("\n\n");
            class_description.append("Weapon Proficiencies:");
            if(weaponLength > 0) {
                while (weaponLength > 0) {
                    weaponLength--;
                    class_description.append("\n");
                    class_description.append("   ");
                    class_description.append(joClass.getJSONArray("Classes").getJSONObject(cClass).getJSONArray("Weapon Proficiencies").getJSONObject(weaponLength).getString("Weapon Proficiency"));
                }
            } else {
                class_description.append("\n   None");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
