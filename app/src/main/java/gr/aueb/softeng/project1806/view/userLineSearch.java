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
import gr.aueb.softeng.project1806.memorydao.LineDAOMemory;
import gr.aueb.softeng.project1806.model.Line;
import gr.aueb.softeng.project1806.presenter.LineSearchPresenter;

public class userLineSearch extends AppCompatActivity {

    AutoCompleteTextView lines;
    Button lineSearch;
    EditText edtLineSearch;
    public static final String LINE_INFO_EXTRA = "info";
    public static final int REQUEST_CODE_LINE_SEARCH = 1;
    private static String[] allLineInfo;
    LineDAOMemory lineDAOMemory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_line_search);
        lineSearch=(Button)findViewById(R.id.userSearchLineButton);
        edtLineSearch=(EditText)findViewById(R.id.userInputInfoText);

        lineDAOMemory = new LineDAOMemory();
        List<Line> lineList= lineDAOMemory.findAll();
        allLineInfo = new String[lineList.size() * 2];
        int i=0;
        for (Line line : lineList){
            allLineInfo[i] = line.getLineCode();
            i++;
            allLineInfo[i] = line.getLineName();
            i++;
        }
        lines = (AutoCompleteTextView) findViewById(R.id.userInputInfoText);

        ArrayAdapter<String> lineArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, allLineInfo);
        lines.setAdapter(lineArrayAdapter);
        lines.setThreshold(0);


        lineSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lineInfo = edtLineSearch.getText().toString().trim().toLowerCase();
                LineSearchPresenter presenter=new LineSearchPresenter();
                Line line=presenter.searchLine(lineInfo);
                if(line!=null) {
                    searchLine(lineInfo);
                }else{
                    Toast.makeText(getApplicationContext(),"Η γραμμή δεν βρέθηκε", Toast.LENGTH_SHORT).show();
                }
            }


        });
    }
    public void searchLine(String lineInfo){
        Intent intent = new Intent(this, userLineSearchResult.class);
        intent.putExtra(LINE_INFO_EXTRA,lineInfo);
        startActivityForResult(intent, REQUEST_CODE_LINE_SEARCH);

    }
}
