package com.gigawattstechnology.e_lib.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.gigawattstechnology.e_lib.Adapter;
import com.gigawattstechnology.e_lib.Modal;
import com.gigawattstechnology.e_lib.R;
import com.gigawattstechnology.e_lib.databinding.FragmentGalleryBinding;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    ArrayList<Modal> subject=new ArrayList<Modal>();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        subject.add(new Modal("Important Documents", R.drawable.imp));
        subject.add(new Modal("Mathematics-I",R.drawable.imp));
        subject.add(new Modal("Mathematics-II",R.drawable.imp));
        subject.add(new Modal("Physics",R.drawable.imp));
        subject.add(new Modal("Chemistry",R.drawable.imp));
        subject.add(new Modal("Mechanics",R.drawable.imp));
        subject.add(new Modal("Numerical Methods",R.drawable.imp));
        subject.add(new Modal("English",R.drawable.imp));
        subject.add(new Modal("Manufacturing process",R.drawable.imp));
        subject.add(new Modal("C programming",R.drawable.imp));
        subject.add(new Modal("Electrical (Modular)",R.drawable.imp));
        subject.add(new Modal("Electronics (Modular)",R.drawable.imp));
        subject.add(new Modal("Engineering Graphics",R.drawable.imp));
        subject.add(new Modal("Economics and Finance",R.drawable.imp));
        subject.add(new Modal("Earth Science",R.drawable.imp));
        subject.add(new Modal("Environmental",R.drawable.imp));
        Adapter adapter=new Adapter(root.getContext(),subject);
        ListView sub=(ListView) binding.firstYearList;
        sub.setAdapter(adapter);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}