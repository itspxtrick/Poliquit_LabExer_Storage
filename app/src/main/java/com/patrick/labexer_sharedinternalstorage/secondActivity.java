package com.patrick.labexer_sharedinternalstorage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class secondActivity extends AppCompatActivity {

    Button load_Prefs, load_Storage, btn_Clear, btn_Back;
    TextView tv_Display;
    FileInputStream fis;
    BufferedReader br;
    String usr, pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        load_Prefs = (Button) findViewById(R.id.loadPrefs);
        load_Storage = (Button) findViewById(R.id.loadStorage);
        btn_Clear = (Button) findViewById(R.id.clearBtn);
        btn_Back = (Button) findViewById(R.id.backBtn);
        tv_Display = (TextView) findViewById(R.id.tvDisplay);

    }

    public void callMainAct (View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void clearDisplay (View view) {
        tv_Display.setText("");
    }

    public void loadPreferences(View view) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());;
        String user = preferences.getString("username","");
        String pwd = preferences.getString("password","");
        tv_Display.setText("The password of " + user + " is " + pwd);
    }

    public void loadStorage (View view) throws IOException {
        String newline = "";
        try{
            fis = openFileInput("output.txt");
            br = new BufferedReader(new InputStreamReader(fis));
            if ((newline = br.readLine()) != null)
                usr = newline;
            if ((newline = br.readLine()) != null)
                pwd = newline;
            fis.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        tv_Display.setText("The password of " + usr + " is " + pwd);
    }
}
