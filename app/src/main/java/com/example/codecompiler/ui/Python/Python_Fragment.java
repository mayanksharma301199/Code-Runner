package com.example.codecompiler.ui.Python;

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
import com.example.codecompiler.ui.main.SectionsPagerAdapter;
import com.example.codecompiler.ui.main.SectionsPagerAdapterPython;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

public class Python_Fragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_python_tabbed, container, false);

        SectionsPagerAdapterPython sectionsPagerAdapter = new SectionsPagerAdapterPython(getContext(), getChildFragmentManager());

        ViewPager viewPager = root.findViewById(R.id.view_pager);

        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = root.findViewById(R.id.tabs);

        tabs.setupWithViewPager(viewPager);

        viewPager.setOffscreenPageLimit(2);

        return root;

    }
}