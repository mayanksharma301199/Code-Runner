package com.example.codecompiler;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Objects;

import android.os.Environment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;

public class HTML_Fragment extends Fragment implements ITabbedFragment {

    private IAdapter mListener;

    private EditText txt;

    public HTML_Fragment(IAdapter listener) {

        if (listener != null) {

            mListener = listener;

        }

    }

    public static HTML_Fragment newInstance(IAdapter listener) {

        return new HTML_Fragment(listener);

    }

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_html, container, false);

        File filepath = Environment.getExternalStorageDirectory();

        File dir = new File(filepath.getAbsolutePath() + "/TempCode/");

        dir.mkdir();

        try {

            File tempHTML = new File(filepath.getAbsolutePath() + "/TempCode/index.html");
            
            tempHTML.createNewFile();

            File tempCSS = new File(filepath.getAbsolutePath() + "/TempCode/index.css");

            tempCSS.createNewFile();

            File tempJS = new File(filepath.getAbsolutePath() + "/TempCode/index.js");

            tempJS.createNewFile();

            File tempPython = new File(filepath.getAbsolutePath() + "/TempCode/index.py");

            tempPython.createNewFile();

        }
        catch (IOException e) {

            e.printStackTrace();

        }

        String defaultHtmlText = "";

        try {

            String fileUrl = filepath.getAbsolutePath() + "/TempCode/index.html";

            BufferedReader readIndexFile = new BufferedReader(new FileReader(fileUrl));

            String tempIndexValue = "";

            while ((tempIndexValue = readIndexFile.readLine()) != null) {

               defaultHtmlText =  defaultHtmlText.concat(tempIndexValue + "\n");

            }
        }
        catch(Exception e){

            Toast.makeText(getContext(), "Unable to find open the file", Toast.LENGTH_SHORT).show();

        }
                String plainText= "<!DOCTYPE html>\n" +
                          "<html lang=" + "\"en\"" + ">\n" +
                          "<head>\n" +
                                "<meta charset=" + "\"UTF-8\"" + ">\n" +
                                "<meta http-equiv=" + "\"X-UA-Compatible\"" + "content=" + "\"IE=edge\"" + ">\n" +
                                "<meta name=" + "\"viewport\"" + "content=" + "\"width=device-width, initial-scale=1.0\"" +">\n" +
                                "<title>Document</title>\n" +
                                "<link rel=" + "\"stylesheet\"" + "href=" + "\"index.css\"" + ">\n" +
                          "</head>\n" +
                            "<body>\n\n" +
                            "<!-- Write Your Code Here -->" +
                            "\n\n</body>\n" +
                            "<script src=" + "\"index.js\"" + "></script>\n" +
                            "</html>";

        txt = view.findViewById(R.id.htmlCode);

        txt.setText(defaultHtmlText);

        FloatingActionButton btnSend = view.findViewById(R.id.fab);

        btnSend.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                TabLayout tabs = (TabLayout)((MainActivity) requireActivity()).findViewById(R.id.tabs);

                if (mListener != null)

                   Objects.requireNonNull(tabs.getTabAt(1)).select();

                   String fileUrl = filepath.getAbsolutePath() + "/TempCode/index.html";

                   FileWriter htmlObj = null;

                try {

                        htmlObj = new FileWriter(fileUrl);

                        htmlObj.write(txt.getText().toString());

                        htmlObj.close();

                    } catch (IOException e) {

                    Toast.makeText(getContext(), "HTML Data not saved", Toast.LENGTH_SHORT).show();

                    }

                    mListener.onSend("Insert Content", HTML_Fragment.this);

            }

        });

        return view;

    }

    @Override

    public void onReceive(Object o) {

    }
}