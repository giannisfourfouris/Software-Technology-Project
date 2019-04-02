package gr.aueb.softeng.project1806.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import gr.aueb.softeng.project1806.R;

public class adminLineOptions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_line_options);

        Button goToAlterLineOptions = (Button) findViewById(R.id.alterLineButton);
        goToAlterLineOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), alterLines.class);
                startActivityForResult(myIntent, 0);
            }
        });

        Button goToRemoveLineOptions = (Button) findViewById(R.id.removeLineButton);
        goToRemoveLineOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), removeLine.class);
                startActivityForResult(myIntent, 0);
            }
        });

        Button goToAddLineOptions = (Button) findViewById(R.id.addLineButton);
        goToAddLineOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), addLine.class);
                startActivityForResult(myIntent, 0);
            }
        });

    }


}
