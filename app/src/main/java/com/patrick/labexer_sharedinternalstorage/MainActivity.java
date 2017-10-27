package com.patrick.labexer_sharedinternalstorage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static java.lang.System.lineSeparator;

public class MainActivity extends AppCompatActivity {

    EditText et_Username, et_Password;
    Button save_Prefs, save_Storage, btn_Next;
    FileOutputStream fos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_Username = (EditText) findViewById(R.id.etUsername);
        et_Password = (EditText) findViewById(R.id.etPassword);
        save_Prefs = (Button) findViewById(R.id.savePrefs);
        save_Storage = (Button) findViewById(R.id.saveStorage);
        btn_Next = (Button) findViewById(R.id.nextBtn);
    }

    public void callNextAct (View view){
        Intent intent = new Intent(this, secondActivity.class);
        startActivity(intent);
    }

    public void savePreferences (View view){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", et_Username.getText().toString());
        editor.putString("password", et_Password.getText().toString());
        editor.commit();
        Toast.makeText(this, "Preferences Saved!", Toast.LENGTH_SHORT).show();

    }
    public void saveStorage (View view) {
        String username = et_Username.getText().toString();
        String newline = ("\r\n");
        String password = et_Password.getText().toString();
        try {
            fos = openFileOutput("output.txt", Context.MODE_PRIVATE);
            fos.write(username.getBytes());
            fos.write(newline.getBytes());
            fos.write(password.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Storage saved!", Toast.LENGTH_SHORT).show();
    }
}
