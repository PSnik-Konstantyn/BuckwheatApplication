package com.example.buckwheatapplication;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class SideActivity extends AppCompatActivity {

    private Document doc;
    private Thread secThread;
    private Runnable runnable;
    private EditText buckwheatPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.side_activity);
        buckwheatPrice = findViewById(R.id.buckwheatPrice);
        init();
    }

    private void  init() {
        runnable = () -> getExchange();
        secThread = new Thread(runnable);
        secThread.start();
    }

    private void getExchange(){
        try {
            doc = Jsoup.connect("https://minfin.com.ua/currency/usd/").get();
            buckwheatPrice.setText(doc.title().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
