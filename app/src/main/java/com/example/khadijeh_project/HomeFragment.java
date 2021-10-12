package com.example.khadijeh_project;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.core.app.NavUtils;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.Wave;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    SwipeRefreshLayout swipeRefreshLayout;
    public static WebView webViewhome;
     ProgressBar progressBarhome;
    LinearLayout noConnectLayout;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home,container,false);
        webViewhome = view.findViewById(R.id.homee);
//        progressBar = view.findViewById(R.id.progress);
        progressBarhome = view.findViewById(R.id.progress);
        Sprite doubleBounce = new Wave();
        progressBarhome.setIndeterminateDrawable(doubleBounce);

        noConnectLayout = view.findViewById(R.id.layout_no_connection);
        load_webview();

        swipeRefreshLayout = view.findViewById(R.id.swipecontainer);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                webView.reload();
                load_webview();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        return view;
    }


    public void load_webview(){
        webViewhome.getSettings().setJavaScriptEnabled(true);
        webViewhome.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBarhome.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressBarhome.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);
                super.onPageFinished(view, url);
            }
        });

        checkConnection();
        webViewhome.goBack();
    }


    public void checkConnection(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if(wifi.isConnected()){
            webViewhome.loadUrl("https://khadije.com/fa");
            webViewhome.setVisibility(View.VISIBLE);
            noConnectLayout.setVisibility(View.INVISIBLE);

        }else if(mobile.isConnected()){
            webViewhome.loadUrl("https://khadije.com/fa");
            webViewhome.setVisibility(View.VISIBLE);
            noConnectLayout.setVisibility(View.INVISIBLE);

        }else {
            progressBarhome.setVisibility(View.INVISIBLE);
            webViewhome.setVisibility(View.INVISIBLE);
            noConnectLayout.setVisibility(View.VISIBLE);
        }
    }

}