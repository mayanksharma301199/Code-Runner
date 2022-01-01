package com.example.codecompiler;

import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JS_Fragment extends Fragment implements ITabbedFragment {

    private IAdapter mListener;

    private File filepath = Environment.getExternalStorageDirectory();

    private EditText myE;

    public JS_Fragment(IAdapter listener) {

        if (listener != null) {

            mListener = listener;

        }

    }

    public static JS_Fragment newInstance(IAdapter listener) {
        return new JS_Fragment(listener);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_javascript, container, false);

        String defaultJSText = "";

        try {

            String fileUrl = filepath.getAbsolutePath() + "/TempCode/index.js";

            BufferedReader readIndexFile = new BufferedReader(new FileReader(fileUrl));

            String tempIndexValue = "";

            while ((tempIndexValue = readIndexFile.readLine()) != null) {

                defaultJSText = defaultJSText.concat(tempIndexValue + "\n");

            }
        }
        catch(Exception e){

            Toast.makeText(getContext(), "Unable to find open the file", Toast.LENGTH_SHORT).show();

        }


        myE = view.findViewById(R.id.javascriptCode);

        myE.setText(defaultJSText);

        return view;
    }

    @Override
    public void onReceive(Object o) {

        if(((String) o).equals("Insert Content")) {

            File filepath = Environment.getExternalStorageDirectory();

            String fileUrl = filepath.getAbsolutePath() + "/TempCode/index.js";

            FileWriter cssObj = null;

            try {

                cssObj = new FileWriter(fileUrl);

                cssObj.write(myE.getText().toString());

                cssObj.close();

            }
            catch (IOException e) {

                Toast.makeText(getContext(), "JS Data not saved", Toast.LENGTH_SHORT).show();

            }

            TabLayout tabs = (TabLayout) ((MainActivity) requireActivity()).findViewById(R.id.tabs);

            tabs.getTabAt(3).select();

        }

    }
}
