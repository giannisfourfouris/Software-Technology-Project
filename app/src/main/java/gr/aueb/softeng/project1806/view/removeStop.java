package gr.aueb.softeng.project1806.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import gr.aueb.softeng.project1806.R;
import gr.aueb.softeng.project1806.memorydao.LineDAOMemory;
import gr.aueb.softeng.project1806.memorydao.StopDAOMemory;
import gr.aueb.softeng.project1806.model.Line;
import gr.aueb.softeng.project1806.model.Stop;
import gr.aueb.softeng.project1806.presenter.LineSearchPresenter;
import gr.aueb.softeng.project1806.presenter.StopSearchPresenter;

public class removeStop extends AppCompatActivity {

    ListView listView;
    StopDAOMemory stopDAOMemory = new StopDAOMemory();
    StopSearchPresenter presenter = new StopSearchPresenter();
    Button btnRemove;
    EditText edtStopToRemove;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_stop);
        btnRemove = (Button) findViewById(R.id.RemoveStopButton);
        edtStopToRemove = findViewById(R.id.InputStopToRemoveText);



        List<Stop> stopList = stopDAOMemory.findAll();
        ArrayList<String> StringStop =new ArrayList<>();
        for(Stop stop:stopList){
            StringStop.add("Κωδικός στάσης: "+stop.getStopID()+" \nΌνομα στάσης: "+ stop.getStopName().toString()
                    +" \nΤοποθεσίας στάσης: "+ stop.getLocation().getLatitude()+" "+stop.getLocation().getLongitude());

        }
        //  Toast.makeText(getApplicationContext(),StringLine[0].toString(), Toast.LENGTH_SHORT).show();
        ArrayAdapter<String> stops=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, StringStop);


        listView = (ListView) findViewById(R.id.stopListView);

        listView.setAdapter(stops);

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=edtStopToRemove.getText().toString().trim().toLowerCase();
                if(!isInteger(s)) {
                    Stop stop = presenter.searchStopByName(s);
                    if (stop != null) {
                        stopDAOMemory.remove(stop);
                        Toast.makeText(getApplicationContext(), "Διαγραφή Επιτυχής", Toast.LENGTH_SHORT).show();
                        finish();
                    } else if (stop == null) {
                        Toast.makeText(getApplicationContext(), "Η στάση δεν βρέθηκε", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Stop stop = presenter.searchStopById(s);
                    if (stop != null) {
                        stopDAOMemory.remove(stop);
                        Set<Line> lines=stop.getLines();
                        for(Line line:lines){
                           line.removeStop(stop);
                        }
                        Toast.makeText(getApplicationContext(), "Διαγραφή Επιτυχής", Toast.LENGTH_SHORT).show();
                        finish();
                    } else if (stop == null) {
                        Toast.makeText(getApplicationContext(), "Η στάση δεν βρέθηκε", Toast.LENGTH_SHORT).show();
                    }


                }




            }
        });



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
