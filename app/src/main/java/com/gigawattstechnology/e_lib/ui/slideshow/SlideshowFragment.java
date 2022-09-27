package com.gigawattstechnology.e_lib.ui.slideshow;

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
import com.gigawattstechnology.e_lib.databinding.FragmentSlideshowBinding;

import java.util.ArrayList;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;
    ArrayList<Modal> subject=new ArrayList<Modal>();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        subject.add(new Modal("Data Structure And Algorithm", R.drawable.imp));
        subject.add(new Modal("Signal System And Network", R.drawable.imp));
        subject.add(new Modal("Probability And Statistics", R.drawable.imp));
        subject.add(new Modal("Analog And Digital Electronics", R.drawable.imp));
        subject.add(new Modal("Computer Organisation", R.drawable.imp));
        subject.add(new Modal("Electromagnetic Theory And Applications", R.drawable.imp));
        subject.add(new Modal("Discrete Mathematics", R.drawable.imp));
        subject.add(new Modal("Utilization Of Electrical Energy", R.drawable.imp));
        subject.add(new Modal("Engineering Material", R.drawable.imp));
        subject.add(new Modal("Solid Mechanics", R.drawable.imp));
        subject.add(new Modal("Kinematics of Machines", R.drawable.imp));
        subject.add(new Modal("Fluid Mechanics", R.drawable.imp));
        subject.add(new Modal("Applied Thermodynamics", R.drawable.imp));
        Adapter adapter=new Adapter(root.getContext(),subject);
        ListView sub=(ListView) binding.secondYearList;
        sub.setAdapter(adapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}