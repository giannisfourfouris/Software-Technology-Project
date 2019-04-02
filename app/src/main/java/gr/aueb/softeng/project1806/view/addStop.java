package gr.aueb.softeng.project1806.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import gr.aueb.softeng.project1806.R;
import gr.aueb.softeng.project1806.memorydao.LocationDAOMemory;
import gr.aueb.softeng.project1806.memorydao.StopDAOMemory;
import gr.aueb.softeng.project1806.model.Location;
import gr.aueb.softeng.project1806.model.Stop;

public class addStop extends AppCompatActivity {

    Button btnInsertStop;
    EditText edtStopNameText, edtStopIDText, edtStopLongitudeText, edtStopLatitudeText;
    StopDAOMemory stopDAOMemory;
    LocationDAOMemory locationDAOMemory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stop);

        btnInsertStop = (Button) findViewById(R.id.InsertStopButton);
        edtStopIDText = findViewById(R.id.InputStopIDText);
        edtStopNameText = findViewById(R.id.InputStopNameText);
        edtStopLongitudeText = findViewById(R.id.InputStopLongitudeText);
        edtStopLatitudeText = findViewById(R.id.InputStopLatitudeText);
        locationDAOMemory = new LocationDAOMemory();
        stopDAOMemory = new StopDAOMemory();

        btnInsertStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isInteger(edtStopIDText.getText().toString()) && isDouble(edtStopLatitudeText.getText().toString()) &&
                        isDouble(edtStopLongitudeText.getText().toString()) && !edtStopNameText.getText().toString().equals("")) {
                    Stop stop = new Stop(Integer.parseInt(edtStopIDText.getText().toString().trim()), edtStopNameText.getText().toString().trim(), new Location(Double.parseDouble(edtStopLongitudeText.getText().toString().trim()), Double.parseDouble(edtStopLatitudeText.getText().toString().trim())));
                    stopDAOMemory.add(stop);
                    locationDAOMemory.add(stop.getLocation());
                    Toast.makeText(getApplicationContext(), "Εισαγωγή επιτυχής.", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    if (!isInteger(edtStopIDText.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Το ΙD πρέπει να είναι θετικος ακεραιος.\nΕισαγωγή ανεπιτυχής.", Toast.LENGTH_SHORT).show();
                    }
                    if (edtStopNameText.getText().toString().equals("")) {
                        Toast.makeText(getApplicationContext(), "Το όνομα της στάσης είναι κενό.\nΕισαγωγή ανεπιτυχής.", Toast.LENGTH_SHORT).show();
                    }
                    if (!isDouble(edtStopLatitudeText.getText().toString()) || !isDouble(edtStopLongitudeText.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Το μήκος και το πλάτος της τοποθεσίας πρέπει να είναι Double.\nΕισαγωγή ανεπιτυχής.", Toast.LENGTH_SHORT).show();
                    }


                }


            }
        });


    }

    public boolean isInteger(String input) {
        try {
            Integer.parseInt(input.trim());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDouble(String input) {
        try {
            Double.parseDouble(input.trim());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
