package gr.aueb.softeng.project1806.view;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import gr.aueb.softeng.project1806.R;
import gr.aueb.softeng.project1806.dao.BusTimetableDAO;
import gr.aueb.softeng.project1806.memorydao.BusTimetableDAOMemory;
import gr.aueb.softeng.project1806.memorydao.LineDAOMemory;
import gr.aueb.softeng.project1806.memorydao.StopDAOMemory;
import gr.aueb.softeng.project1806.model.BusTimetable;
import gr.aueb.softeng.project1806.model.Line;
import gr.aueb.softeng.project1806.model.Stop;
import gr.aueb.softeng.project1806.presenter.StopSearchPresenter;

public class addLine extends AppCompatActivity {

    public static final int ADD_STOPS_TO_LINE_REQUEST_CODE = 1;
    private static final String[] Hours = new String[]{"00", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"};
    private static final String[] Days = new String[]{"Δευτέρα", "Τρίτη", "Τετάρτη", "Πέμπτη", "Παρασκευή",
            "Σάββατο", "Κυριακή", "Κάθε Μέρα"};
    private static final String[] Minutes = new String[]{"00", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24","25","26","27","28",
    "29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49",
            "50","51","52","53","54","55","56","57","58","59"};
    Button btnInsertLine;
    EditText edtAddLineCodeText, edtAddLineNameText;
    LineDAOMemory lineDAOMemory;
    StopDAOMemory stopDAOMemory;
    BusTimetableDAOMemory busTimetableDAOMemory;
    AutoCompleteTextView hours, mins, day;
    Line line;
    BusTimetable btt;
    ArrayList<Stop> stopsToAdd;
    StopSearchPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_line);


        btnInsertLine = (Button) findViewById(R.id.InsertLineButton);
        edtAddLineCodeText = findViewById(R.id.InputAddLineCodeText);
        edtAddLineNameText = findViewById(R.id.InputAddLineNameText);
        lineDAOMemory = new LineDAOMemory();
        stopDAOMemory = new StopDAOMemory();
        presenter = new StopSearchPresenter();
        busTimetableDAOMemory = new BusTimetableDAOMemory();
        mins = (AutoCompleteTextView) findViewById(R.id.MinutesText);
        ArrayAdapter<String> HourAdapter = new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, Hours);
        ArrayAdapter<String> MinuteAdapter = new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, Minutes);
        mins = (AutoCompleteTextView) findViewById(R.id.MinutesText);
        mins.setAdapter(MinuteAdapter);
        mins.setThreshold(0);

        hours = (AutoCompleteTextView) findViewById(R.id.HoursText);
        hours.setAdapter(HourAdapter);
        hours.setThreshold(0);
        ArrayAdapter<String> DayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, Days);
        day = (AutoCompleteTextView) findViewById(R.id.DayText);
        day.setAdapter(DayAdapter);
        day.setThreshold(0);

        btnInsertLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtAddLineCodeText.getText().toString() == null || edtAddLineNameText.getText().toString() == null ||
                        !isInteger(hours.getText().toString()) || !isInteger(mins.getText().toString()) || !Arrays.asList(Days).contains(day.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Παρακαλώ συμπλήρωσε όλα τα πεδία σωστά", Toast.LENGTH_SHORT).show();
                } else {
                    line = new Line(edtAddLineCodeText.getText().toString().trim(), edtAddLineNameText.getText().toString().trim());

                    btt = new BusTimetable(new Time(Integer.parseInt(hours.getText().toString().trim()),
                            Integer.parseInt(mins.getText().toString().trim()), 00), day.getText().toString());

                    busTimetableDAOMemory.add(btt);
                    line.addBusTimetable(btt);
                    //Intent pou phgainei sto activity me th lista twn stasewn
                    Intent myIntent = new Intent(v.getContext(), addStopsToLine.class);
                    startActivityForResult(myIntent, ADD_STOPS_TO_LINE_REQUEST_CODE);

                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_STOPS_TO_LINE_REQUEST_CODE)
            if (resultCode == RESULT_OK) {

                //int numberOfStopsToAdd = data.getIntExtra(addStopsToLine.STOPS_TO_ADD_EXTRA, 0);
                stopsToAdd = (ArrayList<Stop>) data.getSerializableExtra(addStopsToLine.STOPS_TO_ADD_EXTRA);
                Stop st;
                for (Stop stop : stopsToAdd) {
                    st = presenter.searchStopById(String.valueOf(stop.getStopID()));
                    line.addStop(st);
                    st.addLine(line);
                    }
                lineDAOMemory.add(line);
                Toast.makeText(this, "Οι στάσεις προστέθηκαν επιτυχώς", Toast.LENGTH_SHORT).show();
                finish();
            }

    }

    public boolean isInteger(String input) {
        try {
            Integer.parseInt(input.trim());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
