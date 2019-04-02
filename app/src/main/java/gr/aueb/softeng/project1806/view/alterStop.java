package gr.aueb.softeng.project1806.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import gr.aueb.softeng.project1806.R;
import gr.aueb.softeng.project1806.memorydao.StopDAOMemory;
import gr.aueb.softeng.project1806.model.Stop;
import gr.aueb.softeng.project1806.presenter.StopSearchPresenter;

public class alterStop extends AppCompatActivity {
    Button searchStopButton;
    EditText inputStopInfo;
    StopSearchPresenter presenter;
    public static final String STOP_INFO_EXTRA = "info";
    public static final int REQUEST_CODE_STOP_SEARCH = 1;
    AutoCompleteTextView stops;
    StopDAOMemory stopDAOMemory;
    private static String[] allStopInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter_stop);

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
        stops = (AutoCompleteTextView) findViewById(R.id.inputInfoText2);

        ArrayAdapter<String> stopArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, allStopInfo);
        stops.setAdapter(stopArrayAdapter);
        stops.setThreshold(0);

        presenter = new StopSearchPresenter();
        searchStopButton =(Button) findViewById(R.id.searchStopButton);

        inputStopInfo = findViewById(R.id.inputInfoText2);
        searchStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = inputStopInfo.getText().toString().trim().toLowerCase();
                if(!isInteger(s)) {
                    Stop stop = presenter.searchStopByName(s);
                    if (stop != null) {
                        searchStop(String.valueOf(stop.getStopID()));


                    } else if (stop == null) {
                        Toast.makeText(getApplicationContext(), "Η στάση δεν βρέθηκε", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Stop stop = presenter.searchStopById(s);
                    if (stop != null) {
                        searchStop(String.valueOf(stop.getStopID()));

                    } else if (stop == null) {
                        Toast.makeText(getApplicationContext(), "Η στάση δεν βρέθηκε", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
    public void searchStop(String stopInfo){
        Intent intent = new Intent(this, stopInfo.class);
        intent.putExtra(STOP_INFO_EXTRA,stopInfo);
        startActivityForResult(intent, 0);
    }
    public boolean isInteger(String input){
        try{
            Integer.parseInt(input.trim());
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
