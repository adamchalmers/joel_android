package com.example.adam.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    ListView list;
    ArrayList<String> arrList;
    ArrayAdapter<String> adapter;
    public final int EDIT_ITEM_REQUEST_CODE = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.TODOList);
        arrList = new ArrayList<String>();
        arrList.add("Learn Android");
        arrList.add("Get money");
        arrList.add("Get bitchs");
        arrList.add("Get more money");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrList);
        list.setAdapter(adapter);

        setupLongClicks();
        setupClicks();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void hwBtnClick(View v) {
        adapter.add("Item");
    }

    public void setupLongClicks() {
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long rowId) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Delete?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                adapter.remove(adapter.getItem(position));
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.create().show();
                return true;
            }
        });
    }
    public void setupClicks(){
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                if (intent != null) {
                    // put 'extras' into the bundle for access in the edit activity
                    intent.putExtra("item", adapter.getItem(position));
                    intent.putExtra("position",  position);
                    //bring up the new activity
                    startActivityForResult(intent, EDIT_ITEM_REQUEST_CODE);
                    adapter.notifyDataSetChanged();
                } else {
                    Log.e("joel", "no intent");
                }
            }

        });
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == EDIT_ITEM_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String newName = data.getStringExtra("item");

                arrList.set(data.getIntExtra("position", -1),newName);
                adapter.notifyDataSetChanged();
            }
        }
    }

}
