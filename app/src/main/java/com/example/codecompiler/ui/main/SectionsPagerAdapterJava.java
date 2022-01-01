package com.example.codecompiler.ui.main;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.codecompiler.IAdapter;
import com.example.codecompiler.ITabbedFragment;
import com.example.codecompiler.Java_Fragment;
import com.example.codecompiler.Java_Result_Fragment;
import com.example.codecompiler.Python_Fragment;
import com.example.codecompiler.Python_Result_Fragment;

import java.util.ArrayList;
import java.util.List;

public class SectionsPagerAdapterJava extends FragmentPagerAdapter implements IAdapter {

    private List<ITabbedFragment> fragments;

    private static final String[] TAB_TITLES = {
            "Java",
            "OUTPUT"
    };
    private final Context mContext;

    public SectionsPagerAdapterJava(Context context, FragmentManager fm) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        mContext = context;

        fragments = new ArrayList<>();

        fragments.add(Java_Fragment.newInstance(this));

        fragments.add(Java_Result_Fragment.newInstance(this));

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return (Fragment) fragments.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_TITLES[position];
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }

    @Override
    public void onSend(Object o, Fragment fragment) {
        for (ITabbedFragment f: fragments){
            if (!f.equals(fragment)) f.onReceive(o);
        }
    }
}