package com.example.nezar_lulu.second_assignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class second extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        final EditText ed1 = (EditText) findViewById(R.id.ed1);
        final EditText ed2 = (EditText) findViewById(R.id.ed2);
        Button finish = (Button) findViewById(R.id.finish);

        final Intent data = this.getIntent();
        final String name = data.getExtras().getString("name");
        final String number = data.getExtras().getString("number");
        final int id = data.getExtras().getInt("id");
        ed1.setText(name);
        ed2.setText(number);


        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val1 = ed1.getText().toString();
                String val2 = ed2.getText().toString();
                Intent i = new Intent();
                i.putExtra("name", val1);
                i.putExtra("number", val2);
                i.putExtra("id", id);
                setResult(second.RESULT_OK, i);
                finish();
            }
        });


    }

}
