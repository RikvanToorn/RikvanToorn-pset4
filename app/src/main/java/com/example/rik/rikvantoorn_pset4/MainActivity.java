package com.example.rik.rikvantoorn_pset4;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private DBManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;
    private Button addToDo;
    private EditText taskEditText;
    private long toDelete;

    final String[] from = new String[] { DBHelper._ID, DBHelper.TASK};

    final int[] to = new int[] { R.id.id, R.id.task };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManager = new DBManager(this);
        Cursor cursor = dbManager.read();

        listView = (ListView) findViewById(R.id.Tasks);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.view_task, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        taskEditText = (EditText) findViewById(R.id.editText);
        addToDo = (Button) findViewById(R.id.button);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                dbManager.delete(id);
                Intent refresh = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(refresh);
                return true;
            }
        });
    }

    public void addTask(View view) {
        final String task = taskEditText.getText().toString();
        dbManager.insert(task);
        Intent refresh = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(refresh);
    }
}
