package com.example.android.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import java.util.HashSet;

public class EditActivity extends AppCompatActivity {

    EditText editText;
     int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        editText=(EditText)findViewById(R.id.editText);
        Intent intent=getIntent();
        position=intent.getIntExtra("index",-1);
        if( position!=-1){
            Log.i("Position: ",Integer.toString(position));
            editText.setText(MainActivity.notes.get(position));
        }
        else{
            MainActivity.notes.add("");
            position=MainActivity.notes.size()-1;

        }

        //text change and set arraylist when type or text change
       editText.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

           }

           @Override
           public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               //set change text in array list
                   MainActivity.notes.set(position,String.valueOf(charSequence));
                   MainActivity.arrayAdapter.notifyDataSetChanged();
               //save notes in sharedPreference
               SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("com.example.android.notes", Context.MODE_PRIVATE);
               HashSet<String> set=new HashSet<>(MainActivity.notes);
               sharedPreferences.edit().putStringSet("notes", set).apply();
           }

           @Override
           public void afterTextChanged(Editable editable) {

           }
       });
    }
}
