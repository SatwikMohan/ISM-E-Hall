package com.gigawattstechnology.e_lib;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LinkAdapter extends RecyclerView.Adapter<LinkAdapter.RecyclerViewHolder>{

    Context context;
    ArrayList<LinkModal> list;

    public LinkAdapter(Context context,ArrayList<LinkModal> list) {
        this.context=context;
        this.list = list;
    }
    @Override
    public int getItemViewType(final int position) {
        return R.layout.links_container;
    }
    @NonNull
    @Override
    public LinkAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(viewType, parent, false);
        return new LinkAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LinkAdapter.RecyclerViewHolder holder, int position) {
        LinkModal linkModal=list.get(position);
        holder.title.setText(linkModal.getTitle());
        holder.url.setText(linkModal.getUrl());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        Button openLink;
        TextView title,url;
        ImageView imageView;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.videoImage);
            openLink=itemView.findViewById(R.id.OpenLinkButton);
            title=itemView.findViewById(R.id.VideoTitle);
            url=itemView.findViewById(R.id.VideoLink);
            openLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url.getText().toString()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
