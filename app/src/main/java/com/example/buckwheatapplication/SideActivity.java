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
//            buckwheatPrice.setText(doc.title().toString());
            Elements tables = doc.getElementsByAttribute("table-response mfm-table mfcur-table-lg mfcur-table-lg-currency-cur has-no-tfoot");
            Element dol = doc.body().getElementsByTag("table").first().children().get(1).children().get(2).getElementsByTag("span").first();
            buckwheatPrice.setText("Dollar to hryvna: "  + dol.text());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
