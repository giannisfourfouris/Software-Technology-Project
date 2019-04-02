package gr.aueb.softeng.project1806.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import gr.aueb.softeng.project1806.R;
import gr.aueb.softeng.project1806.model.Location;
import gr.aueb.softeng.project1806.model.Stop;
import gr.aueb.softeng.project1806.presenter.StopSearchPresenter;

public class stopInfo extends AppCompatActivity {
    Stop stop;
    Button btnSave;
    EditText edtCodeText, edtNameText, edtLongitudeText, edtLatiduteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_info);
        Intent intent = getIntent();

        String info = intent.getStringExtra(alterStop.STOP_INFO_EXTRA);

        StopSearchPresenter presenter = new StopSearchPresenter();
        stop = presenter.searchStopById(info);
        btnSave = (Button) findViewById(R.id.SaveStopButton);
        edtCodeText = (EditText) findViewById(R.id.InputStopIDText);
        edtNameText = (EditText) findViewById(R.id.InputStopNameText);
        edtLongitudeText = (EditText) findViewById(R.id.InputStopLongitudeText);
        edtLatiduteText = (EditText) findViewById(R.id.InputStopLatitudeText);

        edtCodeText.setText(String.valueOf(stop.getStopID()));
        edtNameText.setText(stop.getStopName());
        edtLongitudeText.setText(String.valueOf(stop.getLocation().getLongitude()));
        edtLatiduteText.setText(String.valueOf(stop.getLocation().getLatitude()));

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (isInteger(edtCodeText.getText().toString()) && isDouble(edtLatiduteText.getText().toString()) &&
                        isDouble(edtLongitudeText.getText().toString()) && !edtNameText.getText().toString().equals("")) {
                    stop.setStopID(Integer.parseInt(edtCodeText.getText().toString().trim()));
                    stop.setStopName(edtNameText.getText().toString().trim());
                    Location Loc =new Location(Double.parseDouble(edtLatiduteText.getText().toString().trim()),Double.parseDouble(edtLatiduteText.getText().toString().trim()));
                    stop.setLocation(Loc);
                    Toast.makeText(getApplicationContext(), "Επιτυχής Αποθήκευση.", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    if (!isInteger(edtCodeText.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Το ΙD πρέπει να είναι θετικος ακεραιος.\nΕισαγωγή ανεπιτυχής.", Toast.LENGTH_SHORT).show();
                    }
                    if (edtNameText.getText().toString().equals("")) {
                        Toast.makeText(getApplicationContext(), "Το όνομα της στάσης είναι κενό.\nΕισαγωγή ανεπιτυχής.", Toast.LENGTH_SHORT).show();
                    }
                    if (!isDouble(edtLatiduteText.getText().toString()) || !isDouble(edtLongitudeText.getText().toString())) {
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
