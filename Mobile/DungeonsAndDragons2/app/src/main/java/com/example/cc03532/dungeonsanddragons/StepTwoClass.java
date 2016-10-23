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

public class StepTwoClass extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_two_class);

        Bundle extras = getIntent().getExtras();
        GlobalVariables character = (GlobalVariables) extras.getSerializable("CHARACTER");

        TextView tvCurrent = (TextView) findViewById(R.id.tvCurrent);

        if (tvCurrent != null) {

            tvCurrent.setText("");

            tvCurrent.setText("Not Triggering tvCurrent.setText();");

            if (character != null) {
                tvCurrent.setText(character.getCLASS_VALUE());
                tvCurrent.append("\n"+character.getHIT_DIE());
                tvCurrent.append("\n"+character.getSTRENGTH_VALUE());
            } else {
                tvCurrent.setText("Character is Null");
            }
        }

        final Spinner class_spinner = (Spinner) findViewById(R.id.class_spinner);
        ArrayAdapter<CharSequence> class_adapter = ArrayAdapter.createFromResource(this,
                R.array.class_array, android.R.layout.simple_spinner_item);
        class_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        assert class_spinner != null;
        class_spinner.setAdapter(class_adapter);

        class_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View v, int position, long id) {

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
}
