package com.example.codecompiler.ui.Java;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.codecompiler.R;
import com.example.codecompiler.ui.main.SectionsPagerAdapterJava;
import com.example.codecompiler.ui.main.SectionsPagerAdapterPython;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

public class Java_Fragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_java_tabbed, container, false);

        SectionsPagerAdapterJava sectionsPagerAdapter = new SectionsPagerAdapterJava(getContext(), getChildFragmentManager());

        ViewPager viewPager = root.findViewById(R.id.view_pager);

        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = root.findViewById(R.id.tabs);

        tabs.setupWithViewPager(viewPager);

        viewPager.setOffscreenPageLimit(2);

        return root;
    }
}