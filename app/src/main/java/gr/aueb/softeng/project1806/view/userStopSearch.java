package gr.aueb.softeng.project1806.view;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

import gr.aueb.softeng.project1806.R;
import gr.aueb.softeng.project1806.memorydao.StopDAOMemory;
import gr.aueb.softeng.project1806.model.Stop;
import gr.aueb.softeng.project1806.presenter.StopSearchPresenter;

public class userStopSearch extends AppCompatActivity {

    private FusedLocationProviderClient mFusedLocationClient;
    EditText edtStopSearch;
    AutoCompleteTextView stops;
    StopDAOMemory stopDAOMemory;
    public static final String STOP_INFO_EXTRA = "info";
    public static final int REQUEST_CODE_STOP_SEARCH = 1;
    public static final String LOCATION_LONGITUDE = "longitude";
    public static final String LOCATION_LATITUDE = "latitude";
    Button userSearchStopButton;
    double longitude,latitude;
    private static String[] allStopInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_stop_search);
        edtStopSearch=(EditText)findViewById(R.id.userInputInfoText2);

        stopDAOMemory = new StopDAOMemory();
        List<Stop> stopList= stopDAOMemory.findAll();
        allStopInfo = new String[stopList.size() * 2];
        int i=0;
        for (Stop stop : stopList){
            allStopInfo[i] = stop.getStopName();
            i++;
            allStopInfo[i] = String.valueOf(stop.getStopID());
            i++;
        }
        stops = (AutoCompleteTextView) findViewById(R.id.userInputInfoText2);

        ArrayAdapter<String> stopArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, allStopInfo);
        stops.setAdapter(stopArrayAdapter);
        stops.setThreshold(0);
        userSearchStopButton=(Button) findViewById(R.id.userSearchStopButton);


        userSearchStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = edtStopSearch.getText().toString().trim().toLowerCase();
                StopSearchPresenter presenter=new StopSearchPresenter();
                if(!isInteger(s)) {
                    Stop stop = presenter.searchStopByName(s);
                    if (stop != null) {
                        searchStop(s);
                    } else if (stop == null) {
                        Toast.makeText(getApplicationContext(), "Η στάση δεν βρέθηκε", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Stop stop = presenter.searchStopById(s);
                    if (stop != null) {
                      searchStop(s);
                    } else if (stop == null) {
                        Toast.makeText(getApplicationContext(), "Η στάση δεν βρέθηκε", Toast.LENGTH_SHORT).show();
                    }

                }
            }


        });



        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        try {
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                longitude=location.getLongitude();
                                latitude=location.getLatitude();
                            }
                        }
                    });
        }
        catch (SecurityException e){}


        Button locButton = (Button) findViewById(R.id.locButton);
        locButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), userStopSearchByLocResult.class);
                myIntent.putExtra(LOCATION_LONGITUDE,longitude);
                myIntent.putExtra(LOCATION_LATITUDE, latitude);
                startActivityForResult(myIntent, 0);
            }
        });

        //https://developer.android.com/training/location/retrieve-current#java


    }





    public boolean isInteger(String input){
        try{
            Integer.parseInt(input.trim());
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public void searchStop(String stopInfo){
        Intent intent = new Intent(this, userStopSearchResult.class);
        intent.putExtra(STOP_INFO_EXTRA,stopInfo);
        startActivityForResult(intent, REQUEST_CODE_STOP_SEARCH);
    }
}
