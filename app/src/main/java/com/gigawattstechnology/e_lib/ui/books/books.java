package com.gigawattstechnology.e_lib.ui.books;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.gigawattstechnology.e_lib.R;
import com.gigawattstechnology.e_lib.databinding.FragmentBooksBinding;
import com.gigawattstechnology.e_lib.databinding.FragmentGalleryBinding;


public class books extends Fragment {
    private FragmentBooksBinding biinding;
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        biinding=FragmentBooksBinding.inflate(inflater,container,false);
        recyclerView=biinding.booksRecyclerView;
        View root=biinding.getRoot();

       /* WebView webView = biinding.booksWeb;
        webView.loadUrl("https://books.google.co.in/");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());*/

        return root;
    }
}