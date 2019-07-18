package com.rajendraneshwaran.xmlpullparser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listView1);

        List<employee> employees = null;
        try {
            XMLPullParserHandler parser = new XMLPullParserHandler();
            InputStream is = getAssets().open("employees.xml");
            employees = parser.parse(is);

            ArrayAdapter<employee> adapter = new ArrayAdapter<employee>
                    (this, android.R.layout.simple_list_item_1, employees);
            listView.setAdapter(adapter);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    }
