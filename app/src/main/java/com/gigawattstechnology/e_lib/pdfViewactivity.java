package com.gigawattstechnology.e_lib;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.URLUtil;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class pdfViewactivity extends AppCompatActivity {
ProgressBar progressBar;
    String url;
    PDFView pdfView;
    FloatingActionButton download;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewactivity);
        getSupportActionBar().hide();
        pdfView=findViewById(R.id.pdfView);
        progressBar=findViewById(R.id.progressBar3);
        url=getIntent().getStringExtra("url");
        download=findViewById(R.id.down);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(pdfViewactivity.this, "File is downloading", Toast.LENGTH_SHORT).show();
                String getUrl=url;
                DownloadManager.Request request=new DownloadManager.Request(Uri.parse(getUrl));
                String title= URLUtil.guessFileName(getUrl,null,null);
                request.setTitle(title);
                request.setDescription("File is downloading");
                String cookie= CookieManager.getInstance().getCookie(getUrl);
                request.addRequestHeader("cookie",cookie);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,title);
                DownloadManager downloadManager=(DownloadManager) pdfViewactivity.this.getSystemService(DOWNLOAD_SERVICE);
                downloadManager.enqueue(request);
                Toast.makeText(pdfViewactivity.this, "Download Complete Check File Manager", Toast.LENGTH_SHORT).show();
            }
        });
        new RetrivePdfStream().execute(url);
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
            }
        },3522);

    }
    class RetrivePdfStream extends AsyncTask<String,Void, InputStream> {

        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream=null;
            try{
                URL url=new URL(strings[0]);
                HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
                if(urlConnection.getResponseCode()==200)
                {
                    inputStream=new BufferedInputStream(urlConnection.getInputStream());
                }
            } catch (IOException e) {
                return null;
            }
            return inputStream;
        }
        @Override
        protected void onPostExecute(InputStream inputStream){
            pdfView.fromStream(inputStream).load();

        }
    }
}