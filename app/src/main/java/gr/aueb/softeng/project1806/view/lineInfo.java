package gr.aueb.softeng.project1806.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import gr.aueb.softeng.project1806.R;
import gr.aueb.softeng.project1806.model.Line;
import gr.aueb.softeng.project1806.presenter.LineSearchPresenter;

public class lineInfo extends AppCompatActivity {


    Line line;
    Button btnSave;
    EditText edtCodeText, edtNameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_info);

        Intent intent = getIntent();

        String info = intent.getStringExtra(alterLines.LINE_INFO_EXTRA);

        LineSearchPresenter presenter = new LineSearchPresenter();
        line = presenter.searchLine(info);

        btnSave = (Button) findViewById(R.id.SaveButton);
        edtCodeText = findViewById(R.id.InputLineCodeText);
        edtNameText = findViewById(R.id.InputLineNameText);

        edtCodeText.setText(line.getLineCode());
        edtNameText.setText(line.getLineName());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                line.setLineCode(edtCodeText.getText().toString().trim());
                line.setLineName(edtNameText.getText().toString().trim());
                Toast.makeText(getApplicationContext(), "Επιτυχής Αποθήκευση", Toast.LENGTH_SHORT).show();
                finish();

            }
        });


    }
}
