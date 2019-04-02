package gr.aueb.softeng.project1806.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import gr.aueb.softeng.project1806.R;
import gr.aueb.softeng.project1806.model.Location;
import gr.aueb.softeng.project1806.model.Stop;
import gr.aueb.softeng.project1806.presenter.StopSearchPresenter;

public class userStopSearchByLocResult extends AppCompatActivity {

    ListView stopList;
    List<Stop> stop;
    StopSearchPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_stop_search_by_loc_result);

        presenter = new StopSearchPresenter();

        Intent intent = getIntent();
        Location loc = new Location(intent.getDoubleExtra(userStopSearch.LOCATION_LATITUDE, 0.0), (intent.getDoubleExtra(userStopSearch.LOCATION_LONGITUDE, 0.0)));
        stop = presenter.searchStopByLocation(loc);

        if (stop.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Καμία κοντινή στάση", Toast.LENGTH_SHORT).show();
            finish();
        }

        ArrayList<String> StringStop = new ArrayList<>();
        for (Stop stop : stop) {
            StringStop.add("Κωδικός: " + stop.getStopID() + "\nΌνομα: " + stop.getStopName() + "\nΑπόσταση από στάση: "+ stop.getLocation().distanceBetweenLocations(loc));
        }

        ArrayAdapter<String> stops = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, StringStop);


        stopList = (ListView) findViewById(R.id.stopsListView);

        stopList.setAdapter(stops);


    }
}

//38.026605, 23.787613