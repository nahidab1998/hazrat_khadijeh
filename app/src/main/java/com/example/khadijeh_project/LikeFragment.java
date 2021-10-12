package com.example.khadijeh_project;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LikeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LikeFragment extends Fragment {

    SwipeRefreshLayout swipeRefreshLayout;
    ProgressBar progressBarlike;
    public static WebView webViewlike;
    LinearLayout noConnectLayout;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LikeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LikeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LikeFragment newInstance(String param1, String param2) {
        LikeFragment fragment = new LikeFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_like, container, false);
//        progressBarlike = (ProgressBar) v.findViewById(R.id.progress1);
//        progressBarlike.setMax(100);

        progressBarlike = v.findViewById(R.id.progress1);
        Sprite doubleBounce = new Wave();
        progressBarlike.setIndeterminateDrawable(doubleBounce);


        webViewlike = v.findViewById(R.id.heart);
        webViewlike.getSettings().setJavaScriptEnabled(true);
        webViewlike.setWebViewClient(new WebViewClient());
        noConnectLayout = v.findViewById(R.id.layout_no_connection);
        checkConnection();
//        webView.loadUrl("https://khadije.com/delneveshte");

//        webView.setWebChromeClient(new WebChromeClient() {
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                super.onProgressChanged(view, newProgress);
//                progressBarlike.setProgress(newProgress);
//            }
//
//            @Override
//            public void onReceivedTitle(WebView view, String title) {
//                super.onReceivedTitle(view, title);
//            }
//
//            @Override
//            public void onReceivedIcon(WebView view, Bitmap icon) {
//                super.onReceivedIcon(view, icon);
//            }
//        });


        swipeRefreshLayout = v.findViewById(R.id.swipecontainer1);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webViewlike.getSettings().setJavaScriptEnabled(true);
                webViewlike.setWebViewClient(new WebViewClient());
//                webView.loadUrl("https://khadije.com/fa");
                checkConnection();
                swipeRefreshLayout.setRefreshing(false);
//                webView.setOnKeyListener(new View.OnKeyListener() {
//                    @Override
//                    public boolean onKey(View v, int keyCode, KeyEvent event) {
//                        Log.d("qqqqq", "onKey: "+event+"-"+keyCode);
//                        if (event.getAction()== KeyEvent.ACTION_DOWN){
//                            if (keyCode== KeyEvent.KEYCODE_BACK){
//                                if (webView!=null){
//                                    if (webView.canGoBack()){
//                                        webView.goBack();
//                                    }else {
//                                        getActivity().onBackPressed();
//                                    }
//                                }
//                            }
//                        }
//                        return true;
//                    }
//                });
            }
        });

        return v;
    }


    private void checkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (wifi.isConnected()) {
            webViewlike.loadUrl("https://khadije.com/delneveshte");
            webViewlike.setVisibility(View.VISIBLE);
            noConnectLayout.setVisibility(View.INVISIBLE);

        } else if (mobile.isConnected()) {
            webViewlike.loadUrl("https://khadije.com/delneveshte");
            webViewlike.setVisibility(View.VISIBLE);
            noConnectLayout.setVisibility(View.INVISIBLE);

        } else {
            progressBarlike.setVisibility(View.INVISIBLE);
            webViewlike.setVisibility(View.INVISIBLE);
            noConnectLayout.setVisibility(View.VISIBLE);
        }
    }

}