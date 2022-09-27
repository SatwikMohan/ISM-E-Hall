package com.gigawattstechnology.e_lib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class workspace extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workspace);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();
        ArrayList<Modal> subject=new ArrayList<Modal>();
        //subject.add(new Modal("Doubt Smash",R.drawable.doubt));
        subject.add(new Modal("Important Documents",R.drawable.imp));
        subject.add(new Modal("Mathematics-I",R.drawable.math1));
        subject.add(new Modal("Mathematics-II",R.drawable.math2));
        subject.add(new Modal("Physics",R.drawable.physics));
        subject.add(new Modal("Chemistry",R.drawable.chem));
        subject.add(new Modal("Mechanics",R.drawable.mech));
        subject.add(new Modal("Numerical Methods",R.drawable.numericalmethods));
        subject.add(new Modal("English",R.drawable.english));
        subject.add(new Modal("Manufacturing process",R.drawable.mnpt));
        subject.add(new Modal("C programming",R.drawable.computer));
        subject.add(new Modal("Electrical (Modular)",R.drawable.electrical));
        subject.add(new Modal("Electronics (Modular)",R.drawable.electronics));
        subject.add(new Modal("Engineering Graphics",R.drawable.graphics));
        subject.add(new Modal("Economics and Finance",R.drawable.eef));
        subject.add(new Modal("Earth Science",R.drawable.earth));
        subject.add(new Modal("Environmental",R.drawable.evs));
        Adapter adapter=new Adapter(this,subject);
        ListView sub=(ListView) findViewById(R.id.list);
        sub.setAdapter(adapter);

    }
}