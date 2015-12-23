package com.icngor.codebook;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import DataBeans.BooksInfo;
import Request.UpdateRequest;
import adapter.MainAdapter;
import tools.NetTools;

public class MainActivity extends OldActivity {

    private GridView gridView;
    private List<BooksInfo> list = new ArrayList<>();
    private ImageButton imageButton;
    private String[] BookName ={"Go圣经","Go标准库"};
    private String[] BookUrl ={
            "http://golang-china.github.io/gopl-zh/",
            "https://github.com/polaris1119/The-Golang-Standard-Library-by-Example/blob/master/"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //升级检查
        if (NetTools.isNetworkAvailable(MainActivity.this)) {
            UpdateRequest request = new UpdateRequest(han);
            request.setHANDLERMSG(UPDATE_APPVIRSION);
            request.start();
        }

        gridView = (GridView) findViewById(R.id.gridView);
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, About.class);
                startActivity(intent);
            }
        });
        for (int i = 0; i < BookName.length; i++) {
            BooksInfo booksInfo = new BooksInfo();
            booksInfo.setPic(R.drawable.go);
            booksInfo.setTitle(BookName[i]);
            booksInfo.setBookurl(BookUrl[i]);
            list.add(booksInfo);
        }

        BaseAdapter adapter = new MainAdapter(MainActivity.this,list);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, TheBook.class);
                intent.putExtra("bookNum", position);
                intent.putExtra("bookUrl", list.get(position).getBookurl());
                startActivity(intent);
            }
        });
        final String[] authorinfo = getResources().getStringArray(R.array.polaris1119);
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("著者/译者信息");
                String authorinfos = "";
                for (int i = 0; i < authorinfo.length; i++) {
                    authorinfos+=authorinfo[i]+"\n";
                }
                dialog.setMessage(authorinfos);
                dialog.show();
                return true;
            }
        });
    }

}
