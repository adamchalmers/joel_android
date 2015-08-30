package com.example.adam.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class EditorActivity extends ActionBarActivity {
    String editItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

         editItem = getIntent().getStringExtra("item");

        ( (EditText)findViewById(R.id.editText)).setText(editItem);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
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

    public void onClickChange(View v){
        Intent data = new Intent();
       String newText =  ((EditText)findViewById(R.id.editText)).getText().toString();
        data.putExtra("item", newText);
        data.putExtra("position", getIntent().getIntExtra("position", -1));


        setResult(RESULT_OK, data);
        finish();
    }

}
