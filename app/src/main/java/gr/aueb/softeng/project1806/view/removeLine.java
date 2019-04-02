package gr.aueb.softeng.project1806.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import gr.aueb.softeng.project1806.R;
import gr.aueb.softeng.project1806.memorydao.LineDAOMemory;
import gr.aueb.softeng.project1806.model.Line;
import gr.aueb.softeng.project1806.model.Stop;
import gr.aueb.softeng.project1806.presenter.LineSearchPresenter;

public class removeLine extends AppCompatActivity {

    ListView listView;
    LineDAOMemory lineDAOMemory = new LineDAOMemory();
    LineSearchPresenter presenter = new LineSearchPresenter();
    Button btnRemove;
    EditText edtLineToRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_line);

        btnRemove = (Button) findViewById(R.id.RemoveLineButton);
        edtLineToRemove = findViewById(R.id.InputLineToRemoveText);



        List<Line> lineList = lineDAOMemory.findAll();
        ArrayList<String> StringLine =new ArrayList<>();
        for(Line line:lineList){
            StringLine.add("Κωδικός γραμμής: "+line.getLineCode().toString()+" \nΌνομα γραμμής: "+ line.getLineName().toString());

        }
      //  Toast.makeText(getApplicationContext(),StringLine[0].toString(), Toast.LENGTH_SHORT).show();
       ArrayAdapter<String> lines=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, StringLine);


        listView = (ListView) findViewById(R.id.lineListView);
        
        listView.setAdapter(lines);

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Line line = presenter.searchLine(edtLineToRemove.getText().toString().trim().toLowerCase());
                if (line!= null){
                    lineDAOMemory.remove(line);
                    Set<Stop> stops=line.getStops();
                    for(Stop stop:stops){
                        stop.removeLine(line);
                    }
                    Toast.makeText(getApplicationContext(),"Διαγραφή Επιτυχής", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(),"Η γραμμή δεν βρέθηκε", Toast.LENGTH_SHORT).show();
                }



            }
        });



    }
}
