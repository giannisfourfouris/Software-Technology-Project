package gr.aueb.softeng.project1806.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import gr.aueb.softeng.project1806.R;

public class adminStopOptions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_stop_options);


        Button goToRemoveStopOptions = (Button) findViewById(R.id.removeStopButton);
        goToRemoveStopOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), removeStop.class);
                startActivityForResult(myIntent, 0);
            }
        });

        Button goToAddStopOptions = (Button) findViewById(R.id.addStopButton);
        goToAddStopOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), addStop.class);
                startActivityForResult(myIntent, 0);
            }
        });

        Button goToAlterStopOptions = (Button) findViewById(R.id.alterStopButton);
        goToAlterStopOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), alterStop.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }
}
