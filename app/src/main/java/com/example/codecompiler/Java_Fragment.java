package com.example.codecompiler;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class Java_Fragment extends Fragment implements ITabbedFragment {

    private IAdapter mListener;

    public Java_Fragment(IAdapter listener) {

        if (listener != null) {

            mListener = listener;

        }

    }

    public static Java_Fragment newInstance(IAdapter listener) {

        return new Java_Fragment(listener);

    }

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_java, container, false);

        return view;

    }

    @Override

    public void onReceive(Object o) {

    }

}
