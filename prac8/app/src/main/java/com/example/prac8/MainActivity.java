package com.example.prac8;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    RadioButton radioButtonPhoto1, radioButtonPhoto2;
    Button buttonDownload;
    ImageView imageView;
    ProgressDialog progressDialog;
    Bitmap bitmap = null;
    String photoURL1 = "https://docs.google.com/uc?id=0B5k4qPq9Waw_dHM3SHFaUmdhN1E";
    String photoURL2 = "https://docs.google.com/uc?id=0B5k4qPq9Waw_YWdhQW5YV1hmdWc";
    String selectedPhoto = "";


    RadioButton radioButtonFile1, radioButtonFile2;
    TextView textViewContent;
    String fileURL1 = "https://docs.google.com/uc?id=0B5k4qPq9Waw_SVFEMkRHWUVqVW8";
    String fileURL2 = "https://docs.google.com/uc?id=0B5k4qPq9Waw_ZnVzTlFrdVN2aUE";
    String selectedFile = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        radioButtonPhoto1 = findViewById(R.id.radioButtonPhoto1);
        radioButtonPhoto2 = findViewById(R.id.radioButtonPhoto2);
        buttonDownload = findViewById(R.id.buttonDownload);

        radioButtonFile1 = findViewById(R.id.radioButtonFile1);
        radioButtonFile2 = findViewById(R.id.radioButtonFIle2);
        textViewContent = findViewById(R.id.textViewContent);

        final Handler messageHandler = new Handler()
        {
            public void handleMessage(Message msg)
            {
                super.handleMessage(msg);

                imageView.setImageBitmap(bitmap);
                progressDialog.dismiss();;
            }
        };


        buttonDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radioButtonPhoto1.isChecked())
                {
                    selectedPhoto = photoURL1;
                }
                else if(radioButtonPhoto2.isChecked())
                {
                    selectedPhoto = photoURL2;
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Select a photo to download", Toast.LENGTH_SHORT).show();
                }

                if (selectedPhoto.length() > 0)
                {
                    progressDialog = ProgressDialog.show(MainActivity.this, "", "Downloading photo...");

                    new Thread()
                    {
                        public void run()
                        {
                            // This part will download our bitmap in the background.
                            bitmap = downloadBitmap(selectedPhoto);

//                             After downloading the bitmap we will have to display it
//                             in the ImageView in the main UI thread. In order for us to
//                             do so, we will have to create a Handler that will allow
//                             us to send a message to the main UI thread.
                            //
                            messageHandler.sendEmptyMessage(0);
                        }
                    }.start();
                }
            }
        });


        Button buttonDownloadFile = findViewById(R.id.buttonDownloadFile);
        buttonDownloadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radioButtonFile1.isChecked()){
                    selectedFile = fileURL1;
                }
                else if(radioButtonFile2.isChecked()) {
                    selectedFile = fileURL2;
                }
                else {
                    Toast.makeText(MainActivity.this, "Select a file to download", Toast.LENGTH_SHORT).show();
                }

                if (selectedFile.length() > 0)
                {
                    // We are going to execute an asynchronous task here.
                    DownloadTask task = new DownloadTask();
                    task.execute();
                }
            }
        });

    }
    public Bitmap downloadBitmap(String strURL)
    {
        try
        {
            URL url = new URL(strURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.connect();

            int responseCode = conn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK)
            {
                Toast.makeText(MainActivity.this, "Error in downloading image", Toast.LENGTH_SHORT).show();
                return null;
            }

            InputStream inputStream = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);

            return bitmap;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(this, "Error in reading response", Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    private class DownloadTask extends AsyncTask<Void, Void, String>
    {

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(MainActivity.this, "", "Downloading file...");
        }

        @Override
        protected void onPostExecute(String s) {
            progressDialog.dismiss();
            textViewContent.setText(s);
        }

        @Override
        protected String doInBackground(Void... voids) {
            String response = "";

            try
            {
                URL url = new URL(selectedFile);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true);
                conn.connect();

                int responseCode = conn.getResponseCode();
                if (responseCode != HttpURLConnection.HTTP_OK)
                {
                    return "Resource not available. Please check the URL.";
                }

                InputStream inputStream = conn.getInputStream();
                BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));
                String s = "";

                while ((s = buffer.readLine()) != null)
                {
                    response += s + "\n";
                }
            }
            catch (Exception e)
            {
                return "Error in downloading file!";
            }
            return response;
        }
    }

}

