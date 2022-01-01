package com.example.codecompiler;

import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class Python_Fragment extends Fragment implements ITabbedFragment {

    private IAdapter mListener;

    private File filepath = Environment.getExternalStorageDirectory();

    private EditText pythonCode;

    public Python_Fragment(IAdapter listener) {

        if (listener != null) {

            mListener = listener;

        }

    }

    public static Python_Fragment newInstance(IAdapter listener) {

        return new Python_Fragment(listener);

    }

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_python, container, false);

        String defaultPythonText = "";

        try {

            String fileUrl = filepath.getAbsolutePath() + "/TempCode/index.py";

            BufferedReader readIndexFile = new BufferedReader(new FileReader(fileUrl));

            String tempIndexValue = "";

            while ((tempIndexValue = readIndexFile.readLine()) != null) {

                defaultPythonText =  defaultPythonText.concat(tempIndexValue + "\n");

            }
        }
        catch(Exception e){

            Toast.makeText(getContext(), "Unable to find open the file", Toast.LENGTH_SHORT).show();

        }

        pythonCode = view.findViewById(R.id.pythonCode);

        pythonCode.setText(defaultPythonText);

        FloatingActionButton btnSend = view.findViewById(R.id.fab);

        btnSend.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                TabLayout tabs = (TabLayout)((MainActivity) requireActivity()).findViewById(R.id.tabs);

                if(!Python.isStarted()){

                    Python.start(new AndroidPlatform(getContext()));

                }

                Python py = Python.getInstance();

                final PyObject pyObjOne = py.getModule("Script");

                PyObject obj = pyObjOne.callAttr("main", pythonCode.getText().toString());

                if (mListener != null)

                    Objects.requireNonNull(tabs.getTabAt(1)).select();

                    mListener.onSend(obj.toString(), Python_Fragment.this);

            }

        });

        return view;

    }

    @Override

    public void onReceive(Object o) {

    }

}
