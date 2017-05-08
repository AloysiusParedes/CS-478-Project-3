package com.example.com.cs478proj3a3;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.List;

public class TeamSiteFragment extends Fragment {
    public int currentIndex = -1;
    private WebView webView = null;

    public List<String> sites;

    public void setSites(List<String> s){
        sites = s;
    }

    public int getShownIndex() {
        return currentIndex;
    }

    public void showSiteAtIndex(int ind){
        if (ind < 0 || ind >= sites.size())
            return;
        currentIndex = ind;

        webView.loadUrl(sites.get(currentIndex));

        //http://stackoverflow.com/questions/21170911/webview-inside-fragment-android-opening-outside-of-the-app-in-a-browser
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.team_site, container, false);
        return view;
    }//end onCreateView(...)

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Retain this Fragment across Activity reconfigurations
        setRetainInstance(true);

    }

    // Set up some information about the mQuoteView TextView
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        webView = (WebView) getActivity().findViewById(R.id.teamWebPage);
        showSiteAtIndex(currentIndex);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
}
