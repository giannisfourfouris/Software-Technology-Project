package gr.aueb.softeng.project1806.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import gr.aueb.softeng.project1806.R;
import gr.aueb.softeng.project1806.memorydao.StopDAOMemory;
import gr.aueb.softeng.project1806.model.Location;
import gr.aueb.softeng.project1806.model.Stop;
import gr.aueb.softeng.project1806.presenter.ItemSelectionListener;

public class addStopsToLine extends AppCompatActivity implements ItemSelectionListener<Stop> {

    public static final String STOPS_TO_ADD_EXTRA = "Stops to add to Line";

    Button btnSaveStopsToLine;
    ArrayList<Stop> stopsToAdd = new ArrayList<Stop>();
    ArrayList<String> test;
    RecyclerView recyclerView;
    private StopAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private StopDAOMemory stopDAOMemory;
    Stop stop = new Stop(11, "11hstash", new Location(43.12212,23.232332));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stops_to_line);
        Intent intent = getIntent();

        test = new ArrayList<String>();
        stopsToAdd = new ArrayList<Stop>();
        stopDAOMemory = new StopDAOMemory();
        List<Stop> stops = stopDAOMemory.findAll();
        btnSaveStopsToLine = (Button) findViewById(R.id.SaveStopsToLineButton);

        recyclerView = (RecyclerView) findViewById(R.id.rvLines);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new StopAdapter(new ArrayList<Stop>(stops));
        recyclerView.setAdapter(mAdapter);
        // register the current activity as listener for book selection events
        mAdapter.setStopSelectionListener(this);

        btnSaveStopsToLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stopsToAdd.size() >= 3){
                    Intent intent = new Intent();
                    intent.putExtra(STOPS_TO_ADD_EXTRA, stopsToAdd);
                    setResult(RESULT_OK, intent);
                    finish();
                } else{
                    Toast.makeText(getApplicationContext(), "Η γραμμή δεν μπορεί να έχει λιγότερο απο 3 στάσεις", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }



    @Override
    public void onItemSelected(Stop stop, int id){
        if (id == R.id.TempAddStopButton){
            stopsToAdd.add(stop);
            Toast.makeText(this, "Στάση : " + stop.getStopName() + " προστέθηκε", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.TempRemoveStopButton){
            stopsToAdd.remove(stop);
            Toast.makeText(this, "Σταση : " + stop.getStopName() + " αφαιρέθηκε", Toast.LENGTH_SHORT).show();
        }

    }
}
