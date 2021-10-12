package com.example.khadijeh_project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.khadijeh_project.api.ApiClint;
import com.example.khadijeh_project.api.ApiService;
import com.example.khadijeh_project.model.Data;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link searchFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class searchFragment extends Fragment {

    ApiService apiService;
    TextView tv_fragSearch ;
    Context context;
    RecyclerView recyclerView;
    LinearLayout noConnectLayout;
    ProgressBar progressBarhome;



//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public searchFragment() {
//        // Required empty public constructor

//    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment searchFragment.
//     */
    // TODO: Rename and change types and number of parameters
//    public static searchFragment newInstance(String param1, String param2) {
//        searchFragment fragment = new searchFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView = view.findViewById(R.id.api_list);
        noConnectLayout = view.findViewById(R.id.layout_no_connection_api);
        checkConnection();


        // Inflate the layout for this fragment

//        try {
//            JSONObject jsonObject = new JSONObject(JsonValue.post);

//        progressBarhome = view.findViewById(R.id.progress2);
//        Sprite doubleBounce = new Wave();
//        progressBarhome.setIndeterminateDrawable(doubleBounce);
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        apiService = ApiClint.getClient().create(ApiService.class);
//        Call<Data> getApiPost = apiService.getPost();
//        getApiPost.enqueue(new Callback<Data>() {
//            @Override
//            public void onResponse(Call<Data> call, Response<Data> response) {
////                Log.e("qqq", "onResponse: "+response.body().ok);
//                recyclerView.setAdapter(new AdapterSearch(getContext(),response.body().result));
//            }
//
//            @Override
//            public void onFailure(Call<Data> call, Throwable t) {
//                Log.e("qqqq", "onFailure: ",t );
//
//            }
//        });
        return view;
    }

    @SuppressLint("ResourceAsColor")
    public void checkConnection(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if(wifi.isConnected()){
            apiService = ApiClint.getClient().create(ApiService.class);
            Call<Data> getApiPost = apiService.getPost();
            getApiPost.enqueue(new Callback<Data>() {
                @Override
                public void onResponse(Call<Data> call, Response<Data> response) {
//                Log.e("qqq", "onResponse: "+response.body().ok);
                    recyclerView.setAdapter(new AdapterSearch(getContext(),response.body().result));

                }
                @Override
                public void onFailure(Call<Data> call, Throwable t) {
                    Log.e("qqqq", "onFailure: ",t );

                }
            });

        }else if(mobile.isConnected()){
            apiService = ApiClint.getClient().create(ApiService.class);
            Call<Data> getApiPost = apiService.getPost();
            getApiPost.enqueue(new Callback<Data>() {
                @Override
                public void onResponse(Call<Data> call, Response<Data> response) {
//                Log.e("qqq", "onResponse: "+response.body().ok);
                    recyclerView.setAdapter(new AdapterSearch(getContext(),response.body().result));

                }
                @Override
                public void onFailure(Call<Data> call, Throwable t) {
                    Log.e("qqqq", "onFailure: ",t );

                }
            });

        }else {
            recyclerView.setVisibility(View.INVISIBLE);
            noConnectLayout.setVisibility(View.VISIBLE);
        }
    }

}