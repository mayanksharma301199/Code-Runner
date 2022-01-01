package com.example.codecompiler;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSS_Fragment extends Fragment implements ITabbedFragment {

    private IAdapter mListener;

    public EditText myE;

    private File filepath = Environment.getExternalStorageDirectory();

    public CSS_Fragment(IAdapter listener) {

        if (listener != null) {

            mListener = listener;

        }

    }

    public static CSS_Fragment newInstance(IAdapter listener) {
        return new CSS_Fragment(listener);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_css, container, false);

        String defaultCSSText = "";

        try {

            String fileUrl = filepath.getAbsolutePath() + "/TempCode/index.css";

            BufferedReader readIndexFile = new BufferedReader(new FileReader(fileUrl));

            String tempIndexValue = "";

            while ((tempIndexValue = readIndexFile.readLine()) != null) {

                defaultCSSText = defaultCSSText.concat(tempIndexValue + "\n");

            }
        }
        catch(Exception e){

            Toast.makeText(getContext(), "Unable to find open the file", Toast.LENGTH_SHORT).show();

        }

        myE = (EditText) (view.findViewById(R.id.cssCode));

        myE.setText(defaultCSSText);

        return view;

    }

    @Override
    public void onReceive(Object o) {

        if(((String) o).equals("Insert Content")) {

           File filepath = Environment.getExternalStorageDirectory();

           String fileUrl = filepath.getAbsolutePath() + "/TempCode/index.css";

            FileWriter cssObj = null;

            try {

                cssObj = new FileWriter(fileUrl);

                cssObj.write(myE.getText().toString());

                cssObj.close();

            } catch (IOException e) {

                Toast.makeText(getContext(), "CSS Data not saved", Toast.LENGTH_SHORT).show();

            }

            TabLayout tabs = (TabLayout) ((MainActivity) requireActivity()).findViewById(R.id.tabs);

            tabs.getTabAt(2).select();

        }

    }
}
