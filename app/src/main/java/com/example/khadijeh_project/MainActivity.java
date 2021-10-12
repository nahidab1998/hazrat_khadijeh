package com.example.khadijeh_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navigationView;
    ImageView imageView;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        navigationView=findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
        navigationView.setSelectedItemId(R.id.nav_home);



        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;

                switch (item.getItemId()){
                    case R.id.nav_home:
                        fragment = new HomeFragment();
                        //Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_like:
                        fragment = new LikeFragment();
                        //Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_search:
                        fragment = new searchFragment();
//                        Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_SHORT).show();
                        //Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
                return true;
            }
        });


    }


    @Override
    public void onBackPressed() {
        if (HomeFragment.webViewhome.canGoBack()) {
            HomeFragment.webViewhome.goBack();

        }else if(LikeFragment.webViewlike.canGoBack()){
            LikeFragment.webViewlike.goBack();
        }
        else {
            finish();
        }
    }

//    private boolean isconnected(){
//
//        ConnectivityManager connectivityManager =(ConnectivityManager) getApplicationContext().getSystemService(context.CONNECTIVITY_SERVICE);
//        return connectivityManager.getActiveNetworkInfo() !=null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting() ;
//    }
}