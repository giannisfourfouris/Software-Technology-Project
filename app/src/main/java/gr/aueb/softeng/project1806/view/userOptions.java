package gr.aueb.softeng.project1806.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import gr.aueb.softeng.project1806.R;

public class userOptions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_options);

        Button goToLineOptions = (Button) findViewById(R.id.userLineOptionButton);
        goToLineOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), userLineSearch.class);
                startActivityForResult(myIntent, 0);
            }
        });

        Button goToStopOptions = (Button) findViewById(R.id.userStopOptionButton);
        goToStopOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), userStopSearch.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }
}