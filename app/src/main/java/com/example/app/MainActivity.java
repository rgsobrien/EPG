package com.example.app;

import java.util.List;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;

//  GOAL: Build a native android Mobile Employee Directory

//  ** The result is similar to the sample with Flex and Flash Builder
//  see http://www.adobe.com/devnet/flex/articles/employee-directory-android-flex.html

//  PURPOSE: Learning how to build an Android App.

//  Step 2: Load data into the ListActivity from an XML file via XmlParser
//          1) employee_list.xml  (in res/xml)
//          2) Employee.java
//          3) EmployeeXmlParser.java

public class MainActivity extends ListActivity {

    public List<Channel> channels = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         * This will work, but best practice is to place in a non-ui thread like below.
         *
         * EmployeeXmlParser parser = new EmployeeXmlParser();
         * employees = parser.parse(this);
         *
         * ArrayAdapter<Employee> adapter = new ArrayAdapter<Employee>(this, android.R.layout.simple_list_item_1, employees);
         * setListAdapter(adapter);
         */

        // Parse xml data in a non-ui thread
        new LoadChannelsTask().execute();
    }

    private class LoadChannelsTask extends AsyncTask<String, Void, List<Channel>> {

        @Override
        protected List<Channel> doInBackground(String... args) {

            // CALL XMLPULLPARSER & RETURN A LIST
            ChannelXmlParser parser = new ChannelXmlParser();
            channels = parser.parse(getBaseContext());

            return channels;
        }

        @Override
        protected void onPostExecute(List<Channel> channels) {

            ArrayAdapter<Channel> adapter = new ArrayAdapter<Channel>(getBaseContext(), android.R.layout.simple_list_item_1, channels);

            setListAdapter(adapter);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}