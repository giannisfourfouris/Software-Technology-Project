package gr.aueb.softeng.project1806.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import gr.aueb.softeng.project1806.R;
import gr.aueb.softeng.project1806.memorydao.BusTimetableDAOMemory;
import gr.aueb.softeng.project1806.memorydao.LineDAOMemory;
import gr.aueb.softeng.project1806.memorydao.StopDAOMemory;
import gr.aueb.softeng.project1806.model.BusTimetable;
import gr.aueb.softeng.project1806.model.Line;
import gr.aueb.softeng.project1806.model.Stop;
import gr.aueb.softeng.project1806.presenter.LineSearchPresenter;

public class userLineSearchResult extends AppCompatActivity {
    LineSearchPresenter presenter;
    Line line;
    TextView nameLine;
    ListView listBtt, listStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_line_search_result);
        presenter = new LineSearchPresenter();

        Intent intent = getIntent();
        String info = intent.getStringExtra(userLineSearch.LINE_INFO_EXTRA).trim().toLowerCase();
        line = presenter.searchLine(info);
        nameLine = (TextView) findViewById(R.id.stopNameText);
        listBtt = (ListView) findViewById(R.id.bttLineList);
        listStop = (ListView) findViewById(R.id.stopLinelist);



        nameLine.setText(line.getLineCode() + " " + line.getLineName());
        Set<Stop> stopList = line.getStops();


        ArrayList<String> StringStop = new ArrayList<String>();
        for (Stop stop : stopList) {

            StringStop.add("•" + stop.getStopName().toString());
        }

        ArrayAdapter<String> stops = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, StringStop);
        listStop.setAdapter(stops);

        Set<BusTimetable> bttList = line.getTimetables();

        ArrayList<String> StringBtt = new ArrayList<String>();
        for (BusTimetable btt : bttList) {
            StringBtt.add(btt.getStartTimeFromStart().toString());
        }

        ArrayAdapter<String> btts = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, StringBtt);
        listBtt.setAdapter(btts);


    }

}
