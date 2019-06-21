package com.example.external_storage_ex;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Second_Act extends AppCompatActivity {

    EditText ed_username_second_act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_act);

        ed_username_second_act = findViewById(R.id.ed_username_2);
    }

    public void internal_cache(View view) {

        File file = getCacheDir();
        File myfile = new File(file, "Test_internal.txt");
        String data = Display_Data(myfile);
        if (data != null) {
            ed_username_second_act.setText(data);
        } else {
            ed_username_second_act.setText("No Data Found");
        }
    }

    public void external_cache(View view) {

        File file = getExternalCacheDir();
        File myfile = new File(file, "Test_external.txt");
        String data = Display_Data(myfile);
        if (data != null) {
            ed_username_second_act.setText(data);
        } else {
            ed_username_second_act.setText("No Data Found");
        }
    }

    public void external_private(View view) {

        File file = getExternalFilesDir("test_cache");
        File myfile = new File(file, "Test_external_private.txt");
        String data = Display_Data(myfile);
        if (data != null) {
            ed_username_second_act.setText(data);
        } else {
            ed_username_second_act.setText("No Data Found");
        }
    }

    public void external_public(View view) {

        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File myfile = new File(file, "Test_external_public.txt");
        String data = Display_Data(myfile);
        if (data != null) {
            ed_username_second_act.setText(data);
        } else {
            ed_username_second_act.setText("No Data Found");
        }
    }

    public void back(View view) {

        startActivity(new Intent(Second_Act.this, MainActivity.class));
    }

    public void msg(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public String Display_Data(File myfile) {

        FileInputStream fileinputStream = null;
        try {

            fileinputStream = new FileInputStream(myfile);


            StringBuffer stringBuffer = new StringBuffer();
            int read =-1;
            while ((read = fileinputStream.read()) != -1) {
                stringBuffer.append((char) read);
            }
            return stringBuffer.toString();

        } catch (java.io.IOException e) {
            e.printStackTrace();
        } finally {
            if (fileinputStream != null) {

                try {
                    fileinputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }
}
