package com.example.external_storage_ex;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText ed_one;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed_one = findViewById(R.id.ed_username);
    }

    public void internal_cache(View view) {

        String u_name = ed_one.getText().toString();
        File file = getCacheDir();
        File my_file = new File(file, "Test_internal.txt");
        write_Data(my_file, u_name);


    }

    public void external_cache(View view) {

        String username = ed_one.getText().toString();
        File file = getExternalCacheDir();
        File myfile = new File(file, "Test_external.txt");
        write_Data(myfile, username);
    }

    public void external_private(View view) {

        String username = ed_one.getText().toString();
        File file = getExternalFilesDir("test_cache");
        File myfile = new File(file, "Test_external_private.txt");
        write_Data(myfile, username);
    }

    public void external_public(View view) {

        String username = ed_one.getText().toString();
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File myfile = new File(file, "Test_external_public.txt");
        write_Data(myfile, username);
    }

    public void next(View view) {

        startActivity(new Intent(MainActivity.this, Second_Act.class));
    }

    public void msg(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    public void write_Data(File myfile, String username) {

        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(myfile);
            try {
                fileOutputStream.write(username.getBytes());
                msg(username + "File Added" + myfile.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
