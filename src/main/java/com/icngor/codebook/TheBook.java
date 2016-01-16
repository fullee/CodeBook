package com.icngor.codebook;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TheBook extends Activity {

    private ListView listView;
    @SuppressLint("InlinedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_the_book);
        listView = (ListView) findViewById(R.id.listView);

        String[] GoBible = getResources().getStringArray(R.array.directorytitle);
        final String[] GoBibleUrl = getResources().getStringArray(R.array.directoryurl);

        String[] GoStdLib = getResources().getStringArray(R.array.gostdlib);
        String[] GoStdLibUrl = getResources().getStringArray(R.array.gostdlibUrl);

        String[] thewaytogo = getResources().getStringArray(R.array.thewaytogo);
        String[] thewaytogoUrls = getResources().getStringArray(R.array.thewaytogoUrls);

        final String[][][] theBook = {
                {GoBible,GoBibleUrl},
                {GoStdLib,GoStdLibUrl},
                {thewaytogo,thewaytogoUrls}
        };
        Intent intent = getIntent();
        final int bookNum = intent.getIntExtra("bookNum", -1);
        final String bookUrl = intent.getStringExtra("bookUrl");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(TheBook.this,android.R.layout.simple_list_item_1,theBook[bookNum][0]);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentRBook = new Intent(TheBook.this, ReadBookBrowser.class);
                intentRBook.putExtra("pagesUrl", bookUrl + theBook[bookNum][1][position]);
                startActivity(intentRBook);
            }
        });

    }
}
