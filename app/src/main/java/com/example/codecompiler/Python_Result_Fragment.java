package com.example.codecompiler;

import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Python_Result_Fragment extends Fragment implements ITabbedFragment{

    private IAdapter mListener;

    private TextView pythonCode;

    private File filepath = Environment.getExternalStorageDirectory();

    public Python_Result_Fragment(IAdapter listener) {

        if (listener != null) {

            mListener = listener;

        }

    }

    public static Python_Result_Fragment newInstance(IAdapter listener) {

        return new Python_Result_Fragment(listener);

    }

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_python_output, container, false);

        pythonCode = (view.findViewById(R.id.pythonOutput));

        return view;

    }

    @Override

    public void onReceive(Object o) {

            pythonCode.setText((String) o);

    }

}
