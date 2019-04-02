package gr.aueb.softeng.project1806.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import gr.aueb.softeng.project1806.R;
import gr.aueb.softeng.project1806.dao.Initializer;
import gr.aueb.softeng.project1806.memorydao.MemoryInitializer;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Initializer dataHelper = new MemoryInitializer();
        dataHelper.prepareData();

        Button goToAdminOptions = (Button) findViewById(R.id.adminButton);
        goToAdminOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), adminOptions.class);
                startActivityForResult(myIntent, 0);
            }
        });

        Button goToUserOptions = (Button) findViewById(R.id.userButton);
        goToUserOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), userOptions.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }
}
