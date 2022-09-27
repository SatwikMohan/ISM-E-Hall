package com.gigawattstechnology.e_lib;

import static android.content.Context.DOWNLOAD_SERVICE;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.RecyclerViewHolder> {
    ArrayList<FileDet> details;
    Activity context;
    ArrayList<FileDet> filterdetails;
    public RecycleAdapter(Files context, ArrayList<FileDet> details){
        this.details=details;
        this.context=context;
        this.filterdetails=new ArrayList<>(details);
    }
    @Override
    public int getItemViewType(final int position) {
        return R.layout.pdfcontainer;
    }
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(viewType, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        final FileDet fileDet=details.get(position);
        holder.filename.setText(fileDet.getSubjectname());
        holder.url.setText(fileDet.getPdfurl());
        holder.openpdf.setText("open: "+fileDet.getSubjectname());
    }

    @Override
    public int getItemCount() {
        return details.size();
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView filename;
        TextView url;
        Context c;
        ImageView download;
        Button openpdf;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            c=itemView.getContext();
            openpdf=itemView.findViewById(R.id.openpdf);
            filename=itemView.findViewById(R.id.filename);
            url=itemView.findViewById(R.id.fileurl);
            download=itemView.findViewById(R.id.download);
            download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(c, "File is downloading", Toast.LENGTH_SHORT).show();
                    String getUrl=url.getText().toString();
                    DownloadManager.Request request=new DownloadManager.Request(Uri.parse(getUrl));
                    String title= URLUtil.guessFileName(getUrl,null,null);
                    request.setTitle(title);
                    request.setDescription("File is downloading");
                    String cookie= CookieManager.getInstance().getCookie(getUrl);
                    request.addRequestHeader("cookie",cookie);
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,title);
                    DownloadManager downloadManager=(DownloadManager) c.getSystemService(DOWNLOAD_SERVICE);
                    downloadManager.enqueue(request);
                    Toast.makeText(c, "Download Complete Check File Manager", Toast.LENGTH_SHORT).show();
                }
            });
            //itemView.setOnClickListener(this);
            openpdf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(c,pdfViewactivity.class);
                    intent.putExtra("url",url.getText().toString());
                    c.startActivity(intent);
                }
            });

        }
    }
}
