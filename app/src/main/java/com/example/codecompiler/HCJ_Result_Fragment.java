package com.example.codecompiler;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.codecompiler.IAdapter;
import com.example.codecompiler.ITabbedFragment;
import com.example.codecompiler.R;

import java.io.File;

public class HCJ_Result_Fragment extends Fragment implements ITabbedFragment {

    private IAdapter mListener;

    private EditText txt;

    private Button btnSend;

    public WebView CurrentWebView;

    File filepath = Environment.getExternalStorageDirectory();

    String fileUrl = filepath.getAbsolutePath() + "/TempCode/index.html";

    public HCJ_Result_Fragment(IAdapter listener) {

        if (listener != null) {

            mListener = listener;

        }

    }

    public static HCJ_Result_Fragment newInstance(IAdapter listener) {

        return new HCJ_Result_Fragment(listener);

    }

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @SuppressLint("SetJavaScriptEnabled")

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hcj_output, container, false);

        CurrentWebView = (view.findViewById(R.id.webPage));

        CurrentWebView.getSettings().setJavaScriptEnabled(true);

        CurrentWebView.setWebChromeClient(new WebChromeClient());

        CurrentWebView.getSettings().setAllowFileAccess(true);

        CurrentWebView.setInitialScale(100);

        return view;

    }

    @Override

    public void onReceive(Object o) {

        if(((String) o).equals("Insert Content")) {

            CurrentWebView.loadUrl(fileUrl);

        }

    }

}
