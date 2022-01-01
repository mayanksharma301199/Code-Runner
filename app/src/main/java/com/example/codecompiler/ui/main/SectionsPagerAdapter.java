package com.example.codecompiler.ui.main;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.codecompiler.CSS_Fragment;
import com.example.codecompiler.HCJ_Result_Fragment;
import com.example.codecompiler.HTML_Fragment;

import com.example.codecompiler.IAdapter;
import com.example.codecompiler.ITabbedFragment;
import com.example.codecompiler.JS_Fragment;
import com.example.codecompiler.R;

import java.util.ArrayList;
import java.util.List;

public class SectionsPagerAdapter extends FragmentPagerAdapter implements IAdapter {

    private List<ITabbedFragment> fragments;

    private static final String[] TAB_TITLES = {
            "HTML",
            "CSS",
            "JS",
            "OUTPUT"
    };
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
        fragments = new ArrayList<>();
        fragments.add(HTML_Fragment.newInstance(this));
        fragments.add(CSS_Fragment.newInstance(this));
        fragments.add(JS_Fragment.newInstance(this));
        fragments.add(HCJ_Result_Fragment.newInstance(this));
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
        return 4;
    }

    @Override
    public void onSend(Object o, Fragment fragment) {
        for (ITabbedFragment f: fragments){
            if (!f.equals(fragment)) f.onReceive(o);
        }
    }
}