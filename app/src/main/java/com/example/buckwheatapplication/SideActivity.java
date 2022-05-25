package com.example.buckwheatapplication;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import static com.example.buckwheatapplication.MainActivity.buckwheatWeight;

public class SideActivity extends AppCompatActivity {

    private Document doc;
    private Document shop;
    private Thread secThread;
    private Runnable runnable;
    private EditText buckwheatPrice;
    private EditText hryvna;
    private EditText dollar;
    public static double dollarPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.side_activity);
        buckwheatPrice = findViewById(R.id.buckwheatPrice);
        hryvna = findViewById(R.id.hryvnias);
        dollar = findViewById(R.id.dollars);
        init();
    }

    private void init() {
        runnable = () -> getExchange();
        secThread = new Thread(runnable);
        secThread.start();
    }

    private void getExchange() {
        try {
            doc = Jsoup.connect("https://minfin.com.ua/currency/usd/").get();
            Elements tables = doc.getElementsByAttribute("table-response mfm-table mfcur-table-lg mfcur-table-lg-currency-cur has-no-tfoot");
            String dol = doc.body().getElementsByTag("table").first().children().get(1).children().get(2).getElementsByTag("span").first().text().substring(0, 5);
            //  buckwheatPrice.setText("Dollar to hryvna: " + dol);
            dollarPrice = Double.parseDouble(dol);
            shop = Jsoup.connect("https://aquamarket.ua/en/grechka/6532-sto-pudov-1-kg-krupa-grechnevaya-nezharennaya-m-u.html").get();
            Elements price = shop.getElementsByClass("product-price");
            Double buckPrice = Double.parseDouble(price.first().text().replace(" ", "").replace("u", "").replace("a", "").replace("h", ""))/ 100;
            buckwheatPrice.setText(buckPrice + " uah per 1 kg price");
            hryvna.setText(""+buckwheatWeight * buckPrice);
            dollar.setText(""+(buckwheatWeight * buckPrice)/dollarPrice);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
