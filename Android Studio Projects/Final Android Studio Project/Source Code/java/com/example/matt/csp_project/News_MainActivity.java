package com.example.matt.csp_project;

import android.app.Activity;
import android.os.Bundle;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;

public class News_MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ConnectivityManager connManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mMobile = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (mWifi.isConnected() == false && mMobile.isConnected() == false) {
            showErrorView();
        }
        else {
            System.out.println("Connected");
            setContentView(R.layout.news_activity_main);

            findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
            FileDownloader news = new FileDownloader("http://branko-cirovic.appspot.com/etcapp/news/news.xml", News_MainActivity.this);
            news.setOnResultsListener(new AsyncResponse() {
                @Override
                public void processFinish(String output) {
                Intent newsScreen = new Intent(getApplicationContext(), NewsActivity.class);
                newsScreen.putExtra("xmlData", output);
                findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                startActivity(newsScreen);
                }
            });
            news.execute();
        }
    }

    private void showErrorView() {
        setContentView(R.layout.error_layout);
        TextView errorView = (TextView) findViewById(R.id.errorMessage);
        errorView.setText("App cannot connect to network. Check network settings and try again.");
    }
}
