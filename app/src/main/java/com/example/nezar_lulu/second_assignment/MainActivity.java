package com.example.nezar_lulu.second_assignment;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActionMode mActionmode;
    private int myPosition;
    DB db = new DB(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton add = (ImageButton) findViewById(R.id.add);


        db.open();
        final ListView lst = (ListView) findViewById(R.id.lv);
        String[] from = {MySQLiteDatabase.NAME_COLUMN, MySQLiteDatabase.NUMBER_COLUMN};
        int[] to = {R.id.name, R.id.number};
        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.list, db.getnew(), from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        lst.setAdapter(adapter);
        db.close();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListView lst = (ListView) findViewById(R.id.lv);
                EditText et1 = (EditText) findViewById(R.id.et1);
                EditText et2 = (EditText) findViewById(R.id.et2);
                String val1 = et1.getText().toString();
                String val2 = et2.getText().toString();
                list con = new list();
                db.open();
                long id = db.addNew(val1, val2);
                String[] from = {MySQLiteDatabase.NAME_COLUMN, MySQLiteDatabase.NUMBER_COLUMN};
                int[] to = {R.id.name, R.id.number};
                SimpleCursorAdapter adapter = new SimpleCursorAdapter(MainActivity.this, R.layout.list, db.getnew(), from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
                con.setName(val1);
                con.setNumber(val2);
                lst.setAdapter(adapter);
                db.close();
            }

        });


        final ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                getMenuInflater().inflate(R.menu.main_menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

                final int pos = (int) adapter.getItemId(myPosition);
                db.open();
                switch (item.getItemId()) {

                    case R.id.edit:
                        String name = db.getname(pos);
                        String number = db.getnumber(pos);
                        Intent i = new Intent(MainActivity.this, second.class);
                        i.putExtra("name", name);
                        i.putExtra("number", number);
                        i.putExtra("id", pos);
                        startActivityForResult(i, 1);

                        mode.finish();
                        break;
                    case R.id.delete:

                        db.remove(pos);
                        Toast.makeText(MainActivity.this, "Record Deleted , please refresh the app", Toast.LENGTH_SHORT).show();
                        mode.finish();
                        break;

                }
                db.close();
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                mActionmode = null;
            }
        };
        lst.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                mActionmode = startActionMode(mActionModeCallback);
                myPosition = position;
                return true;

            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == 1) && (resultCode == RESULT_OK)) {
            db.open();
            String name = data.getExtras().getString("name");
            String number = data.getExtras().getString("number");
            int id = data.getExtras().getInt("id");
            db.updateData(id, name, number);
            Toast.makeText(MainActivity.this, "Record Updated , please refresh the app", Toast.LENGTH_SHORT).show();

            db.close();
        }

    }
}

