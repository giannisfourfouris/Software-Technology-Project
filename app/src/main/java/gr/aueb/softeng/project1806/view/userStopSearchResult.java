package gr.aueb.softeng.project1806.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import gr.aueb.softeng.project1806.R;
import gr.aueb.softeng.project1806.memorydao.BusTimetableDAOMemory;
import gr.aueb.softeng.project1806.memorydao.LineDAOMemory;
import gr.aueb.softeng.project1806.memorydao.StopDAOMemory;
import gr.aueb.softeng.project1806.model.Line;
import gr.aueb.softeng.project1806.model.Stop;
import gr.aueb.softeng.project1806.presenter.StopSearchPresenter;

public class userStopSearchResult extends AppCompatActivity {
    TextView stopAllInfo;

    ListView listLine;
    Stop stop;
    StopSearchPresenter presenter;
    LineDAOMemory lineDAOMemory;
    StopDAOMemory stopDAOMemory;
    BusTimetableDAOMemory busTimetableDAOMemory;
    boolean first10Mins=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_stop_search_result);

        presenter = new StopSearchPresenter();
        lineDAOMemory = new LineDAOMemory();


        Intent intent = getIntent();
        String info = intent.getStringExtra(userStopSearch.STOP_INFO_EXTRA).trim().toLowerCase();
        if (!isInteger(info)) {
            stop = presenter.searchStopByName(info);

        } else {
            stop = presenter.searchStopById(info);

        }


        stopAllInfo = (TextView) findViewById(R.id.stopInfoText);

        listLine = (ListView) findViewById(R.id.listViewStopLine);

        stopAllInfo.setText(String.valueOf("Κωδικός: " + stop.getStopID() + "\nΌνομα: " + stop.getStopName()));


        Set<Line> LineList = stop.getLines();
        Calendar now = Calendar.getInstance();


        now.add(Calendar.MINUTE, 20);

        if ((now.get(Calendar.MINUTE))<10){
            first10Mins = true;
        }

        ArrayList<String> StringLine = new ArrayList<>();
        for (Line line : LineList) {
            if (first10Mins==true) {
                StringLine.add(String.valueOf(line.getLineCode() + " " + line.getLineName() + " "
                        + now.get(Calendar.HOUR_OF_DAY) + ":0"
                        + now.get(Calendar.MINUTE)));
            }
            else {
                StringLine.add(String.valueOf(line.getLineCode() + " " + line.getLineName() + " "
                        + now.get(Calendar.HOUR_OF_DAY) + ":"
                        + now.get(Calendar.MINUTE)));
            }
        }

        ArrayAdapter<String> lines = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, StringLine);
        listLine.setAdapter(lines);


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
